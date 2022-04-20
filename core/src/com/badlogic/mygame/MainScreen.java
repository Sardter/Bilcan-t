package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainScreen implements Screen {
    private  BilcantGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player character;
    private GameMap map;
    private ArrayList<GameObject> objects;
    //private ArrayList<NonPlayerCharacter> NPC;
    private Stage stage;
    private Table table1, table2;
    private BitmapFont font;
    private TextButton interactButton;
    private boolean moveOnMouse, isIneteracting;
    private boolean willBeLoaded;
    private Touchpad touchpad;

    final static int BOUNDRY_X = 800, BOUNDRY_Y = 480;
    final static int STARTING_POSITION_X = BOUNDRY_X / 2, STARTING_POSITION_Y = BOUNDRY_Y / 2;
    //final NonPlayerCharacter n = new NonPlayerCharacter();

    public MainScreen(BilcantGame game) {
        this.game = game;
        willBeLoaded = false;
    }
    public MainScreen(BilcantGame game, boolean loadLast) {
        this.game = game;
        willBeLoaded = loadLast;
    }
    public void setMoveOnMouse(boolean bool) {this.moveOnMouse = bool;}
    public void setisIneteracting(boolean bool) {this.isIneteracting = bool;}

    public Player getCharacter() {return character;}
    public OrthographicCamera getCamera() {return camera;}
    public ArrayList<GameObject> objects() {return objects;}
    public TextButton getInteractButton() {return interactButton;}
    public Touchpad getTouchpad() {return touchpad;}
    public boolean getMoveOnMouse() {return moveOnMouse;}
    public boolean getIsIneteracting() {return isIneteracting;}

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.position.x = STARTING_POSITION_X;
        camera.position.y = STARTING_POSITION_Y;

        batch = new SpriteBatch();


        character = new Player("rectext.png", 32, 32,
                STARTING_POSITION_X, STARTING_POSITION_Y);
        if (willBeLoaded) {
            loadGame();
        }
        map = new GameMap("map.png", 800, 480, - BOUNDRY_X,  -BOUNDRY_Y);

        GameObject object1 = new GameObject(0,"rectext.png", 64,
                64, 200, 200);
        GameObject object2 = new GameObject(1,"rectext.png", 64,
                64, 360, 360);
        objects = new ArrayList<>();
        objects.add(object1);
        objects.add(object2);


        NonPlayerCharacter np1 = new NonPlayerCharacter(false, 100, 200, 100, 200, 2, 200, 200, 64, 64);
        NonPlayerCharacter np2 = new NonPlayerCharacter(true,100, 200, 100, 200, 1, 100, 300, 64, 64);
        NonPlayerCharacter np3 = new NonPlayerCharacter(false, 100, 200, 100, 200, 3, 100, 100, 128, 128);
        NonPlayerCharacter np4 = new NonPlayerCharacter(false, 100, 200, 100, 200, 4, 200, 200, 128, 128);
        NonPlayerCharacter np5 = new NonPlayerCharacter(false, 100, 200, 100, 200, 2, 150, 150, 128 ,128);
        NonPlayerCharacter np6 = new NonPlayerCharacter(false, 100, 200, 100, 200, 1, 200, 200, 128 ,128);

        //NPC = new ArrayList<>();
        objects.add(np1);
        objects.add(np2);
        //objects.add(np3);
        //objects.add(np4);
        //objects.add(np5);
        //objects.add(np6);



        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table1 = new Table();
        table1.setFillParent(true);
        stage.addActor(table1);

        Skin skin1 = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        interactButton = new TextButton("Interact", skin1);
        interactButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                interactOnVicinity();

            }
        });
        interactButton.setVisible(false);
        table1.add(interactButton).expandY();
        table1.row();

        interactButton.setPosition(0,0);
        table1.setDebug(false);

        table2 = new Table();
        stage.addActor(table2);
        table2.setFillParent(true);
        TextButton menuButton = new TextButton("Menu", skin1);
        
        table2.top().left();

        table2.add(menuButton).pad(10);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveGame();
                game.changeScreen(BilcantGame.DETAIL);
            }
        });
        TextButton saveButton = new TextButton("Save", skin1);
        //table1.add(saveButton).pad(10);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveGame();
            }
        });

        Table table3 = new Table();
        table3.setFillParent(true);
        stage.addActor(table3);

        touchpad = new Touchpad(10, skin1);
        table3.add(touchpad).pad(20);
        table3.bottom().right();
    }

    public void onInteract() {
        isIneteracting = true;
        moveOnMouse = false;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("here");
                //isIneteracting = false;

            }
        }, 1000);
    }

    public void interactOnVicinity() {
        for ( GameObject object : objects) {
            if (object instanceof  NonPlayerCharacter) {
                NonPlayerCharacter npc = (NonPlayerCharacter) object;
                if (npc.getOnVicinity() && npc.getISImportant()) {
                    onInteract();
                    npc.interact();
                    break;
                }
            } else {
                if (object.getOnVicinity()) {
                    onInteract();
                    object.interact();
                    break;
                }
            }
        }
    }

    public void saveGame() {
        Preferences preferences = Gdx.app.getPreferences("myprefs");
        preferences.putFloat("x", character.x);
        preferences.putFloat("y", character.y);
        //preferences.flush();
    }

    public void loadGame() {
        Preferences preferences = Gdx.app.getPreferences("myprefs");
        character.x = preferences.getFloat("x");
        character.y = preferences.getFloat("y");
        camera.position.x = preferences.getFloat("x");
        camera.position.y = preferences.getFloat("y");
        System.out.println("here");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(map.getTexture(), map.x, map.y);
        batch.draw(character.getTexture(), character.x, character.y);

        for (GameObject object: objects) {
            if (object instanceof NonPlayerCharacter) {
                ((NonPlayerCharacter) object).moveBySpecificIndexLoop();
            }
            batch.draw(object.getTexture(), object.x, object.y);
        }



        batch.end();

        Controller.move(this);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport()
                .update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}
