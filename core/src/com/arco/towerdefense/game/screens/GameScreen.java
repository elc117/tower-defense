package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.controllers.GroundController;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
    final TowerDefenseGame game;
    private GroundController groundController;
    private Texture homeButton;

    Vector2 cursorLocation = new Vector2(0, 0);

    public GameScreen(TowerDefenseGame game) {
        this.game = game;
        groundController = new GroundController(game.batch,2, Consts.V_WIDTH, Consts.V_HEIGHT);
        homeButton = GameSingleton.getInstance().getTexture(Consts.HOME_BUTTON);
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
        cursorLocation.y = Consts.V_HEIGHT - Gdx.input.getY();

        game.batch.draw(homeButton, Consts.V_WIDTH - 32, Consts.V_HEIGHT - 32, 32, 32);

        if(cursorLocation.x >= Consts.V_WIDTH - 32 && cursorLocation.y >= Consts.V_HEIGHT - 32) {
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
