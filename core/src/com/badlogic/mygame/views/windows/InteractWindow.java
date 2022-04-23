package com.badlogic.mygame.views.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.mygame.models.GameObject;

public class InteractWindow extends InteractiveWindow {
    private GameObject gameObject;

    public InteractWindow(String title, WindowStyle style) {
        super(title, style);
        verticalGroup = new VerticalGroup();
    }

    public void setObject(Object object) {
        if (!(object instanceof GameObject)) return;
        GameObject gameObject = (GameObject) object;
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        this.gameObject = gameObject;
        this.name = new Label(gameObject.getName(), skin2);
        this.description = new Label(gameObject.getDescription(), skin2);
        description.setFontScale(0.7f);

        verticalGroup = new VerticalGroup();
        verticalGroup.addActor(name);
        verticalGroup.addActor(description);
        verticalGroup.addActor(new Label(" ", skin2));
        setCloseButton();
        addVerticalGroup();
    }

}
