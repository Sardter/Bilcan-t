package com.badlogic.mygame.models;


import com.badlogic.gdx.graphics.Texture;

public interface Item {
    void use(Player player);
    int getID();
    Texture getTexture();
    String getName();
    String getDescription();
    boolean getIsUsable();
}