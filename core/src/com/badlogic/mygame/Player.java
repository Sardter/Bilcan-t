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

    public Player(String textureUrl, int width, int height, int x, int y,double Aenergy, double aGPA, double aPopularity) {
        super();
        super.width = width;
        super.height = height;
        super.x = x;
        super.y = y;
        setEnergy(Aenergy);
        setGPA(aGPA);
        setPopularity(aPopularity);

        texture = new Texture(textureUrl);
        setInventory();
    }

    public Texture getTexture() {
        return texture;
    }
    private void setEnergy(double energy){this.Energy = energy; }
    private void setGPA(double GPA) { this.GPA = GPA; }
    private void setPopularity(double popularity) { this.Popularity = popularity; }
    private void setInventory(){
        this.inventory = new Inventory();
    }
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

}
