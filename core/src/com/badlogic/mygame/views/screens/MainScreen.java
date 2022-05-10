package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.controllers.Controller;
import com.badlogic.mygame.models.maps.MapRouter;
import com.badlogic.mygame.models.missions.MissionRouter;
import com.badlogic.mygame.models.maps.GameMap;
import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.models.npc.DialogItem;
import com.badlogic.mygame.models.npc.DialogOption;
import com.badlogic.mygame.models.npc.NPCDialog;
import com.badlogic.mygame.models.npc.NPCRoute;
import com.badlogic.mygame.models.npc.NPCRouter;
import com.badlogic.mygame.models.npc.NonPlayerCharacter;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.windows.InteractWindow;
import com.badlogic.mygame.views.windows.NPCInteractWindow;

import java.util.ArrayList;
import java.util.Arrays;
/**
        Screen where the most of the gameplay takes place.
        Player object is followed by an orthographic camera when it moves.
        Interactable and collision detectable NPC objects, interactable and collision detectable building objects are placed within
        this screen in order for the Player object to interact with.

 */
public class MainScreen implements Screen {
    private BilcantGame game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player character;
    private MapRouter mapRouter;
    private GameMap map;
    private ArrayList<GameObject> objects;
    private InteractWindow interactWindow;
    private NPCInteractWindow npcInteractWindow;
    private Stage stage;
    private Table interactContainer, menuContainer;
    private TextButton interactButton;
    private boolean moveOnMouse, isIneteracting;
    private boolean willBeLoaded;
    private Touchpad touchpad;
    private MissionRouter missionRouter;

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
    public InteractWindow getInteractWindow() {return interactWindow;}
    public boolean getMoveOnMouse() {return moveOnMouse;}
    public boolean getIsIneteracting() {return isIneteracting;}

