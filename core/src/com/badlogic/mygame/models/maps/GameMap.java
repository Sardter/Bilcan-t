package com.badlogic.mygame.models.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameMap extends Rectangle {
    private final Texture texture;
    private final MapObjects objects;
    private final MapRouter mapRouter;
    private final float spawnX, spawnY;

    public float getSpawnX() {
        return spawnX;
    }

    public float getSpawnY() {
        return spawnY;
    }

    public GameMap(String textureUrl, int width, int height,
                   int boundry_x, int boundry_y, float spawnX , float spawnY , int objectsType, MapRouter mapRouter) {
        super();
        super.width = width;
        super.height = height;
        super.x = boundry_x / 2;
        super.y = boundry_y / 2;

        this.mapRouter = mapRouter;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        texture = new Texture(Gdx.files.internal(textureUrl));
        objects = new MapObjects(objectsType, mapRouter);
    }

    public MapObjects getObjects() {
        return objects;
    }

    public Texture getTexture() {
        return texture;
    }
}
