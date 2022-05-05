package com.badlogic.mygame.models.missions;

public class MissionRouter {
    private int index;
    private Mission[] missions;

    public MissionRouter(Mission[] missions) {
        this.missions = missions;
        this.index = 0;
    }

    public Mission getCurrentMission() {
        return missions[index];
    }

    public boolean hasNext() {
        return index < missions.length;
    }

    public void next() {
        index++;
    }
}