    public BilcantGame getGame() {
        return game;
    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        camera.position.x = STARTING_POSITION_X;
        camera.position.y = STARTING_POSITION_Y;
        objects = new ArrayList<>();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        interactWindow = new InteractWindow("Interact", new Window.WindowStyle(
                new BitmapFont(),
                new Color(),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("itemWindowBackground.png")))
        ));
        npcInteractWindow = new NPCInteractWindow("NPC Interact", new Window.WindowStyle(
                new BitmapFont(),
                new Color(),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("itemWindowBackground.png")))
        ));


        character = game.getPlayer();
        mapRouter = new MapRouter(this, game);
        map = mapRouter.getMap();
        this.objects = map.getObjects().getObjects();
        game.initializeMissions();
        missionRouter = game.getMissionRouter();

        if (willBeLoaded) {
            loadGame();
            game.initializeMissions();
            missionRouter = game.getMissionRouter();
        } else {
            System.out.println(character);
            character.x = map.getSpawnX();
            character.y = map.getSpawnY();
            camera.position.x = map.getSpawnX();
            camera.position.y = map.getSpawnY();
        }


        interactContainer = new Table();
        interactContainer.setFillParent(true);
        stage.addActor(interactContainer);

        Skin skin1 = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        interactButton = new TextButton("Interact", skin1);
        interactButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                interactOnVicinity();

            }
        });
        interactButton.setVisible(false);
        interactContainer.add(interactButton).expandY();
        interactContainer.row();

        interactButton.setPosition(0,0);
        interactContainer.setDebug(false);

        menuContainer = new Table();
        stage.addActor(menuContainer);
        menuContainer.setFillParent(true);
        TextButton menuButton = new TextButton("Menu", skin1);
        
        menuContainer.top().left();

        menuContainer.add(menuButton).pad(10);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                saveGame();
                game.changeScreen(BilcantGame.DETAIL);
            }
        });

        Table touchContainer = new Table();
        touchContainer.setFillParent(true);
        stage.addActor(touchContainer);

        touchpad = new Touchpad(10, skin1);
        touchContainer.add(touchpad).pad(20);
        touchContainer.bottom().right();



        Table activeMissionContainer = new Table();
        activeMissionContainer.setFillParent(true);
        stage.addActor(activeMissionContainer);
        activeMissionContainer.top().right().pad(10);
        System.out.println(missionRouter);
        Label missionTitle = new Label(missionRouter.getCurrentMission().getName(), skin2);
        Label currentTask = new Label(missionRouter.getCurrentMission()
                .getCurrentTask().getDescription(), skin2);
        currentTask.setFontScale(0.5f);
        activeMissionContainer.add(missionTitle);
        activeMissionContainer.row();
        activeMissionContainer.add(currentTask);

        interactWindow.setVisible(false);
        interactWindow.setPosition(380, 220);
        stage.addActor(interactWindow);


        npcInteractWindow.setVisible(false);
        npcInteractWindow.setPosition(300, 150);
        npcInteractWindow.setSize(300,300);
        stage.addActor(npcInteractWindow);
    }



    public void createObjects() {
        GameObject[] gameObjects = {
                new GameObject("rectext.png", "Obj1", "desc1",
                        64,64, 200, 200),
                new GameObject("rectext.png", "Obj2", "desc2",
                        64, 64, 360, 360),
        };
        //setting the gameOBjects to include Bilcant game


        DialogOption[] options = {
                new DialogOption("good, you?", 1, true),
                new DialogOption("shut up, beach", -1, false)
        };

        DialogItem[] dialogItems = {
                new DialogItem("hey man, how are you?", options),
                new DialogItem("uga uga", null)
        };

        /*
        NonPlayerCharacter[] missionNPCs = {
                new NonPlayerCharacter("bucket.png", "take a quiz NPC", "npc desc",
                    true, 200, 200, new NPCDialog(missionDialogItems)),
        };
        */




        NonPlayerCharacter[] nonPlayerCharacters = {
                new NonPlayerCharacter("bucket.png", "important", "npc desc",
                        true, 100, 200, new NPCDialog(dialogItems)),
                new NonPlayerCharacter("bucket.png", "important 2", "npc desc",
                true, 100, 100, new NPCDialog(null)),
                /*new NonPlayerCharacter(true,100, 200, 100, 200, 1,
                        100, 300)*/
                new NonPlayerCharacter("bucket.png", "npc", "npc desc",
                        false, 200, 100, new NPCDialog(null)),

        };

        for (NonPlayerCharacter npc : nonPlayerCharacters) {
            NPCRoute[] routes = {
                    new NPCRoute(300, 300),
                    new NPCRoute(350, 300),
                    new NPCRoute(400, 400),

            };
            NPCRouter router = new NPCRouter(npc, routes);
            npc.setRouter(router);
        }

        this.objects.addAll(Arrays.asList(gameObjects));
        this.objects.addAll(Arrays.asList(nonPlayerCharacters));
    }


    public void interactOnVicinity() {
        for ( GameObject object : objects) {
            if (object instanceof  NonPlayerCharacter) {
                NonPlayerCharacter npc = (NonPlayerCharacter) object;
                if (npc.getOnVicinity() && npc.getISImportant()) {
                    npc.interact(npcInteractWindow);
                    break;
                }
            } else {
                if (object.getOnVicinity()) {
                    object.interact(interactWindow);
                    break;
                }
            }
        }
    }

    public void saveGame() {
        Preferences preferences = Gdx.app.getPreferences("myprefs");
        preferences.putFloat("x", character.x);
        preferences.putFloat("y", character.y);
        preferences.putInteger("avatar", character.getType());
        preferences.putInteger("mapIndex", mapRouter.getIndex());
        preferences.putString("inventory", character.getInventory().toJson());
        preferences.putString("stats", character.getStatsInJson());
        preferences.putInteger("mission", missionRouter.getIndex());
        for (int i = 0; i < missionRouter.getCurrentMission().getTasks().length; i++) {
            preferences.putBoolean("mainMission" + i ,missionRouter.getCurrentMission().getTasks()[i].getBoolean());
        }
        System.out.println(preferences.getInteger("mission"));
    }

    public void loadGame() {
        Preferences preferences = Gdx.app.getPreferences("myprefs");
        character = new Player(preferences.getInteger("avatar"), 64, 64,
                preferences.getFloat("x"), preferences.getFloat("y"));
        camera.position.x = preferences.getFloat("x");
        camera.position.y = preferences.getFloat("y");
        mapRouter.setIndex(preferences.getInteger("mapIndex"));
        character.getInventory().fromJson(preferences.getString("inventory"));
        character.setStatsFromJson(preferences.getString("stats"));
        missionRouter.setIndex(preferences.getInteger("mission"));

        int tasknumber = 0;
        for (int i = 0; i < missionRouter.getCurrentMission().getTasks().length; i++) {
            if(preferences.getBoolean("mainMission" + i ,missionRouter.getCurrentMission().getTasks()[i].getBoolean())){
                missionRouter.getCurrentMission().getTasks()[i].setCompleted(true);
                if(tasknumber >= missionRouter.getCurrentMission().getTasks().length -1){
                    missionRouter.getCurrentMission().onCompleted(game);
            }
                else{
                    missionRouter.getCurrentMission().nextTask();
                    tasknumber++;
                }
            }
        }
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
                //((NonPlayerCharacter) object).moveBySpecificIndexLoop();
                ((NonPlayerCharacter) object).traverse();
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
