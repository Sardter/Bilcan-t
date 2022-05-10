package com.badlogic.mygame.models.items;

import com.badlogic.mygame.models.player.Player;

public class IdCard extends Item{

    public IdCard(String name, String id){
        super(name, id, "item_skins/ticket.png");
        isUsable = true;
    }
    @Override
    public void use(Player player) {
        player.getInventory().addItem(this);
        this.isUsable = false;
    }
}
