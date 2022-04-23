package com.badlogic.mygame.missions;

import com.badlogic.mygame.models.Item;
import com.badlogic.mygame.models.Mission;
import com.badlogic.mygame.models.Player;

public class FirstMission extends Mission {
    //variables
    private String explanation;

    //ID card item
    private Item idcard;
    private String name;

    //constructor
    public FirstMission(Player player){
        super(player);
        explanation = "Go to the B building and get the bilcan-t card item from the NPC";
        name = "First Mission";
    }

    //inherited methods
    public String getQuestExplanation(){
        return explanation;
    }
    public void Ifcompleted(){
        GetExperience();
        player.getInventory().delete(idcard);
    }
    public void setDifficulty(){
        //no difficulty
    }
    public String getName() {return name;}
    public void GetExperience(){
        player.giveXP(100);
    }
    // other missions
}
