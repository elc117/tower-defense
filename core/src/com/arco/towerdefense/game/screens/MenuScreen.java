package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    Texture playButton;
    Texture playButtonHigh;
    Texture quitButton;
    Texture quitButtonHigh;
    Texture helpButton;
    Texture helpButtonHigh;

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
        playButtonUpdate();
        quitButtonUpdate();
    }

    public void playButtonUpdate() {
        float posX = Utils.getScreenCenterX() - playButton.getWidth()/2;
        float posY = Utils.getScreenCenterY() - playButton.getHeight()/2;

        if (Utils.isCursorInside(posX, posY, playButton.getWidth(), playButton.getHeight())) {
            game.batch.draw(playButtonHigh, posX, posY);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.gameScreen);
            }
        }
        else {
            game.batch.draw(playButton, posX, posY);
        }
    }

    public void quitButtonUpdate() {
        float posX = Utils.getScreenCenterX() - quitButton.getWidth()/2;
        float posY = Utils.getScreenCenterY() - quitButton.getHeight()/2 - playButton.getHeight() - 30; // 30 is the margin

        if (Utils.isCursorInside(posX, posY, quitButton.getWidth(), quitButton.getHeight())) {
            game.batch.draw(quitButtonHigh, posX, posY);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quitButton, posX, posY);
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