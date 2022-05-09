package com.badlogic.mygame.models.maps;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.views.screens.MainScreen;

public class MapRouter {
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private final GameMap[] maps;
    private int index;
    private final MainScreen mainScreen;
    private final BilcantGame game;

    public final static int DEVELOPMENT = 0;
    public final static int DEV_2 = 1;
    public final static int MAIN_CAMPUS = 2;
    public final static int UPPER_CAMPUS = 3;

    public MapRouter(MainScreen mainScreen, BilcantGame game) {
        this.index = 2;
        this.mainScreen = mainScreen;
        this.game = game;
        maps = new GameMap[]{
                new GameMap("map.png", 800, 480,
                        -800, -480, 0, 0, DEVELOPMENT, this),
                new GameMap("back2.jpeg", 800, 480,
                        -800, -480, 0, 0,DEV_2, this),
                new GameMap("map_assets/main_campus.png", 1600, 800,
                        -800, -480, 500, 500,MAIN_CAMPUS, this),
                new GameMap("map_assets/upper_campus.png", 1600, 800,
                        -800, -480, 500, 0 ,UPPER_CAMPUS, this)
        };
    }

    public void setMap(int index) {
        this.index = index;
        mainScreen.setMap(getMap());
        mainScreen.setObjects(getMap().getObjects().getObjects());
        mainScreen.getCharacter().x = getMap().getSpawnX();
        mainScreen.getCharacter().y = getMap().getSpawnY();
        mainScreen.getCamera().position.x = getMap().getSpawnX();
        mainScreen.getCamera().position.y = getMap().getSpawnY();
    }

    public BilcantGame getGame() {
        return game;
    }

    public MainScreen getMainScreen() {
        return mainScreen;
    }

    public GameMap getMap() {
        return  maps[index];
    }
}
