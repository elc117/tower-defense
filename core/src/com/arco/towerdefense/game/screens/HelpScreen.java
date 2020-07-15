package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.TowerDefenseGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HelpScreen implements Screen {
    final TowerDefenseGame game;
    BitmapFont font;

    public HelpScreen(TowerDefenseGame game) {
        this.game = game;
        font = new BitmapFont();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) game.setScreen(game.menuScreen);
            font.draw(game.batch,"PRESS \"SPACE\" TO BACK", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        game.batch.end();
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
