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
    int buttonNormalSize;
    int buttonFocusedSize;
    int buttonFocusWidth;
    int buttonFocusHeight;
    int cutBorder; //Variavel responsavel por aumentar Y para que a area transparente da imagem dos botões sejam desconsiderada
    int posY;//botões alinhados

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        buttonNormalSize = 200;
        buttonFocusedSize = 220;
        buttonFocusWidth = 200;
        buttonFocusHeight = 80;
        cutBorder = 70;
        posY = 30;
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
        int posX = 30;


        if (Utils.isCursorInside(posX, posY + cutBorder, buttonFocusWidth, buttonFocusHeight)) {
            game.batch.draw(playButton, posX, posY,buttonFocusedSize,buttonFocusedSize);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.gameScreen);
                selectionSound.play(1.0f);
            }
        }
        else {
            game.batch.draw(playButton, posX, posY,buttonNormalSize,buttonNormalSize);
        }
    }

    public void helpButtonUpdate() {
        int posX = 300;

        if (Utils.isCursorInside(posX, posY + cutBorder, buttonFocusWidth, buttonFocusHeight)) {
            game.batch.draw(helpButton, posX, posY, buttonFocusedSize,buttonFocusedSize);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.helpScreen);
                selectionSound.play(1.0f);
            }
        } else {
            game.batch.draw(helpButton, posX, posY,buttonNormalSize,buttonNormalSize);
        }
    }

    public void quitButtonUpdate() {
        int posX = 570;

        if (Utils.isCursorInside(posX, posY + cutBorder, buttonFocusWidth, buttonFocusHeight)) {
            game.batch.draw(quitButton, posX, posY,buttonFocusedSize,buttonFocusedSize);
            if (Gdx.input.isTouched()) {
                selectionSound.play(1.0f);
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quitButton, posX, posY,buttonNormalSize,buttonNormalSize);
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