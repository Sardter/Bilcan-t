package com.badlogic.mygame.models.missions;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;

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

    public String missionsDataToJson() {
        final String[] missionData = new String[missions.length];
        for (int i = 0; i < missions.length; i++) {
            missionData[i] = missions[i].tasksToJson();
        }
        return new Json().toJson(new HashMap<String, Object>(){{
            put("missionsData", missionData);
        }});
    }

    public void dataFromJson(String json) {
        HashMap<String, Object> data = new Json().fromJson(HashMap.class, json);
        Array<String> missionData = (Array<String>) data.get("missionsData");
        for (int i = 0; i < missionData.size; i++) {
            missions[i].updateMissionFromJson(missionData.get(i));
        }
    }
}
