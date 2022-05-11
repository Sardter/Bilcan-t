package com.badlogic.mygame.models.npc;

/**
          Moves the NPC objects within the screen within a certain route.
          When a route is fully traversed by a NPC object, it reverses the route so the NPC object doesn't go of the screen.
 */
public class NPCRouter {
    private final NonPlayerCharacter npc;
    private NPCRoute[] routes;
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

    public void reverseRoutes() {
        NPCRoute[] reversed = new NPCRoute[routes.length];
        for (int i = 0; i < routes.length; i++) {
            reversed[i] = routes[routes.length - 1 - i];
        }
        routes = reversed;
        index = 0;
    }

    public void traverse(int speed) {
        if (npc.getISImportant() || npc.isInCollision()) return;
        if (isDone()) {
            reverseRoutes();
            //System.out.println("reversed");
        }
        NPCRoute currentRoute = routes[index];
        if (currentRoute.isDone(npc.x, npc.y)) {
            //System.out.println("done: " + index);
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
        } else if (npc.y > currentRoute.getY()) {
            npc.y -= speed;
        }
    }
}
