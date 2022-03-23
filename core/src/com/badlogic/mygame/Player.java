package com.badlogic.mygame;

import com.badlogic.gdx.graphics.Texture;
import  com.badlogic.gdx.math.Rectangle;

public class Player extends Rectangle {
    private Texture texture;

    public Player(String textureUrl, int width, int height, int x, int y) {
        super();
        super.width = width;
        super.height = height;
        super.x = x;
        super.y = y;

        texture = new Texture(textureUrl);
    }

    public Texture getTexture() {
        return texture;
    }
}
