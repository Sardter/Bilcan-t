package com.badlogic.mygame.models.items;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.mygame.models.player.Player;

public interface Item {
    void use(Player player);
    int getID();
    Texture getTexture();
    String getName();
    String getDescription();
    boolean getIsUsable();
}