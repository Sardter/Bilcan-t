package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import  com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;


import java.util.ArrayList;

public class Player extends Rectangle {
    private Texture texture;
    private double Energy; //0-1
    private double GPA; //0-1
    private double Popularity; //0-1
    private Inventory inventory;
    private long experience;
    private static int eatCount = 0;

    public Player(String textureUrl, int width, int height, int x, int y) {
        super();
        super.width = width;
        super.height = height;
        super.x = x;
        super.y = y;
        setEnergy(100);
        setGPA(0);
        setPopularity(100);
        setExperience(0);

        texture = new Texture(textureUrl);
        setInventory();
    }

    //setter methods
    public Texture getTexture() {
        return texture;
    }
    public void setEnergy(double energy){this.Energy = energy; }
    public void setGPA(double GPA) { this.GPA = GPA; }
    public void setPopularity(double popularity) { this.Popularity = popularity; }
    private void setInventory(){
        this.inventory = new Inventory();
    }
    public void setExperience(int xp){ experience = xp;}
    public void increaseEatCount(){ eatCount++;}

    //JSon for inventory
    public void openJsonInventory(){
        Json json = new Json();
        String str = json.toJson(inventory);
        FileHandle file = Gdx.files.local("items.json");
        file.writeString(str, true);
    }
    public void getJsonInventory(){
        Json json = new Json();
        FileHandle file = Gdx.files.local("items.json");
        String StrItems = file.readString();
        inventory = json.fromJson(Inventory.class, StrItems);
    }



    //get methods
    public Inventory getInventory() {
        return inventory;
    }
    public int getEatCount() { return eatCount;}

    //mission methods
    public void restoreEnergy(){
        setEnergy(Energy + 10);
    }
    public void giveXP(int xp){
        experience += xp;
    }
}
