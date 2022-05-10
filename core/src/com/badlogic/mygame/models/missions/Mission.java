package com.badlogic.mygame.models.missions;

import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;

public abstract class Mission {
    //variables
    protected final Player player;
    protected final String name, description;
    protected final int xp;
    protected final Item reward;
    protected final Task[] tasks;
    protected int taskIndex;


    public Mission(Player player, String name, String description, int xp, Item reward, Task[] tasks) {
        this.player = player;
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.reward = reward;
        this.tasks = tasks;
        this.taskIndex = 0;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public abstract void onCompleted(BilcantGame game);

    public Task getCurrentTask() {
        return tasks[taskIndex];
    }

    public void nextTask(){
        if(taskIndex < 3){
            taskIndex++;
        }
    }

    public int getXPReward() {
        return xp;
    }

    public String getQuestExplanation() {

        if(this instanceof MainStoryMissionLesson) {
            int i = 0;
            for (int j = 0; j < tasks.length; j++) {
                if (!tasks[j].getBoolean()) {
                    return tasks[j].getDescription();
                }
            }
        }
        return description;
    }
    public String getName() {
        return this.name;
    }
}
