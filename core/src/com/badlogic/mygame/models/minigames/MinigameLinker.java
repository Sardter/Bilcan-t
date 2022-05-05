package com.badlogic.mygame.models.minigames;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.GameObject;
import com.badlogic.mygame.views.windows.InteractiveWindow;

public class MinigameLinker extends GameObject {
    private final BilcantGame game;
    private final int miniGame;

    public MinigameLinker(String textureUrl, String name, String description,
                          int width, int height, int posX, int posY, BilcantGame game ,int miniGame) {
        super(textureUrl, name, description, width, height, posX, posY);
        this.game = game;
        this.miniGame = miniGame;
    }

    @Override
    public void interact(InteractiveWindow interactWindow) {
        game.changeScreen(miniGame);
    }
}
