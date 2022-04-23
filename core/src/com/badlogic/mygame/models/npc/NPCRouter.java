package com.badlogic.mygame.models.npc;

public class NPCRouter {
    private final NonPlayerCharacter npc;
    private final NPCRoute[] routes;
    private final float initX, initY;
    private int index;

    public NPCRouter(NonPlayerCharacter npc, NPCRoute[] routes) {
        this.npc = npc;
        this.routes = routes;
        this.index = 0;
        this.initX = npc.x;
        this.initY = npc.y;
    }

    public NPCRoute[] getRoutes() {
        return routes;
    }

    public boolean isDone() {
        return index >= routes.length;
    }

    public void traverse(int speed) {
        if (npc.getISImportant() || npc.isInCollision()) return;
        if (isDone()) {
            npc.x = initX;
            npc.y = initY;
            index = 0; // TODO inverse instead of reset
        }
        NPCRoute currentRoute = routes[index];
        if (currentRoute.isDone(npc.x, npc.y)) {
            index++;
            if (isDone()) return;
            currentRoute = routes[index];
        }

        if (npc.x < currentRoute.getX()) {
            npc.x += speed;
        } else if (npc.x > currentRoute.getX()) {
            npc.x -= speed;
        }

        if (npc.y < currentRoute.getY()) {
            npc.y += speed;
        } else if (npc.x > currentRoute.getX()) {
            npc.y -= speed;
        }
    }
}
