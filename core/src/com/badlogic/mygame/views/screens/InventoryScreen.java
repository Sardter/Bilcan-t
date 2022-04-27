package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.items.Food;
import com.badlogic.mygame.models.player.Inventory;
import com.badlogic.mygame.models.items.Item;
import com.badlogic.mygame.views.windows.ItemWindow;

import java.util.ArrayList;

public class InventoryScreen implements Screen {
    private final BilcantGame game;
    private Stage stage;
    private Inventory inventory;


    public InventoryScreen(BilcantGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        System.out.println(game.getPlayer());
        this.inventory = game.getPlayer().getInventory();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Table table1 = new Table();
        table1.setFillParent(true);
        //table2.setFillParent(true);
        stage.addActor(table1);
        //stage.addActor(table2);

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

        this.inventory.addTo(new Food("asasd", "sadasd","bucket.png"));
        final ItemWindow itemWindow = new ItemWindow("item", new Window.WindowStyle(
                new BitmapFont(),
                new Color(),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("itemWindowBackground.png")))
        ), game.getPlayer(), this);
        stage.addActor(itemWindow);
        itemWindow.setSize(200, 250);
        itemWindow.setPosition(380,175);

        drawItems(itemWindow);

        itemWindow.setVisible(false);
        itemWindow.setModal(true);

        drawStats(skin1,skin2);
    }

    private void drawStats(Skin skin1, Skin skin2) {
        Table playerStats = new Table();
        ProgressBar GPA = new ProgressBar(0.0f,4.0f,0.5f, false, skin1);
        ProgressBar popularity = new ProgressBar(0f,1f,1f, false, skin1);
        ProgressBar energy = new ProgressBar(0f,1f,0.01f, false, skin1);
        ProgressBar xp = new ProgressBar(0f,1f,0.01f, false, skin1);

        GPA.setValue(game.getPlayer().getGPA());
        energy.setValue(game.getPlayer().getEnergy());
        popularity.setValue(game.getPlayer().getPopularity());
        xp.setValue(game.getPlayer().getXPForCurrentLevel());

        playerStats.add(new Label("Level: ", skin2));
        playerStats.add(new Label(game.getPlayer().getLevel() + "", skin2));
        playerStats.row();
        playerStats.add(new Label("XP: ", skin2));
        playerStats.add(xp);
        playerStats.row();
        playerStats.add(new Label("GPA: ", skin2));
        playerStats.add(GPA);
        playerStats.row();
        playerStats.add(new Label("Popularity: ", skin2));
        playerStats.add(popularity);
        playerStats.row();
        playerStats.add(new Label("Energy: ", skin2));
        playerStats.add(energy);
        playerStats.setFillParent(true);
        playerStats.top().pad(10);
        stage.addActor(playerStats);
    }

    public void drawItems(final ItemWindow itemWindow) {
        Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));

        HorizontalGroup itemsContainer = new HorizontalGroup();
        itemsContainer.pad(10f);
        itemsContainer.setFillParent(true);
        VerticalGroup itemsRow1 = new VerticalGroup();
        VerticalGroup itemsRow2 = new VerticalGroup();
        VerticalGroup itemsRow3 = new VerticalGroup();
        VerticalGroup itemsRow4 = new VerticalGroup();
        itemsContainer.addActor(itemsRow1);
        itemsContainer.addActor(itemsRow2);
        itemsContainer.addActor(itemsRow3);
        itemsContainer.addActor(itemsRow4);
        itemsRow1.pad(5);
        itemsRow1.space(5);
        itemsRow2.pad(5);
        itemsRow2.space(5);
        itemsRow3.pad(5);
        itemsRow3.space(5);
        itemsRow4.pad(5);
        itemsRow4.space(5);

        itemsContainer.center().padTop(200f);

        final ArrayList<Item> items = this.inventory.getItems();

        for (int i = 0; i < 16; i++) {
            Button item;
            if (i < items.size()) {
                Drawable itemTexture =
                        new TextureRegionDrawable(
                                new TextureRegion(items.get(i).getTexture()));
                item = new ImageButton(itemTexture);
                final int finalI = i;
                item.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        itemWindow.setObject(items.get(finalI));
                        itemWindow.setVisible(true);
                    }
                });
            } else {
                item = new TextButton("", skin1);
            }

            item.pad(10f);
            item.setSize(10f, 10f);
            if (i % 4 == 0) itemsRow1.addActor(item);
            else if (i % 4 == 1) itemsRow2.addActor(item);
            else if (i % 4 == 2) itemsRow3.addActor(item);
            else itemsRow4.addActor(item);
        }

        for (Actor child: stage.getActors()) {
            if (child instanceof HorizontalGroup) {
                child.remove();
            }
        }
        stage.addActor(itemsContainer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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