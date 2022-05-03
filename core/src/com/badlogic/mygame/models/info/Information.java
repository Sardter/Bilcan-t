package com.badlogic.mygame.models.info;

public class Information {
    private final String name, description, type;
    private final int neededXP;

    public static String TUTORIAL = "Tutorial";
    public static String GENERAL = "General";
    public static String NPC = "Character";

    public String getType() {
        return type;
    }

    public Information(String name, String description, int neededXP, String type) {
        this.name = name;
        this.description = description;
        this.neededXP = neededXP;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getNeededXP() {
        return neededXP;
    }

    public boolean isVisible(long xp) {
        return xp >= neededXP;
    }
}
