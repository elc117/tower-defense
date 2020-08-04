package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LevelSelectScreen implements Screen {
    final TowerDefenseGame game;
    private Stage stage;
    private Skin skin;
    private Button btnLvl1, btnLvl2, btnLvl3, btnLvl4, btnLvl5;
    private Table table;
    private Image backGround;
    private TextButton backButton;

    public LevelSelectScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera), game.batch);
        this.stage.clear();

        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("levels/btnLevels.atlas"));
        this.skin.load(Gdx.files.internal("levels/btnLevels.json"));

        this.table = new Table(skin);
        table.setFillParent(true);

        this.backGround = new Image(new Texture("after_screens/levelSelectBackGround.jpg"));
        backGround.setBounds(0, 0, Consts.V_WIDTH, Consts.V_HEIGHT);

        initButtons();
        initListeners();
        composeScene();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void initButtons() {
        btnLvl1 = new Button(skin, "btnLvlOne");
        btnLvl1.addAction(sequence( alpha(0), fadeIn(.4f)));

        btnLvl2 = new Button(skin, "btnLvlTwo");
        btnLvl2.addAction(sequence( alpha(0), fadeIn(.45f)));

        btnLvl3 = new Button(skin, "btnLvlThr");
        btnLvl3.addAction(sequence( alpha(0), fadeIn(.5f)));

        btnLvl4 = new Button(skin, "btnLvlFou");
        btnLvl4.addAction(sequence( alpha(0), fadeIn(.55f)));

        btnLvl5 = new Button(skin, "btnLvlFiv");
        btnLvl5.addAction(sequence( alpha(0), fadeIn(.6f)));

        backButton = new TextButton("BACK", GameSingleton.getInstance().getSkin(), "default");
        backButton.setBounds(0, 0, backButton.getWidth()/2, backButton.getHeight()/2);
        backButton.addAction(sequence( alpha(0), fadeIn(.6f)));
    }

    private void initListeners() {
        btnLvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSingleton.getInstance().getConfirmSound().play();
                game.setScreen(game.gameScreen = new GameScreen(game, 1));
            }
        });

        btnLvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSingleton.getInstance().getConfirmSound().play();
                game.setScreen(game.gameScreen = new GameScreen(game, 2));
            }
        });

        btnLvl3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.gameScreen = new GameScreen(game, 3));
            }
        });

        btnLvl4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.gameScreen = new GameScreen(game, 4));
            }
        });

        btnLvl5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.gameScreen = new GameScreen(game, 5));
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSingleton.getInstance().getConfirmSound().play();
                game.setScreen(game.mainMenuScreen);
            }
        });
    }

    private void composeScene() {
        this.table.add(btnLvl1);
        this.table.add(btnLvl2).padLeft(20);
        this.table.add(btnLvl3).padLeft(20);
        this.table.add(btnLvl4).padLeft(20);
        this.table.add(btnLvl5).padLeft(20);

        this.stage.addActor(this.backGround);
        this.stage.addActor(backButton);
        this.stage.addActor(this.table);
    }

    @Override
    public void show() { Gdx.input.setInputProcessor(stage); }

    @Override
    public void hide() { GameSingleton.getInstance().restoreOldInputProcessor(); }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }
}
