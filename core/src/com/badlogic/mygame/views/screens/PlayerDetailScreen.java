package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;

public class PlayerDetailScreen implements Screen {
    private final BilcantGame game;
    private final Stage stage;

    public PlayerDetailScreen(BilcantGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        //game.changeScreen(BilcantGame.MENU);
        Table table1 = new Table(), table2 = new Table();
        table1.setFillParent(true);
        table2.setFillParent(true);

        //table.setDebug(true);
        stage.addActor(table1);
        stage.addActor(table2);

        Skin skin1 = new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        TextButton backButton = new TextButton("Back", skin1);
        TextButton mainMenu = new TextButton("Main Menu", skin1);
        table1.add(backButton).pad(10);
        table1.add(mainMenu);
        table1.bottom();
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.LOADGAME);
            }
        });
        mainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.MENU);
            }
        });

        TextButton missions = new TextButton("Missions", skin2);
        TextButton inventory = new TextButton("Inventory", skin2);
        TextButton info = new TextButton("Info", skin2);

        missions.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.MISSIONS);
            }
        });
        inventory.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.INVENTORY);
            }
        });
        info.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeScreen(BilcantGame.INFO);
            }
        });

        table2.add(missions).pad(10);
        table2.row();
        table2.add(inventory).pad(10);
        table2.row();
        table2.add(info).pad(10);

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
