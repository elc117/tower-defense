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
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    Sprite configButton;
    Sprite highConfigButton;
    Sprite playButton;
    Sprite quitButton;
    Sprite helpButton;
    Sprite background;
    Sound selectionSound;
    Music music;
    private int posY;
    private int buttonWidth;
    private int buttonsHeight;

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        backgroundConfig();
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
        background.draw(game.batch);
        playButtonUpdate();
        helpButtonUpdate();
        quitButtonUpdate();
        configButtonUpdate();
    }

    private void backgroundConfig() {
        background = new Sprite(GameSingleton.getInstance().getTexture(Consts.MENU_BACKGROUND));
        background.setPosition(0,0);
        background.setSize(Consts.V_WIDTH,Consts.V_HEIGHT);
    }

    private void configButtonUpdate() {
        int posX = 730;
        int posY = 420;

        configButton.setPosition(posX,posY);
        highConfigButton.setPosition(posX,posY);
        if (Utils.isCursorInside(posX, posY, configButton.getWidth(), configButton.getHeight())) {
            highConfigButton.setSize(50,50);
            highConfigButton.draw(game.batch);
            if (Gdx.input.isTouched()) {
                //CONFIG SCREEN
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
            }
        }
        else {
            configButton.setSize(50,50);
            configButton.draw(game.batch);
        }
    }

    public void playButtonUpdate() {
        int posX = 30;

        //game.setScreen(game.levelSelectScreen);
        //music.stop();
        playButton.setPosition(posX,posY);
        if (Utils.isCursorInside(posX, posY, playButton.getWidth(), playButton.getHeight())) {
            playButton.setSize(buttonWidth +20,buttonsHeight +20);
            playButton.draw(game.batch);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.levelSelectScreen);
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
                music.stop();

            }
        }
        else {
            playButton.setSize(buttonWidth,buttonsHeight);
            playButton.draw(game.batch);
        }
    }

    public void helpButtonUpdate() {
        int posX = 300;


        helpButton.setPosition(posX,posY);
        if (Utils.isCursorInside(posX, posY, helpButton.getWidth(), helpButton.getHeight())) {
            helpButton.setSize(buttonWidth +20,buttonsHeight +20);
            helpButton.draw(game.batch);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.helpScreen);
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
            }
        } else {
            helpButton.setSize(buttonWidth,75);
            helpButton.draw(game.batch);
        }
    }

    public void quitButtonUpdate() {
        int posX = 570;

        quitButton.setPosition(posX,posY);
        if (Utils.isCursorInside(posX, posY, quitButton.getWidth(), quitButton.getHeight())) {
            quitButton.setSize(buttonWidth +20,buttonsHeight +20);
            quitButton.draw(game.batch);
            if (Gdx.input.isTouched()) {
                float volume = GameSingleton.getInstance().soundController.getEffectsVolume();
                selectionSound.play(volume);
                Gdx.app.exit();
            }
        } else {
            quitButton.setSize(buttonWidth,buttonsHeight);
            quitButton.draw(game.batch);
        }
    }

    @Override
    public void dispose() {
        selectionSound.dispose();
        music.dispose();
    }

    public void initButtons() {
        posY = 100;//botões alinhados em Y

        buttonWidth = 200;
        buttonsHeight = 75;

        configButton = new Sprite(GameSingleton.getInstance().getTexture(Consts.CONFIG_BUTTON));
        highConfigButton = new Sprite(GameSingleton.getInstance().getTexture(Consts.HIGH_CONFIG_BUTTON));
        playButton = new Sprite(GameSingleton.getInstance().getTexture(Consts.PLAY_BUTTON));
        quitButton = new Sprite(GameSingleton.getInstance().getTexture(Consts.QUIT_BUTTON));
        helpButton = new Sprite(GameSingleton.getInstance().getTexture(Consts.HELP_BUTTON));

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