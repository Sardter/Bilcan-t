package com.badlogic.mygame.models.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.views.windows.InteractiveWindow;
import com.badlogic.mygame.views.windows.NPCInteractWindow;
/**
        Class of the NPCs.
        Field variables:
        boolean isImportant => true if this NPC is intractable, else false.
        boolean isInCollision => true if NPC is in collision with another object, else false.
        int speed => speed for how the NPC traverses the NPCRoute.
        NPCRouter router => designates the route and how the NPC will operate within the route.

        interact(InteractiveWindow interactWindow) method => allows the NPC object to interact with other objects.
 */

public class NonPlayerCharacter extends GameObject {
    private boolean isImportant;
    private boolean first = true;
    private boolean isInCollision;
    private int speed;
    private NPCDialog dialog;

    private NPCRouter router;

    public NPCDialog getDialog() {
        return dialog;
    }

    public void setDialog(NPCDialog dialog) {
        this.dialog = dialog;
    }



    public void setIsInCollision(boolean condition) {this.isInCollision = condition;}



    public NonPlayerCharacter(String textureUrl, String name, String description,
                              boolean isImportant, int posX, int posY, NPCDialog dialog) {
            super(textureUrl, name, description, 50, 90, posX, posY);
            this.isImportant = isImportant;
            this.speed = 2;
            this.dialog = dialog;
    }

    public void setRouter(NPCRouter router) {
        this.router = router;
    }

    public boolean isInCollision() {
        return isInCollision;
    }


    @Override
    public void interact(InteractiveWindow interactWindow) {
        if (!(interactWindow instanceof NPCInteractWindow)) return;
        System.out.println(this);
        interactWindow.setObject(this);
        interactWindow.setVisible(true);
        if(this.name.equals("take a quiz NPC")){
            //calls did interacted with npc method which is inside the mainStoryMissionLesson class
        }
    }



    public void traverse() {
        router.traverse(this.speed);
    }

    /**
     * to make an NPC move in a specific loop
     * there is a speed variable to make them look more random and realistic
     */





    public boolean getISImportant(){
        return isImportant;
    }

}
