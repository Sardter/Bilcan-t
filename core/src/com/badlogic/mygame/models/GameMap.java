package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Rectangle;

public class GameMap extends Rectangle {
    private Texture texture;

    public GameMap(String textureUrl, int width, int height, int boundry_x, int boundry_y) {
        super();
        super.width = width;
        super.height = height;
        super.x = boundry_x / 2;
        super.y = boundry_y / 2;

        texture = new Texture(Gdx.files.internal(textureUrl));
    }

    public Texture getTexture() {
        return texture;
    }
}
