package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.screens.EscapeTheBeesMinigameScreen;

//has chained missions related to the lessons in Collage
public class MainStoryMissionLesson extends Mission{

    public void setGame(BilcantGame game){
        this.game = game;
    }

    private String Task1Description = "interact with specific NPC to learn about the existance of a quiz";
    private String Task2Description = "now take a quiz at the SA building";
    private String Task3Description = "attend 2 lessons by going to the buildings B and G";

    private boolean[] enteredBuiildings = {false, false};
    private BilcantGame game;

    private boolean missionCompleted = false;

    String aQuizexplanation = "this is a Bilcan-t academic quiz to upset you and see how miserable you are";
    private Quiz quizTask = new Quiz( aQuizexplanation);

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
                if(!task1.getBoolean()){
                    player.addXP(10);
                    task1.setCompleted(true);
                    nextTask();
                    System.out.println("task 1 ez");
                }
            }

            return true;
        }
    };
    Task task2 = new Task(Task2Description) {
        @Override
        public boolean isCompleted() {

            if(!missionCompleted){
                System.out.println("task 2 no ez");
                if(task1.getBoolean()){
                    player.addXP(100);
                    task2.setCompleted(true);
                    nextTask();
                    System.out.println("task 2 ez");
                }
            }
            return true;
        }
    };

    Task task3 = new Task(Task3Description) {
        @Override
        public boolean isCompleted() {

            if(!missionCompleted){
                if(task2.getBoolean()){
                    player.addXP(1000);
                    task3.setCompleted(true);
                    nextTask();
                    System.out.println("task 3 ez");
                }
            }

            return true;
        }
    };

    public MainStoryMissionLesson(Player player){
        super(player,"main mission", "it is a main mission related lessons", 1200, null, new Task[3]);
        tasks[0] = task1;
        tasks[1] = task2;
        tasks[2] = task3;
    }

    //must construct in the main class a stationary NPC which says that there is a quiz in the SA building (done)
    public void DidinteractWithNPC(){
        task1.isCompleted();
    }

    //must write a pop quiz which has 2 random question and must pop when entering the SA building
    //must construct a SA building (done)
    // after entering the building this will ve completed but it must display the pop quiz
    public void DidEnterSAbuilding(){
        task2.isCompleted();
    }

    //must be called after entering the B building
    //must make B building in main game (done)
    //must add this method to the interaction of B building
    public void DidEnterBbuilding(BilcantGame game){
        enteredBuiildings[0] = true;
        if(enteredBuiildings[1] == true){
            task3.isCompleted();
            onCompleted(game);
        }

    }

    //must be called after entering the G building
    //must make G building in main game (done)
    //must add this method to the interaction of G building
    public void DidEnterGbuilding(BilcantGame game){
        enteredBuiildings[1] = true;
        if(enteredBuiildings[0] == true){
            task3.isCompleted();
            onCompleted(game);
        }
    }
}
