package com.badlogic.mygame.views.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.mygame.BilcantGame;

public class MinigameCompletionWindow extends InteractiveWindow{
    private String[] items;
    private final BilcantGame game;

    public MinigameCompletionWindow(String title, WindowStyle style, BilcantGame game) {
        super(title, style);
        this.game = game;
    }

    @Override
    public void setObject(Object object) {
        if (!(object instanceof String[])) return;
        items = (String[]) object;
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        this.name = new Label(items[0], skin2);
        this.description = new Label(items[1], skin2);
        description.setFontScale(0.7f);

        verticalGroup = new VerticalGroup();
        verticalGroup.addActor(name);
        verticalGroup.addActor(description);
        verticalGroup.addActor(new Label(" ", skin2));
        setCloseButton();
        addVerticalGroup();
    }

    @Override
    protected void setCloseButton() {
        Skin skin =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        closeButton = new TextButton("Close", skin);
        verticalGroup.addActor(closeButton);
        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                verticalGroup = new VerticalGroup();
                setVisible(false);
                game.changeScreen(BilcantGame.LOADGAME);
            }
        });
    }
}
