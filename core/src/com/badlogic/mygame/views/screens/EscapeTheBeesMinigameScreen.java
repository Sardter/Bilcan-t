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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.mygame.BilcantGame;
import com.badlogic.mygame.models.minigames.EscapeTheBeesMinigame;
import com.badlogic.mygame.models.player.Inventory;
import com.badlogic.mygame.views.windows.MinigameCompletionWindow;
/**
        Screen for the EscapeTheBeesMinigame, triggered when the player object interacts with the specific object
        within the MainScreen.
        EscapeTheBeesMinigame is a minigame under the minigames package.
*/

public class EscapeTheBeesMinigameScreen implements Screen {
    private final BilcantGame game;
    private final Stage stage;
    private final Inventory inventory;
    private EscapeTheBeesMinigame miniGame;
    private Table container, items;
    private MinigameCompletionWindow completionWindow;

    public EscapeTheBeesMinigameScreen(BilcantGame game) {
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
        //table2.setFillParent(true);
        stage.addActor(table1);
        //stage.addActor(table2);

        Skin skin1 =  new Skin(Gdx.files.internal("level-plane/skin/level-plane-ui.json"));
        Skin skin2 = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));


        TextureRegionDrawable textureRegionDrawableBg =
                new TextureRegionDrawable(new TextureRegion(
                        new Texture(Gdx.files.internal("escape_back.jpeg"))));
        table1.setBackground(textureRegionDrawableBg);

        miniGame = new EscapeTheBeesMinigame(game.getPlayer(), this, 4);

        TextButton up = new TextButton("up", skin1);
        up.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                miniGame.play(new int[]{-1,0});
                container.removeActor(items);
                drawItems();
            }
        });
        final TextButton down = new TextButton("down", skin1);
        down.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                miniGame.play(new int[]{1,0});
                container.removeActor(items);
                drawItems();
            }
        });
        TextButton left = new TextButton("left", skin1);
        left.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                miniGame.play(new int[]{0,-1});
                container.removeActor(items);
                drawItems();
            }
        });
        TextButton right = new TextButton("right", skin1);
        right.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                miniGame.play(new int[]{0,1});
                container.removeActor(items);
                drawItems();
            }
        });
        Table touchContainer = new Table();
        touchContainer.add(up).pad(10);
        touchContainer.add(down).pad(10);
        touchContainer.add(left).pad(10);
        touchContainer.add(right).pad(10);
        touchContainer.setFillParent(true);
        touchContainer.bottom().right().pad(10);
        stage.addActor(touchContainer);
        container = new Table();
        container.setFillParent(true);
        stage.addActor(container);
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

        items = new Table();
        for (EscapeTheBeesMinigame.MinigameObject[] row : miniGame.getPositions()) {
            for (EscapeTheBeesMinigame.MinigameObject minigameObject : row) {
                Button button;
                if (minigameObject == null) {
                    button = new ImageButton(new TextureRegionDrawable(
                            new TextureRegion(new Texture("item_skins/empty.png"))));
                } else {
                    Drawable itemTexture =
                            new TextureRegionDrawable(
                                    new TextureRegion(minigameObject.getTexture()));
                    button = new ImageButton(itemTexture);
                }
                button.pad(5f);
                items.add(button);
            }
            items.row();
        }
        container.add(items);
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