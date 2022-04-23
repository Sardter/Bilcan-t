package com.badlogic.mygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;

public class InfoScreen implements Screen {
    private final BilcantGame game;
    private Stage stage;

    public InfoScreen(BilcantGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table1 = new Table(), table2 = new Table();
        table1.setFillParent(true);
        table2.setFillParent(true);
        stage.addActor(table1);
        stage.addActor(table2);

        Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));


        final TextButton back = new TextButton("Back", skin1); // the extra argument here "small" is used to set the button to the smaller version instead of the big default version
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.DETAIL);
            }
        });
        table1.add(back).pad(10);
        table1.bottom();

        TextureRegionDrawable textureRegionDrawableBg =
                new TextureRegionDrawable(new TextureRegion(
                        new Texture(Gdx.files.internal("back2.jpeg"))));
        table1.setBackground(textureRegionDrawableBg);

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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
    }
}