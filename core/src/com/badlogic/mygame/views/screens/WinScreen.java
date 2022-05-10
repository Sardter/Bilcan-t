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

public class WinScreen implements Screen {
    private final BilcantGame game;
    private final Stage stage;

    public WinScreen(BilcantGame game){
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show() {
        Table  table1 = new Table();
        table1.setFillParent(true);
        stage.addActor(table1);
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        TextureRegionDrawable textureRegionDrawableBg =
                new TextureRegionDrawable(new TextureRegion(
                        new Texture(Gdx.files.internal("back2.jpeg"))));
        table1.setBackground(textureRegionDrawableBg);

        final TextButton exit = new TextButton("Cancel", skin2);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(game);
                game.changeScreen(BilcantGame.LOADGAME);
            }
        });
        table1.addActor(exit);

        final Label question = new Label("YOU WON!!", skin2);
        table1.add(question);
        table1.row().pad(10, 0, 10, 0);


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

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
