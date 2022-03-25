package com.badlogic.mygame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoadingScreen implements Screen {
    private final BilcantGame game;

    public LoadingScreen(BilcantGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        game.changeScreen(BilcantGame.MENU);
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
