package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    Texture playButton;
    Texture playButtonHigh;
    Texture quitButton;
    Texture quitButtonHigh;
    Texture helpButton;
    Texture helpButtonHigh;

    Vector2 cursorLocation = new Vector2(0, 0);

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        initButtons();
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            update();
        game.batch.end();

    }

    public void update() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = Consts.V_HEIGHT - Gdx.input.getY();

        playButtonUpdate();
        quitButtonUpdate();

    }

    public void playButtonUpdate() {
        if (cursorLocation.x > Consts.V_WIDTH / 2 - 90 && cursorLocation.x < Consts.V_WIDTH / 2 + 40 && cursorLocation.y > Consts.V_HEIGHT / 2 + 60 && cursorLocation.y < Consts.V_HEIGHT / 2 + 165) {
            game.batch.draw(playButtonHigh, Consts.V_WIDTH / 2 - playButtonHigh.getWidth() / 8, Consts.V_HEIGHT / 2, 200, 200);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.gameScreen);
            }
        }
        else {
            game.batch.draw(playButton, Consts.V_WIDTH / 2 - playButton.getWidth() / 8, Consts.V_HEIGHT / 2, 200, 200);
        }
    }

    public void quitButtonUpdate() {
        if (cursorLocation.x > Consts.V_WIDTH / 2 - 90 && cursorLocation.x < Consts.V_WIDTH / 2 + 40 && cursorLocation.y > Consts.V_HEIGHT / 2 + 60 - 120 && cursorLocation.y < Consts.V_HEIGHT / 2 + 165 - 120) {
            game.batch.draw(quitButton, Consts.V_WIDTH / 2 - quitButton.getWidth() / 8, Consts.V_HEIGHT / 2 - 120, 200, 200);
            game.batch.draw(quitButtonHigh, Consts.V_WIDTH / 2 - quitButtonHigh.getWidth() / 8, Consts.V_HEIGHT / 2 - 120, 200, 200);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quitButton, Consts.V_WIDTH / 2 - quitButton.getWidth() / 8, Consts.V_HEIGHT / 2 - 120, 200, 200);
        }
    }

    @Override
    public void dispose() {

    }

    public void initButtons() {
        playButton =  GameSingleton.getInstance().getTexture(Consts.PLAY_BUTTON);
        playButtonHigh = GameSingleton.getInstance().getTexture(Consts.PLAY_BUTTON_HIGHLIGHT);
        quitButton = GameSingleton.getInstance().getTexture(Consts.QUIT_BUTTON);
        quitButtonHigh = GameSingleton.getInstance().getTexture(Consts.QUIT_BUTTON_HIGHLIGHT);
        helpButton = GameSingleton.getInstance().getTexture(Consts.HELP_BUTTON);
        helpButtonHigh = GameSingleton.getInstance().getTexture(Consts.HELP_BUTTON_HIGHLIGHT);
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