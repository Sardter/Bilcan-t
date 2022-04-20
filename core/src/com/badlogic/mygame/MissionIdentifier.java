package com.badlogic.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MissionIdentifier {
    Label title, description;

    public MissionIdentifier(Mission mission) {
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
        this.title = new Label(mission.getName(), skin2);
        this.description = new Label(mission.getQuestExplanation(), skin2);
    }
}
