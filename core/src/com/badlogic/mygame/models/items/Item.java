package com.badlogic.mygame.models.items;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.mygame.models.player.Player;

public abstract class Item {
    protected String name;
    protected boolean isUsable;
    protected Texture texture;
    protected String textureUrl;
    protected String description;

    public Item(String name, String description ,String textureUrl) {
        this.name = name;
        this.isUsable = false;
        this.description = description;
        this.textureUrl = textureUrl;
        this.texture = new Texture(textureUrl);
    }

    public abstract void use(Player player);
    public Texture getTexture() {
        return  texture;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean getIsUsable() {
        return isUsable;
    }

    public String getTextureUrl() {
        return textureUrl;
    }
}