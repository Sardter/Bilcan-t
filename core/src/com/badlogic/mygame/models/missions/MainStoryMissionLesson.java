package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.models.player.Player;

//has chained missions related to the lessons in Collage
public class MainStoryMissionLesson extends Mission{

    private String Task1Description = "interact with specific NPC to learn about the existance of a quiz";
    private String Task2Description = "now take a quiz at the SA building";
    private String Task3Description = "attend 2 lessons by going to the buildings B and G";

    private boolean[] enteredBuiildings = {false, false};

    String aQuizexplanation = "this is a Bilcan-t academic quiz to upset you and see how miserable you are";
    private Quiz quizTask = new Quiz( aQuizexplanation);

    Task task1 = new Task(Task1Description) {
        @Override
        public boolean isCompleted() {
            player.addXP(10);
            task1.setCompleted(true);
            return true;
        }
    };
    Task task2 = new Task(Task2Description) {
        @Override
        public boolean isCompleted() {
            player.addXP(100);
            task2.setCompleted(true);
            return true;
        }
    };

    Task task3 = new Task(Task3Description) {
        @Override
        public boolean isCompleted() {
            player.addXP(1000);
            task3.setCompleted(true);
            return true;
        }
    };

    public MainStoryMissionLesson(Player player){
        super(player,"main mission", "it is a main mission related lessons", 1200, null, new Task[3]);
        tasks[0] = task1;
        tasks[1] = task2;
        tasks[2] = task3;
    }
    @Override
    public void onCompleted() {
        if(checkIfTasksCompleted() == false) {return;}
        player.setGpa(4);
        player.addXP(1500);
    }
    public boolean checkIfTasksCompleted(){
        for (int i = 0; i < tasks.length; i++) {
            if(!tasks[i].isCompleted()){
                return false;
            }
        }
        return true;
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
    public void DidEnterBbuilding(){
        enteredBuiildings[0] = true;
        if(enteredBuiildings[1] == true){
            task3.isCompleted();
        }
    }

    //must be called after entering the G building
    //must make G building in main game (done)
    //must add this method to the interaction of G building
    public void DidEnterGbuilding(){
        enteredBuiildings[1] = true;
        if(enteredBuiildings[0] == true){
            task3.isCompleted();
        }
    }
}
