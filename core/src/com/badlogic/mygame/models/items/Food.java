package com.badlogic.mygame.models;

import com.badlogic.gdx.graphics.Texture;

public class Food implements Item {


    //constants

    //variables

    private static int IDcounter = 0;
    private int ID;
    private String name;
    private boolean isUsable;
    private Texture texture;
    private String description;
    //constructor

    public Food(String name, String description ,String textureUrl){
        super();
        this.name = name;
        this.isUsable = true;
        this.description = description;
        this.texture = new Texture(textureUrl);
    }

    public Texture getTexture() {return this.texture;}

    public String getName(){
        return name;
    }

    public String getDescription() {return this.description;}

    //methods
    public int getID(){
        return ID;
    }
    public void use(Player player){
        isUsable = false;

        eat(player);

    }
    public void setID(){
        ID = IDcounter;
        IDcounter++;
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
    public int getFoodCount(){
        return IDcounter;
    }
    public boolean getIsUsable() {return isUsable;}

}