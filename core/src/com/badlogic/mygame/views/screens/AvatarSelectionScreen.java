package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.player.Player;

/**
          This screen is the screen for the menu.
          The user is able to switch to MissionScreen, InventoryScreen and InfoScreen, which are all separated classes.
 */
public class AvatarSelectionScreen implements Screen {
    private BilcantGame game;
    private Stage stage;
    private final Skin skin1, skin2;


    public AvatarSelectionScreen(BilcantGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin1 = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
    }

    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        table.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("menubackground.jpg")))));

        drawLabel();
        drawAvatars(table);
    }

    public void drawLabel() {
        Table labelContainer = new Table();
        labelContainer.add(new Label("Select Avatar", skin2)).pad(10);
        labelContainer.setFillParent(true);
        labelContainer.top();
        stage.addActor(labelContainer);
    }

    public void drawAvatars(Table table) {
        VerticalGroup mertContainer = new VerticalGroup();
        mertContainer.addActor(new Image(new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("npc_skins/npc8.png")))));
        TextButton selectMert = new TextButton("Mert",skin1);
        mertContainer.addActor(selectMert);
        selectMert.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setPlayer(new Player(Player.MERT, 25, 45, 0, 0));
                game.changeScreen(BilcantGame.APPLICATION);
            }
        });
        mertContainer.space(10);
        table.add(mertContainer).pad(10);

        VerticalGroup selenContainer = new VerticalGroup();
        selenContainer.addActor(new Image(new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("npc_skins/npc1.png")))));
        TextButton selectSelen = new TextButton("Alara",skin1);
        selenContainer.addActor(selectSelen);
        selectSelen.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setPlayer(new Player(Player.SELEN, 10, 45, 0, 0));
                game.changeScreen(BilcantGame.APPLICATION);
            }
        });
        selenContainer.space(10);
        table.add(selenContainer).pad(10);

        VerticalGroup denizContainer = new VerticalGroup();
        denizContainer.addActor(new Image(new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("npc_skins/npc10.png")))));
        TextButton selectDeniz = new TextButton("Deniz",skin1);
        denizContainer.addActor(selectDeniz);
        selectDeniz.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setPlayer(new Player(Player.DENIZ, 25, 45, 0, 0));
                game.changeScreen(BilcantGame.APPLICATION);
            }
        });
        denizContainer.space(10);
        table.add(denizContainer).pad(10);
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
