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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.minigames.FindATableMinigame;
import com.badlogic.mygame.models.player.Inventory;
import com.badlogic.mygame.views.windows.MinigameCompletionWindow;

public class FindTheTableScreen implements Screen {
    private final BilcantGame game;
    private final Stage stage;
    private final Inventory inventory;
    private FindATableMinigame miniGame;
    private Table container, container2, items, tryContainer;
    private MinigameCompletionWindow completionWindow;

    public FindTheTableScreen(BilcantGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.inventory = game.getPlayer().getInventory();
        Gdx.input.setInputProcessor(stage);
    }

    public BilcantGame getGame() {
        return game;
    }

    public MinigameCompletionWindow getCompletionWindow() {
        return completionWindow;
    }

    @Override
    public void show() {
        Table table1 = new Table();
        table1.setFillParent(true);
        stage.addActor(table1);

        TextureRegionDrawable textureRegionDrawableBg =
                new TextureRegionDrawable(new TextureRegion(
                        new Texture(Gdx.files.internal("find_the_table_back.jpeg"))));
        table1.setBackground(textureRegionDrawableBg);

        miniGame = new FindATableMinigame(game.getPlayer(), this, 3);

        container = new Table();
        container.setFillParent(true);
        stage.addActor(container);

        container2 = new Table();
        container2.setFillParent(true);
        container2.top().pad(10);
        stage.addActor(container2);
        drawItems();


        completionWindow = new MinigameCompletionWindow("Complete", new Window.WindowStyle(
                new BitmapFont(),
                new Color(),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("itemWindowBackground.png")))), game);
        completionWindow.setVisible(false);
        completionWindow.setSize(200, 250);
        completionWindow.setPosition(380,175);
        stage.addActor(completionWindow);
    }



    public void drawItems() {
        Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        FindATableMinigame.DinningTable[][] tables = miniGame.getTables();


        items = new Table();
        container.add(items);
        for (int i = 0; i < tables.length; i++) {
            FindATableMinigame.DinningTable[] column = tables[i];
            for (int j = 0; j < column.length; j++) {
                final FindATableMinigame.DinningTable dinningTable = column[j];
                Button button;
                if (miniGame.getCharacter().x == i && miniGame.getCharacter().y == j) {
                    Drawable itemTexture =
                            new TextureRegionDrawable(
                                    new TextureRegion(miniGame.getCharacter().texture));
                    button = new ImageButton(itemTexture);
                } else {
                    if (dinningTable.isFull) {
                        button = new ImageButton(new TextureRegionDrawable(
                                new TextureRegion(new Texture("item_skins/full_table.png"))));
                    } else {
                        button = new ImageButton(new TextureRegionDrawable(
                                new TextureRegion(new Texture("item_skins/table.png"))));
                    }
                }
                button.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        miniGame.play(new int[]{dinningTable.x, dinningTable.y});
                        container.removeActor(items);
                        container2.removeActor(tryContainer);
                        drawItems();
                    }
                });
                items.add(button).pad(10);
            }
            items.row();
        }
        if (miniGame.getCharacter().x == -1 && miniGame.getCharacter().y == -1) {
            items.add(new Label(" ", skin1));
            Drawable itemTexture =
                    new TextureRegionDrawable(
                            new TextureRegion(miniGame.getCharacter().texture));
            items.add(new ImageButton(itemTexture)).center();
        }

        tryContainer = new Table();
        tryContainer.add(new Label("Tries: " + miniGame.getTurnCount() +
                "/" + miniGame.getMaxTries(),skin2));
        //tryContainer.setFillParent(true);
        container2.add(tryContainer);
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