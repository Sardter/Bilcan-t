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

    //constructor
    public FirstMission(Player player){
        super(player, "First Mission",
                "Go to the A building and get the bilcan-t card item from the NPC",
                100, null, new Task[]{new Task("Some task") {
                    @Override
                    public boolean isCompleted() {
                        return false;
                    }
                }});
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
