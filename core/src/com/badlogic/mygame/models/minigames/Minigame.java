package com.badlogic.mygame.models.minigames;

import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;
import com.badlogic.mygame.views.screens.EscapeTheBeesMinigameScreen;

public abstract class Minigame {
    protected final Player player;
    protected final EscapeTheBeesMinigameScreen screen;
    protected boolean won;
    protected boolean lost;

    public Minigame(Player player,  EscapeTheBeesMinigameScreen screen) {
        this.player = player;
        this.screen = screen;
        this.won = false;
        this.lost = false;
    }

    public Player getPlayer() {
        return player;
    }


    public EscapeTheBeesMinigameScreen getScreen() {
        return screen;
    }

    public boolean isCompleted() {
        return won || lost;
    }

    public abstract void play(int[] input);
    public abstract void onWin();
    public abstract void onLose();
}
