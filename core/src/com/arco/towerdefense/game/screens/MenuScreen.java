package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    Texture configButton;
    Texture highConfigButton;
    Texture playButton;
    Texture quitButton;
    Texture helpButton;
    Sound selectionSound;
    Music music;
    int posY;

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        initButtons();
        initSounds();
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
        configButtonUpdate();
    }

    private void configButtonUpdate() {
        int posX = 730;
        int posY = 420;
        if (Utils.isCursorInside(posX, posY, configButton.getWidth()/11, configButton.getHeight()/11)) {
            game.batch.draw(highConfigButton, posX, posY,highConfigButton.getWidth()/11,highConfigButton.getHeight()/11);
            if (Gdx.input.isTouched()) {
                //CONFIG SCREEN
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
            }
        }
        else {
            game.batch.draw(configButton, posX, posY,configButton.getWidth()/11,configButton.getHeight()/11);
        }
    }

    public void playButtonUpdate() {
        int posX = 30;

        //game.setScreen(game.levelSelectScreen);
        music.stop();

        if (Utils.isCursorInside(posX, posY, playButton.getWidth()/5, playButton.getHeight()/5)) {
            game.batch.draw(playButton, posX, posY,playButton.getWidth()/5 + 20,playButton.getHeight()/5 + 20);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.levelSelectScreen);
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
                music.stop();

            }
        }
        else {
            game.batch.draw(playButton, posX, posY,playButton.getWidth()/5,playButton.getHeight()/5);
        }
    }

    public void helpButtonUpdate() {
        int posX = 300;

        if (Utils.isCursorInside(posX, posY, helpButton.getWidth()/5, helpButton.getHeight()/5)) {
            game.batch.draw(helpButton, posX, posY, helpButton.getWidth()/5 + 20,helpButton.getHeight()/5 + 20);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.helpScreen);
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
            }
        } else {
            game.batch.draw(helpButton, posX, posY,helpButton.getWidth()/5,helpButton.getHeight()/5);
        }
    }

    public void quitButtonUpdate() {
        int posX = 570;

        if (Utils.isCursorInside(posX, posY, quitButton.getWidth()/5, quitButton.getHeight()/5)) {
            game.batch.draw(quitButton, posX, posY,quitButton.getWidth()/5 + 20,quitButton.getHeight()/5 + 20);
            if (Gdx.input.isTouched()) {
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quitButton, posX, posY,quitButton.getWidth()/5,quitButton.getHeight()/5);
        }
    }

    @Override
    public void dispose() {
        selectionSound.dispose();
        music.dispose();
    }

    public void initButtons() {
        posY = 100;//botões alinhados em Y

        configButton = GameSingleton.getInstance().getTexture(Consts.CONFIG_BUTTON);
        highConfigButton = GameSingleton.getInstance().getTexture(Consts.HIGH_CONFIG_BUTTON);
        playButton =  GameSingleton.getInstance().getTexture(Consts.PLAY_BUTTON);
        quitButton = GameSingleton.getInstance().getTexture(Consts.QUIT_BUTTON);
        helpButton = GameSingleton.getInstance().getTexture(Consts.HELP_BUTTON);

    }

    private void initSounds() {
        selectionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/MainMenuSelection.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/musics/mainMenu.mp3"));
        float volume = GameSingleton.getInstance().soundController.getMusicVolume();
        music.setVolume(volume);
        music.setLooping(true);
        music.play();
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