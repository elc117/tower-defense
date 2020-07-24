package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.TowerDefenseGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class LevelSelectScreen implements Screen {

    final TowerDefenseGame game;
    BitmapFont testFont;

    public LevelSelectScreen(TowerDefenseGame game) {
        this.game = game;

        testFont = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            testFont.draw(game.batch, "HELLO WORLD, PRESS ENTER TO START LEVEL 1", 120, 120);
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                game.gameScreen.setLevel(1);
                game.setScreen(game.gameScreen);
            }
        game.batch.end();
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

    @Override
    public void dispose() {

    }
}
