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
            case MapRouter.MAIN_CAMPUS:
                main_campus();
                break;
            case MapRouter.UPPER_CAMPUS:
                upper_campus();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private void main_campus() {
        this.objects.addAll(Arrays.asList(
                new Door("item_skins/door.png", "SB Building", "desc2",
                64, 64, 350, 470, mapRouter, MapRouter.DEVELOPMENT),
                new Door("item_skins/door.png", "SA Building", "desc2",
                        64, 64, 370, 100, mapRouter, MapRouter.DEVELOPMENT),
                new Door("item_skins/door.png", "B building", "desc2",
                        64, 64, 200, 850, mapRouter, MapRouter.DEVELOPMENT),
                new MinigameLinker("item_skins/bee.png", "Coffee Break", "desc2",
                        64, 64, 900, 470, mapRouter.getGame(), BilcantGame.ESCAPE_THE_BEES),
                new Door("item_skins/door.png", "G Building", "desc2",
                        64, 64, 1070, 950, mapRouter, MapRouter.DEVELOPMENT),
                new Door("item_skins/door.png", "Upper Campus", "desc2",
                        64, 64, 600, 1000, mapRouter, MapRouter.UPPER_CAMPUS),
                new Door("item_skins/door.png", "Dorms", "desc2",
                        64, 64, 700, 0, mapRouter, MapRouter.DEVELOPMENT),
                new Door("item_skins/door.png", "A Building", "desc2",
                        64, 64, 1050, 100, mapRouter, MapRouter.DEVELOPMENT)
                ));

        this.objects.addAll(Arrays.asList(
                new NonPlayerCharacter("npc_skins/npc4.png", "npc 1", "npc desc",
                        false, 500, 100, new NPCDialog(null)) {{
                            setRouter(new NPCRouter(this, new NPCRoute[]{
                                    new NPCRoute(500,100),
                                    new NPCRoute(600,200),
                                    new NPCRoute(400,200),
                                    new NPCRoute(400,400)
                            }));
                }},
                new NonPlayerCharacter("npc_skins/npc6.png", "npc 3", "npc desc",
                        false, 300, 830, new NPCDialog(null)) {{
                    setRouter(new NPCRouter(this, new NPCRoute[]{
                            new NPCRoute(300,830),
                            new NPCRoute(600,820),
                    }));
                }},
                new NonPlayerCharacter("npc_skins/npc9.png", "npc 4", "npc desc",
                        false, 50, 900, new NPCDialog(null)) {{
                    setRouter(new NPCRouter(this, new NPCRoute[]{
                            new NPCRoute(50,900),
                            new NPCRoute(20,400),
                    }));
                }},
                new NonPlayerCharacter("npc_skins/npc11.png", "npc 5", "npc desc",
                        true, 800, 400, new NPCDialog(null)) {{
                    setRouter(new NPCRouter(this, new NPCRoute[]{}));
                }},
                new NonPlayerCharacter("npc_skins/npc12.png", "npc 6", "npc desc",
                        true, 830, 370, new NPCDialog(null)) {{
                    setRouter(new NPCRouter(this, new NPCRoute[]{}));
                }},
                new NonPlayerCharacter("npc_skins/npc15.png", "npc 7", "npc desc",
                        true, 970, 200, new NPCDialog(null)) {{
                    setRouter(new NPCRouter(this, new NPCRoute[]{}));
                }},
                new NonPlayerCharacter("npc_skins/npc5.png", "npc 2", "npc desc",
                        false, 600, 700, new NPCDialog(null)) {{
                    setRouter(new NPCRouter(this, new NPCRoute[]{
                            new NPCRoute(600,700),
                            new NPCRoute(800,700),
                            new NPCRoute(750,800)
                    }));
                }}
        ));
    }

    private void upper_campus() {
        this.objects.addAll(Arrays.asList(
                new Door("item_skins/door.png", "Main Campus", "desc2",
                        64, 64, 500, -200, mapRouter, MapRouter.MAIN_CAMPUS),
                new MinigameLinker("item_skins/table.png", "Restaurant", "desc2",
                        64, 64, 870, 250, mapRouter.getGame(), BilcantGame.FIND_THE_TABLE),
                new Door("item_skins/door.png", "EB Building", "desc2",
                        64, 64, 640, 440, mapRouter, MapRouter.DEVELOPMENT),
                new MinigameLinker("item_skins/table.png", "CafeInn", "desc2",
                        64, 64, 880, -100, mapRouter.getGame(), BilcantGame.FIND_THE_TABLE),
                new Door("item_skins/door.png", "Library", "desc2",
                        64, 64, 500, 100, mapRouter, MapRouter.DEVELOPMENT)
        ));
    }

    private void dev2() {
        GameObject[] gameObjects = {
                new Door("item_skins/door.png", "Obj2", "desc2",
                        64, 64, 260, 260, mapRouter, MapRouter.DEVELOPMENT),
                new MinigameLinker("item_skins/bee.png", "Obj2", "desc2",
                        64, 64, 350, 300,
                        mapRouter.getGame(), BilcantGame.ESCAPE_THE_BEES),
                new MinigameLinker("item_skins/table.png", "Obj2", "desc2",
                    64, 64, 300, 100,
                    mapRouter.getGame(), BilcantGame.FIND_THE_TABLE),
        };

        this.objects.addAll(Arrays.asList(gameObjects));
    }

    private void development() {
        GameObject[] gameObjects = {
                new GameObject("item_skins/laundry.png", "Obj1", "desc1",
                        64,64, 200, 200),
                new GameObject("item_skins/pillow.png", "Obj2", "desc2",
                        64, 64, 360, 360),
                new Door("item_skins/door.png", "Obj2", "desc2",
                        64, 64, 260, 260, mapRouter, MapRouter.DEV_2),

                new GameObject("rectext.png", "SA building", "important quiz",
                        64, 64, 600, 300),
                new GameObject("B_building - Copy.jpg", "B building", "Math lessons",
                        64, 64, 600, 200),
                new GameObject("rectext.png", "G building", "ENG lessons",
                        64, 64, 600, 90),

                new MinigameLinker("bucket.png", "Obj3", "miniGame", 64, 64,
                        480, 200, mapRouter.getGame(), BilcantGame.QUIZ),

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
                new NonPlayerCharacter("npc_skins/npc1.png", "important", "npc desc",
                        true, 100, 200, new NPCDialog(dialogItems)),
                new NonPlayerCharacter("npc_skins/npc3.png", "important 2", "npc desc",
                        true, 100, 100, new NPCDialog(null)),
                /*new NonPlayerCharacter(true,100, 200, 100, 200, 1,
                        100, 300)*/

                new NonPlayerCharacter("bucket.png", "npc", "npc desc",
                        false, 200, 100, new NPCDialog(null)),
                new NonPlayerCharacter("bucket.png", "take a quiz NPC", "npc that gives quiz quest",
                        true, 300, 100, new NPCDialog(missionDialogItems)),

                new NonPlayerCharacter("npc_skins/npc4.png", "npc", "npc desc",
                        false, 200, 100, new NPCDialog(null))

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
