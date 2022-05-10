package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.player.Player;

public class MinigameMissions extends Mission{
    private String Task1Description = "play the FindaTable mini game in the cafetaria, to play go to the topmost door and then to the table icon";
    private String Task2Description = "now play the escape the bees game, go to the bee icon";

    private BilcantGame game;

    private boolean missionCompleted = false;

    public MinigameMissions(Player player){
        super(player,"mini game Mission", "play and struggle to complete the mini games", 1200, null, new Task[2]);
        tasks[0] = task1;
        tasks[1] = task2;
    }

    @Override
    public void onCompleted(BilcantGame game) {
        player.setGpa(4);
        player.addXP(1500);
        missionCompleted = true;

    }

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

    public boolean checkIfTasksCompleted(){
        for (int i = 0; i < tasks.length; i++) {
            if(!tasks[i].isCompleted()){
                return false;
            }
        }
        return true;
    }
}
