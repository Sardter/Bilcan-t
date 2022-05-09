package com.badlogic.mygame.models.missions;

public abstract class Task {
    private final String description;

    private boolean completed;

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
}
