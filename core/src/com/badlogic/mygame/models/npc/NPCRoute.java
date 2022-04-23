package com.badlogic.mygame.models.npc;

public class NPCRoute {
    private final float x;
    private final float y;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isDone(float x, float y) {
        return this.x == x && this.y == y;
    }

    public NPCRoute(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
