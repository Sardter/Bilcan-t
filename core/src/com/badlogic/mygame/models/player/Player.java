package com.badlogic.mygame.models.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import  com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;

public class Player extends Rectangle {
    private Texture texture;
    private float Energy; //0-1
    private float GPA; //0-1
    private float Popularity; //0-1
    private Inventory inventory;
    private long experience;
    private int level;
    private int[] levelCaps = {
            1000,
            2000,
            3000,
            4000,
            5000,
    };

    public Player(String textureUrl, int width, int height, int x, int y) {
        super();
        super.width = width;
        super.height = height;
        super.x = x;
        super.y = y;
        setEnergy(0f);
        setGPA(2f);
        setPopularity(0f);
        setExperience(0);

        level = 0;

        System.out.println(textureUrl);
        texture = new Texture(textureUrl);
        setInventory();
    }

    //setter methods
    public Texture getTexture() {
        return texture;
    }
    public void setEnergy(float energy){this.Energy = energy; }
    public void setGPA(float GPA) { this.GPA = GPA; }
    public void setPopularity(float popularity) { this.Popularity = popularity; }
    private void setInventory(){
        this.inventory = new Inventory();
    }
    public void setExperience(int xp){ experience = xp;}

    public float getGPA() {return this.GPA;}
    public float getEnergy() {return this.Energy;}
    public float getPopularity() {return this.Popularity;}
    public float getXPForCurrentLevel() {
        if (level == 0) return  experience;
        return levelCaps[level] * level - experience;
    }

    public long getExperience() {
        return experience;
    }

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

    //mission methods
    public void restoreEnergy(){
        setEnergy(Energy + 10);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addXP(int xp){
        experience += xp;
        if (xp > levelCaps[level]) {
            level++;
        }
    }
}
