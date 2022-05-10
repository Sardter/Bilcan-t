package com.badlogic.mygame.models.maps;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.models.minigames.MinigameLinker;
import com.badlogic.mygame.models.npc.DialogItem;
import com.badlogic.mygame.models.npc.DialogOption;
import com.badlogic.mygame.models.npc.NPCDialog;
import com.badlogic.mygame.models.npc.NPCRoute;
import com.badlogic.mygame.models.npc.NPCRouter;
import com.badlogic.mygame.models.npc.NonPlayerCharacter;

import java.util.ArrayList;
import java.util.Arrays;

public class MapObjects {
    private final ArrayList<GameObject> objects;
    private final int type;
    private final MapRouter mapRouter;

    public MapObjects(GameObject[] objects, MapRouter mapRouter) {
        this.objects = new ArrayList<>();
        this.objects.addAll(Arrays.asList(objects));
        this.type = -1;
        this.mapRouter = mapRouter;
    }

    public MapObjects(int type, MapRouter mapRouter) {
        this.objects = new ArrayList<>();
        this.type = type;
        this.mapRouter = mapRouter;
        addObjectsByType();
    }

    private void addObjectsByType() {
        switch (type) {
            case MapRouter.DEVELOPMENT:
                development();
                break;
            case MapRouter.DEV_2:
                dev2();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private void dev2() {
        GameObject[] gameObjects = {
                new Door("drop.png", "Obj2", "desc2",
                        64, 64, 260, 260, mapRouter, MapRouter.DEVELOPMENT),
                new MinigameLinker("rectext.png", "Obj2", "desc2",
                        64, 64, 350, 300,
                        mapRouter.getGame(), BilcantGame.ESCAPE_THE_BEES)
        };

        this.objects.addAll(Arrays.asList(gameObjects));
    }

    private void development() {
        GameObject[] gameObjects = {
                new GameObject("rectext.png", "Obj1", "desc1",
                        64,64, 200, 200),
                new GameObject("rectext.png", "Obj2", "desc2",
                        64, 64, 360, 360),
                new Door("drop.png", "Obj2", "desc2",
                        64, 64, 260, 260, mapRouter, MapRouter.DEV_2),
                new GameObject("rectext.png", "SA building", "important quiz",
                        64, 64, 600, 300),
                new GameObject("B_building - Copy.jpg", "B building", "Math lessons",
                        64, 64, 600, 200),
                new GameObject("rectext.png", "G building", "ENG lessons",
                        64, 64, 600, 90),
        };

        for (int i = 0; i < gameObjects.length; i++) {
            gameObjects[i].setGame(mapRouter.getGame());
        }

        DialogOption[] options = {
                new DialogOption("good, you?", 1, true),
                new DialogOption("shut up, beach", -1, false)
        };

        DialogItem[] dialogItems = {
                new DialogItem("hey man, how are you?", options),
                new DialogItem("uga uga", null)
        };
        DialogItem[] missionDialogItems = {
                new DialogItem("uga buga take a quiz at SA building", null)
        };

        NonPlayerCharacter[] nonPlayerCharacters = {
                new NonPlayerCharacter("bucket.png", "important", "npc desc",
                        true, 100, 200, new NPCDialog(dialogItems)),
                new NonPlayerCharacter("bucket.png", "important 2", "npc desc",
                        true, 100, 100, new NPCDialog(null)),
                /*new NonPlayerCharacter(true,100, 200, 100, 200, 1,
                        100, 300)*/
                new NonPlayerCharacter("bucket.png", "npc", "npc desc",
                        false, 200, 100, new NPCDialog(null)),
                new NonPlayerCharacter("bucket.png", "take a quiz NPC", "npc that gives quiz quest",
                        true, 300, 100, new NPCDialog(missionDialogItems)),
        };

        nonPlayerCharacters[3].setGame(mapRouter.getGame());

        for (NonPlayerCharacter npc : nonPlayerCharacters) {
            NPCRoute[] routes = {
                    new NPCRoute(200, 300),
                    new NPCRoute(350, 300),
                    //new NPCRoute(350, 250),
                    //new NPCRoute(400, 400)
            };
            NPCRouter router = new NPCRouter(npc, routes);
            npc.setRouter(router);
        }

        this.objects.addAll(Arrays.asList(gameObjects));
        this.objects.addAll(Arrays.asList(nonPlayerCharacters));
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
}
