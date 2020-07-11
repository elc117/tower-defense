package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.controllers.GroundController;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.controllers.InputController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;

import static com.arco.towerdefense.game.TowerDefenseGame.V_HEIGHT;
import static com.arco.towerdefense.game.TowerDefenseGame.V_WIDTH;

public class GameScreen implements Screen {
    final TowerDefenseGame game;
    private GroundController groundController;
    private Texture homeButton;

    Vector2 cursorLocation = new Vector2(0, 0);

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        groundController = new GroundController(game.batch,2, game.V_WIDTH, game.V_HEIGHT);
        homeButton = new Texture("menu/HomeButton.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            groundController.update();
            homeButtonUpdate();
        game.batch.end();
    }

    public void homeButtonUpdate() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = V_HEIGHT - Gdx.input.getY();

        game.batch.draw(homeButton, V_WIDTH - 32, V_HEIGHT - 32, 32, 32);

        if(cursorLocation.x >= V_WIDTH - 32 && cursorLocation.y >= V_HEIGHT - 32) {
            if (Gdx.input.isTouched()) {
                game.setScreen(game.menuScreen);
            }
        }
    }

    @Override
    public void dispose() {
        groundController.dispose();
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
