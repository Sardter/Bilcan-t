package com.badlogic.mygame.views.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.mygame.BilcantGame;

public class LoadSaveScreen implements Screen {
    private final BilcantGame game;

    public LoadSaveScreen(BilcantGame game) {
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
