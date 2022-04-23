package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;


public class GameObject extends Rectangle {
    private Texture objectTexture;
    private int id;
    private boolean onVicinity = false;
    private boolean canInteract = true;
    Random rand = new Random();

    public  GameObject(int id ,String textureUrl ,int width, int height, int x, int y) {
        super();
        super.height = height;
        super.width = width;
        super.x = x;
        super.y = y;

        this.id = id;

        this.objectTexture = new Texture(Gdx.files.internal(textureUrl));
    }
    public  GameObject(int id ,String textureUrl ,int width, int height) {
        super();
        super.height = height;
        super.width = width;
        int x = MathUtils.random(0, (int)this.getWidth());
        int y = MathUtils.random(0, (int)this.getHeight());
        super.x = x;
        super.y = y;

        this.id = id;

        this.objectTexture = new Texture(Gdx.files.internal(textureUrl));
    }

    public int  getId() {return  id;}
    public  boolean getOnVicinity() {return  onVicinity;}
    public Texture getTexture() {return objectTexture;}

    public void setOnVicinity(boolean bool) {this.onVicinity = bool;}


    public void interact() {
        System.out.println("smt"+ id);
    }
}
