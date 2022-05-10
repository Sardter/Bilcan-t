package com.badlogic.mygame.models.player;

import com.badlogic.gdx.graphics.Texture;
import  com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;
/**
        The Player class whose object is the main object which the game is played.
        Has attributes like energy, gpa, popularity which are adjusted after each specific interaction.
        For example: If the player object interacts with a book object, player.gpa is incremented.
*/
public class Player extends Rectangle {
    private Texture texture;
    private float energy; //0-1
    private float gpa; //0-1
    private float popularity; //0-1
    private Inventory inventory;
    private long experience;
    private int level;
    private int type;
    private int[] levelCaps = {
            1000,
            2000,
            3000,
            4000,
            5000,
    };

    public static final int MERT = 0, SELEN = 1, DENIZ = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        selectTexture(type);
    }

    private void selectTexture(int type) {
        switch (type) {
            case MERT:
                texture = new Texture("npc_skins/npc8.png");
                break;
            case SELEN:
                texture = new Texture("npc_skins/npc1.png");
                break;
            case DENIZ:
                texture = new Texture("npc_skins/npc10.png");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

    }

    public Player(int type, int width, int height, float x, float y) {
        super();
        super.width = width;
        super.height = height;
        super.x = x;
        super.y = y;
        setEnergy(0.5f);
        setGpa(2f);
        setPopularity(0.5f);
        setExperience(0);

        level = 0;
        selectTexture(type);
        this.type = type;
        setInventory();
    }

    //setter methods
    public Texture getTexture() {
        return texture;
    }
    public void setEnergy(float energy){this.energy = energy;}
    public void setGpa(float gpa) { this.gpa = gpa; }
    public void setPopularity(float popularity) { this.popularity = popularity; }
    private void setInventory(){
        this.inventory = new Inventory();
    }
    public void setExperience(int xp){ experience = xp;}
    public float getGpa() {return this.gpa;}
    public float getEnergy() {return this.energy;}
    public float getPopularity() {return this.popularity;}
    public float getXPForCurrentLevel() {
        if (level == 0) return  experience;
        return levelCaps[level] * level - experience;
    }

    public float getXPPercentage() {
        return getXPForCurrentLevel() / (levelCaps[level] * (level + 1));
    }

    public long getExperience() {
        return experience;
    }

    //JSon for inventory
    public String getStatsInJson(){
        return new Json().toJson(new HashMap<String, Float>(){{
            put("energy", getEnergy());
            put("gpa", getGpa());
            put("popularity", getPopularity());
            put("xp", getExperience() + .0f);
        }});
    }
    public void setStatsFromJson(String json){
        HashMap<String, Float> map = new Json().fromJson(HashMap.class, json);
        System.out.println(map);
        setGpa(map.get("gpa"));
        setEnergy(map.get("energy"));
        setPopularity(map.get("popularity"));
        setExperience(map.get("xp").intValue());
    }



    //get methods
    public Inventory getInventory() {
        return inventory;
    }

    //mission methods
    public void restoreEnergy(){
        if (energy <= 0.9)
            setEnergy(energy + 0.1f);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void addXP(int xp){
        experience += xp;
        if (xp > levelCaps[level]) {
            level++;
        }
    }
}
