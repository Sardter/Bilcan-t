package com.badlogic.mygame.models.missions;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.models.player.Player;

import java.util.HashMap;

public abstract class Mission {
    //variables
    protected Player player;
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

    public Player getPlayer() {
        return player;
    }

    public abstract void onCompleted(BilcantGame game);

    public Task getCurrentTask() {
        return tasks[taskIndex];
    }

    public void nextTask() {

        if (taskIndex < tasks.length) {

            if (!(tasks[taskIndex] == null)) {

                taskIndex++;
            }
        }
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public int getXPReward() {
        return xp;
    }

    public abstract boolean getMissioncompleted();

    public String getQuestExplanation() {

        if (this instanceof MainStoryMissionLesson) {
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

    public String tasksToJson() {
        final String[] tasksJson = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            tasksJson[i] = tasks[i].toJson();
        }
        return new Json().toJson(new HashMap<String, Object>() {{
            put("taskIndex", taskIndex);
            put("tasks", tasksJson);
        }});
    }

    public void updateMissionFromJson(String json) {
        HashMap<String, Object> map = new Json().fromJson(HashMap.class, json);

        Array<String> tasksJson = (Array<String>) map.get("tasks");
        this.taskIndex = (Integer) map.get("taskIndex");
        for (int i = 0; i < tasks.length; i++) {
            HashMap<String, Object> taskJson = new Json().fromJson(HashMap.class, tasksJson.get(i));
            tasks[i].setCompleted((Boolean) taskJson.get("completed"));
        }
    }
}

