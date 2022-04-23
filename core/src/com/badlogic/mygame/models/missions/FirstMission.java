package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;

public class FirstMission extends Mission {
    //variables

    //ID card item
    private Item idcard;

    //constructor
    public FirstMission(Player player){
        super(player, "First Mission",
                "Go to the B building and get the bilcan-t card item from the NPC",
                100, null, null);
    }

    //inherited methods
    public void ifCompleted(){
        GetExperience();
        player.getInventory().delete(idcard);
    }

    public void GetExperience(){
        player.giveXP(100);
    }
    // other missions
}
