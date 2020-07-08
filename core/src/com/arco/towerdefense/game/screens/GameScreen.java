package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.controllers.GroundController;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.controllers.InputController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
    final TowerDefenseGame game;
    private GroundController groundController;
    private InputController mouse;

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        groundController = new GroundController("grasstop.png", "dirt.png", 16, 2, game.V_WIDTH, game.V_HEIGHT);
        mouse = new InputController();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
            groundController.paint(game.batch);
        game.batch.end();

        update();

    }

    public void update() {
        groundController.update();
    }

    @Override
    public void dispose() {
        groundController.dispose();
        mouse.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

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
