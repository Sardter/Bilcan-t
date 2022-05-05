package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.models.player.Player;

public class eat3FoodMission extends Mission {
    public eat3FoodMission(Player player, Task[] tasks){
        super(player, "Eat 3 Foods",
                "I need to eat three foods.", 100, null, tasks);
    }

    @Override
    public void onCompleted() {

    }

    public void GetExperience(){

    }
    public String getQuestExplanation(){
        return null;
    }
    public String getName(){
        return null;
    }
}
