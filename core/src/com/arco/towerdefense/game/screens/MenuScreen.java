package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    Texture playButton;
    Texture quitButton;
    Texture helpButton;
    Sound selectionSound;

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
        helpButtonUpdate();
        quitButtonUpdate();
    }

    public void playButtonUpdate() {
        if (Utils.isCursorInside(30, 50, 200, 145)) {
            game.batch.draw(playButton, 30, 30,220,220);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.gameScreen);
                selectionSound.play(1.0f);
            }
        }
        else {
            game.batch.draw(playButton, 30, 30,200,200);
        }
    }

    public void helpButtonUpdate() {
        if (Utils.isCursorInside(300, 50, 200, 145)) {
            game.batch.draw(helpButton, 300, 30,220,220);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.helpScreen);
                selectionSound.play(1.0f);
            }
        } else {
            game.batch.draw(helpButton, 300, 30,200,200);
        }
    }

    public void quitButtonUpdate() {
        if (Utils.isCursorInside(570, 50, 200, 145)) {
            game.batch.draw(quitButton, 570, 30,220,220);
            if (Gdx.input.isTouched()) {
                selectionSound.play(1.0f);
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quitButton, 570, 30,200,200);
        }
    }

    @Override
    public void dispose() {
        selectionSound.dispose();
    }

    public void initButtons() {
        playButton =  GameSingleton.getInstance().getTexture(Consts.PLAY_BUTTON);
        quitButton = GameSingleton.getInstance().getTexture(Consts.QUIT_BUTTON);
        helpButton = GameSingleton.getInstance().getTexture(Consts.HELP_BUTTON);
        selectionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/MainMenuSelection.mp3"));
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