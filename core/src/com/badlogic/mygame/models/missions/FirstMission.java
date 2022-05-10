package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.screens.EscapeTheBeesMinigameScreen;

public class FirstMission extends Mission {
    //variables

    //ID card item
    private Item idcard;
    private boolean missionCompleted = false;
    private String Task1Description = "go the SA building and answer the questions perfectly to collect the idCard";
    private String Task2Description = "now go the A building to drop the id Card";
    private BilcantGame game;

    Task task1 = new Task(Task1Description) {
        @Override
        public boolean isCompleted() {

            if(!missionCompleted){
                System.out.println("task 1 ez");
                player.addXP(10);
                task1.setCompleted(true);
                nextTask();
            }
            return true;
        }
    };
    Task task2 = new Task(Task2Description) {
        @Override
        public boolean isCompleted() {

            if(!missionCompleted){
                System.out.println("task 2 ez");
                player.addXP(100);
                task2.setCompleted(true);
                onCompleted(game);
            }
            return true;
        }
    };

    //constructor
    public FirstMission(Player player){
        super(player, "First Mission",
                "Go to the A building and get the bilcan-t card item from the NPC",
                100, null, new Task[2]);
        tasks[0] = task1;
        tasks[1] = task2;
    }
    public Player getPlayer(){
        return player;
    }

    //inherited methods
    public void onCompleted(BilcantGame game){
        GetExperience();
        player.getInventory().delete(idcard);
        missionCompleted = true;
    }

    public void GetExperience(){
        player.addXP(100);
    }

}
