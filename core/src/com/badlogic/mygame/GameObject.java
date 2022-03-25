package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class GameObject extends Rectangle {
    private Texture objectTexture;
    private int id;
    private boolean onVicinity = false;
    private boolean canInteract = true;

    public  GameObject(int id ,String textureUrl ,int width, int height, int x, int y) {
        super();
        super.height = height;
        super.width = width;
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
