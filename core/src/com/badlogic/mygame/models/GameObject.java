package com.badlogic.mygame.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.missions.FirstMission;
import com.badlogic.mygame.models.missions.MainStoryMissionLesson;
import com.badlogic.mygame.views.windows.InteractiveWindow;

import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.util.Random;
/**
          Main parent class for the objects within the game.
          Sets their size and position within the specific screen.
          Getters for, Id, texture, name and description of the object.
          Has a boolean field called "onVicinity" which indicates if the current game object is around another game object
          within the screen.
 */

public class GameObject extends Rectangle {
    private static int ID = 0;

    private Texture objectTexture;
    private boolean onVicinity = false;
    private boolean canInteract = true;
    private int id;
    protected String name, description;
    protected BilcantGame game;


    private Random rand = new Random();

    public  GameObject(String textureUrl, String name, String description,
                       int width, int height, int posX, int posY) {
        super();
        super.height = height;
        super.width = width;
        super.x = posX;
        super.y = posY;

        this.id = ID;
        ID++;

        this.name = name;
        this.description = description;

        this.objectTexture = new Texture(Gdx.files.internal(textureUrl));
    }
    public  GameObject(int id ,String textureUrl ,int width, int height) {
        super();
        super.height = height;
        super.width = width;
        int x = MathUtils.random(0, (int)this.getWidth());
        int y = MathUtils.random(0, (int)this.getHeight());
        super.x = x;
        super.y = y;

        this.id = id;

        this.objectTexture = new Texture(Gdx.files.internal(textureUrl));
    }

    public int  getId() {return  id;}
    public  boolean getOnVicinity() {return  onVicinity;}
    public Texture getTexture() {return objectTexture;}
    public String getName() {return this.name;}
    public String getDescription() {return this.description;}

    public void setGame(BilcantGame game){
        this.game = game;
    }

    public void setOnVicinity(boolean bool) {this.onVicinity = bool;}

    public void interact(InteractiveWindow interactWindow) {
        //System.out.println("smt"+ id);
        interactWindow.setObject(this);
        interactWindow.setVisible(true);
        if(this.name.equals("SA building") ){ //&& game.getMissionRouter().getCurrentMission().getName().equals("miniGame")
            //instanciate the quiz mechanism
            game.getMainScreen().saveGame();
            game.changeScreen(BilcantGame.QUIZ);

            MainStoryMissionLesson currentMission = (MainStoryMissionLesson) game.getMissionRouter().getCurrentMission();
            currentMission.setGame(game);
            currentMission.getTasks()[1].isCompleted();
            game.getMainScreen().saveGame();
            game.getMainScreen().drawTasks();
        }
        if(this.name.equals("B building") && game.getMissionRouter().getCurrentMission().getName().equals("main mission")){
            //call the DidEnterBbuilding() from the MainStoryMissionLesson
            //game.getPlayer().getInventory().addItem();
            game.getMainScreen().saveGame();
            MainStoryMissionLesson currentMission = (MainStoryMissionLesson) game.getMissionRouter().getCurrentMission();
            currentMission.setGame(game);

            currentMission.DidEnterBbuilding(game);

            game.getMainScreen().saveGame();
            game.getMainScreen().drawTasks();
        }
        if(this.name.equals("G building") && game.getMissionRouter().getCurrentMission().getName().equals("main mission")){
            //call the DidEnterGbuilding() from the MainStoryMissionLesson
            game.getMainScreen().saveGame();

            MainStoryMissionLesson currentMission = (MainStoryMissionLesson) game.getMissionRouter().getCurrentMission();
            currentMission.setGame(game);

            currentMission.DidEnterGbuilding(game);

            game.getMainScreen().saveGame();
            game.getMainScreen().drawTasks();
        }

        if(this.name.equals("A building") && game.getMissionRouter().getCurrentMission().getName().equals("First Mission")
        && game.getMissionRouter().getCurrentMission().getPlayer().getInventory().isItIn("idCard")){
            FirstMission currentMission = (FirstMission) game.getMissionRouter().getCurrentMission();
            currentMission.onCompleted(game);
        }
    }
}
