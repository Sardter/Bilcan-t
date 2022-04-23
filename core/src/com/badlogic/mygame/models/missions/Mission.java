package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;

public abstract class Mission {
    //variables
    protected final Player player;
    protected final String name, description;
    protected final int xp;
    protected final Item reward;
    protected final Task[] tasks;


    public Mission(Player player, String name, String description, int xp, Item reward, Task[] tasks) {
        this.player = player;
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.reward = reward;
        this.tasks = tasks;
    }

    public abstract void ifCompleted();

    public int GetXP() {
        return xp;
    }

    public String getQuestExplanation() {
        return description;
    }
    public String getName() {
        return this.name;
    }
}
