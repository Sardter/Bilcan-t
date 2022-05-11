package com.badlogic.mygame.models.items;

import com.badlogic.mygame.models.player.Inventory;
import com.badlogic.mygame.models.player.Player;

public class Food extends Item {
    public Food(String name, String description, String textureUrl) {
        super(name, description, textureUrl);
        isUsable = true;
    }

    //methods
    public void use(Player player){
        isUsable = false;
        eat(player);

    }

    /*must interact with the avatar to use the food when used replenishes the avatars energy
    must interact with inventory to delete itself from it
    */
    public void eat(Player player){
        //adds energy to the avatar
        player.restoreEnergy();
        //deletes itself from inventory
        Inventory inventory = player.getInventory();
        inventory.delete(this);
    }
    public boolean getIsUsable() {return isUsable;}

}