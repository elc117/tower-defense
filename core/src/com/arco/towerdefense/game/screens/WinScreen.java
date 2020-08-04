package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class WinScreen implements Screen {
    final TowerDefenseGame game;
    private Stage stage;
    private Table table;
    private Skin skin;
    private TextButton continueButton;
    private TextButton restartButton;
    private TextButton exitButton;
    private Image victoryImage;
    private Image trophyImage;
    private Image backGround;
    private Music winMusic;
    private int lastLevel;

    public WinScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        this.stage.clear();

        this.skin = GameSingleton.getInstance().getSkin();

        this.table = new Table();
        this.table.setFillParent(true);
        this.table.center();

        this.winMusic = Gdx.audio.newMusic(Gdx.files.internal("after_screens/winning.mp3"));

        initButtons();
        initImages();
        initListeners();
        composeScene();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
        winMusic.dispose();
    }

    private void initButtons() {
        continueButton = new TextButton("CONTINUE", skin, "default");
        restartButton = new TextButton("RESTART", skin, "default");
        exitButton = new TextButton("EXIT", skin, "default");
    }

    private void initImages() {
        this.backGround = new Image(new Texture("after_screens/winScreenBackGround.jpg"));
        this.victoryImage = new Image(new Texture("after_screens/victory.png"));
        this.trophyImage = new Image(new Texture("after_screens/trophy.png"));

        backGround.setBounds(0, 0, Consts.V_WIDTH, Consts.V_HEIGHT);
    }

    private void initListeners() {
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                winMusic.stop();
                game.setScreen(game.levelSelectScreen);
            }
        });

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                winMusic.stop();
                game.setScreen(game.gameScreen = new GameScreen(game, lastLevel));
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                winMusic.stop();
                game.setScreen(game.mainMenuScreen);
            }
        });
    }

    private void composeScene() {
        table.add(trophyImage);
        table.row();
        table.add(victoryImage);
        table.getCell(victoryImage).padBottom(20);
        table.row();
        table.add(continueButton);
        table.row();
        table.add(restartButton);
        table.row();
        table.add(exitButton);
        stage.addActor(backGround);
        stage.addActor(table);
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        winMusic.play();
    }

    @Override
    public void hide() { GameSingleton.getInstance().restoreOldInputProcessor(); }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }
}
