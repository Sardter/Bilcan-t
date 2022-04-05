package com.badlogic.mygame;

public class Food extends Items{


    //constants

    //variables

    private static int IDcounter = 0;
    private int ID;
    private String name;
    private boolean useable;
    //constructor

    public Food(String aname){
        super();
        this.name = aname;
        this.useable = true;
    }

    //methods
    public int getID(){
        return ID;
    }
    public void use(Player player){
        useable = false;

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
        Inventory inventori = player.getInventory();
        inventori.delete(this);
    }

}