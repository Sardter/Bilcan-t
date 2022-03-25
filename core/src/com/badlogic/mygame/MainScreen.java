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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MainScreen implements Screen {
    private  BilcantGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player character;
    private GameMap map;
    private ArrayList<GameObject> objects;
    private Stage stage;
    private Table table;
    private BitmapFont font;
    private TextButton interactButton;
    private boolean moveOnMouse, isIneteracting;
    private boolean willBeLoaded;

    final static int BOUNDRY_X = 800, BOUNDRY_Y = 480;
    final static int STARTING_POSITION_X = BOUNDRY_X / 2, STARTING_POSITION_Y = BOUNDRY_Y / 2;

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

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));

        TextButton menuButton = new TextButton("Menu", skin);



        interactButton = new TextButton("Interact", skin);
        interactButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                isIneteracting = true;
                interactOnVicinity();

            }
        });
        interactButton.setVisible(false);
        table.add(interactButton).expandY();
        table.row();

        interactButton.setPosition(0,0);
        table.setDebug(false);

        table.add(menuButton);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.MENU);
            }
        });
        TextButton saveButton = new TextButton("Save", skin);
        table.add(saveButton);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveGame();
            }
        });
    }

    public void interactOnVicinity() {
        for ( GameObject object : objects) {
            if (object.getOnVicinity()) {
                object.interact();
                break;
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
