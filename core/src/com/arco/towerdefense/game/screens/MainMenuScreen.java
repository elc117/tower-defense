package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class MainMenuScreen implements Screen {

    final TowerDefenseGame game;
    private Stage stage;
    private Skin skin;
    private Button playButton, quitButton, helpButton, configButton;
    private Table table;

    public MainMenuScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        stage.clear();

        //used SkinComposer to generate menu.atlas/menu.json/menu.png
        //https://github.com/raeleus/skin-composer
        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("menu/menu.atlas"));
        this.skin.load(Gdx.files.internal("menu/menu.json"));

        //table é uma espécie de stackLayout que o mineiro tinha feito
        this.table = new Table(skin);

        initButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.draw();
    }

    public void update(float delta) {
        stage.act(delta);
    }


    @Override
    public void dispose() {
        //selectionSound.dispose();
        //music.dispose();
        stage.dispose();
    }

    public void initButtons() {
        playButton = new Button(skin, "playButton");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.gameScreen);
            }
        });
        playButton.addAction(sequence( alpha(0), fadeIn(.5f)));


        quitButton = new Button(skin, "quitButton");
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        quitButton.addAction(sequence( alpha(0), fadeIn(.5f)));

        helpButton = new Button(skin, "helpButton");
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("help screen");
            }
        });
        helpButton.addAction(sequence( alpha(0), fadeIn(.5f)));

        configButton = new Button(skin, "configButton");
        configButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("config screen");
            }
        });
        configButton.addAction(sequence( alpha(0), fadeIn(.5f)));

        table.setBounds(0, 0, stage.getWidth(), stage.getHeight());

        table.add(playButton);
        table.add(helpButton);
        table.add(quitButton);
        table.add(configButton).padBottom(300).padLeft(50);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    private void initSounds() {

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