package com.badlogic.mygame;

import com.badlogic.gdx.graphics.Texture;
import  com.badlogic.gdx.math.Rectangle;

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
        //this.inventory = new inventory()
    }
}
