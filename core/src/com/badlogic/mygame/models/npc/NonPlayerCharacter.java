package com.badlogic.mygame.models.npc;

import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.models.missions.MainStoryMissionLesson;
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
<<<<<<< HEAD
        System.out.println(game);
        if(this.name.equals("Cat Girl") && game.getMissionRouter().getCurrentMission().getName().equals("main mission")){
=======
        if(this.name.equals("npc5") && game.getMissionRouter().getCurrentMission().getName().equals("main mission")){
>>>>>>> 44046b60462df7720a970fcdc15db07f65c81921

            //calls did interacted with npc method which is inside the mainStoryMissionLesson class
            MainStoryMissionLesson currentMission = (MainStoryMissionLesson) game.getMissionRouter().getCurrentMission();
            currentMission.setGame(game);
            currentMission.getTasks()[0].isCompleted();
            game.getMainScreen().drawTasks();
            game.getMainScreen().saveGame();
            game.getMainScreen().loadGame();

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
