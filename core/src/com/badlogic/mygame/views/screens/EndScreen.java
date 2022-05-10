package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;

/**
          This screen is the screen for the menu.
          The user is able to switch to MissionScreen, InventoryScreen and InfoScreen, which are all separated classes.
 */
public class EndScreen implements Screen {
    private BilcantGame game;
    private Stage stage;

    public EndScreen(BilcantGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        Label menuLabel = new Label("Game Over!", skin);
        menuLabel.setFontScale(4);
        TextButton newGame = new TextButton("New Game", skin);
        TextButton exit = new TextButton("Exit", skin);

        TextureRegionDrawable textureRegionDrawableBg =
                new TextureRegionDrawable(new TextureRegion(
                        new Texture(Gdx.files.internal("menubackground.jpg"))));


        table.setBackground(textureRegionDrawableBg);
        table.add(menuLabel);
        table.row().pad(20, 0, 10, 0);
        table.add(newGame).fillX().uniformX();
        table.add(exit).fillX().uniformX();
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.AVATAR_SELECTION);
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
