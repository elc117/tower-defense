package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class MainMenuScreen implements Screen {
    final TowerDefenseGame game;
    private Stage stage;
    private Skin skin;
    private TextButton playButton, quitButton, helpButton;
    private Table table;
    private Music menuMusic;
    private Image backGround;
    private Image title;

    public MainMenuScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        stage.clear();

        this.skin = GameSingleton.getInstance().getSkin();

        this.table = new Table();
        table.setFillParent(true);
        table.center();

        this.backGround = new Image(new Texture("menu/menu-background.png"));
        backGround.setBounds(0, 0, Consts.V_WIDTH, Consts.V_HEIGHT);
        backGround.addAction(sequence( alpha(0), fadeIn(0.5f)));

        this.title = new Image(new Texture("title.png"));
        title.addAction(sequence( alpha(0), fadeIn(1f)));

        initButtons();
        initListeners();
        composeScene();

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu/menuMusic.mp3"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void initButtons() {
        playButton = new TextButton("PLAY", skin, "default");
        playButton.addAction(sequence( alpha(0), fadeIn(1f)));

        quitButton = new TextButton("QUIT", skin, "default");
        quitButton.addAction(sequence( alpha(0), fadeIn(1f)));

        helpButton = new TextButton("HELP", skin, "default");
        helpButton.addAction(sequence( alpha(0), fadeIn(1f)));
    }

    private void initListeners() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuMusic.stop();
                GameSingleton.getInstance().getConfirmSound().play();
                game.setScreen(game.levelSelectScreen);
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuMusic.stop();
                GameSingleton.getInstance().getConfirmSound().play();
                Gdx.app.exit();
            }
        });

        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuMusic.stop();
                GameSingleton.getInstance().getConfirmSound().play();
                game.setScreen(game.helpScreen);
            }
        });
    }

    private void composeScene() {
        table.add(title);
        table.row();
        table.add(playButton);
        table.row();
        table.add(helpButton);
        table.row();
        table.add(quitButton);

        stage.addActor(backGround);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        menuMusic.play();
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