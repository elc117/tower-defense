package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.TowerDefenseGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    private BitmapFont font;

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        this.font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        font.draw(game.batch, "Welcome to Tower Defense ", 120, 150);
        font.draw(game.batch, "Tap anywhere to begin", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(game.gameScreen);
        }
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    @Override
    public void show() {

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
}