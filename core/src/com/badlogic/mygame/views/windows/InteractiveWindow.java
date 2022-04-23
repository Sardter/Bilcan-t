package com.badlogic.mygame.views.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.SnapshotArray;

public abstract class InteractiveWindow extends Window {
    protected Label name, description;
    protected VerticalGroup verticalGroup;
    protected TextButton closeButton;

    public InteractiveWindow(String title, WindowStyle style) {
        super(title, style);
    }

    public abstract void setObject(Object object);

    protected void addVerticalGroup() {
        SnapshotArray<Actor> children = getChildren();
        for (Actor child: children) {
            if (child instanceof VerticalGroup) {
                child.remove();
                //return false;
            }
        }
        add(verticalGroup);
    }

    protected void setCloseButton() {
        Skin skin =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        closeButton = new TextButton("Close", skin);
        verticalGroup.addActor(closeButton);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                verticalGroup = new VerticalGroup();
                setVisible(false);
            }
        });
    }

}
