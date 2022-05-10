package com.badlogic.mygame.models.missions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.mygame.BilcantGame;

import java.util.HashMap;

/**





 */
public abstract class Task {
    private final String description;

    private boolean completed = false;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public abstract boolean isCompleted();

    public boolean getBoolean(){
        return completed;
    }

    public String toJson() {
        return new Json().toJson(new HashMap<String, Object>(){{
            put("completed", completed);
            put("description", description);
        }});
    }

    public void updateTask(String json) {
        HashMap<String, Object> map = new Json().fromJson(HashMap.class, json);
        setCompleted((Boolean) map.get("completed"));
    }
}
