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

    public int getIndex() {
        return index;
    }

    public Mission[] getMissions() {
        return missions;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void next() {
        index++;
    }

    public Mission[] getCurrentMissions() {
        return missions;
    }
}
