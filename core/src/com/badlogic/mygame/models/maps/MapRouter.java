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

    public MapRouter(MainScreen mainScreen, BilcantGame game) {
        this.index = 0;
        this.mainScreen = mainScreen;
        this.game = game;
        maps = new GameMap[]{
                new GameMap("map.png", 800, 480,
                        -800, -480, DEVELOPMENT, this),
                new GameMap("back2.jpeg", 800, 480,
                        -800, -480, DEV_2, this)
        };
    }

    public void setMap(int index) {
        this.index = index;
        mainScreen.setMap(getMap());
        mainScreen.setObjects(getMap().getObjects().getObjects());
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
