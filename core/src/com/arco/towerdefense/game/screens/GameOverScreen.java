package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameOverScreen implements Screen {
    final TowerDefenseGame game;
    private Stage stage;
    private Table table;
    private Skin skin;
    private TextButton restartButton;
    private TextButton exitButton;
    private Image gameOverImage;
    private Image backGround;

    public GameOverScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        this.stage.clear();

        this.backGround = new Image(new Texture("after_screens/gameOverBackGround.png"));
        backGround.setBounds(0, 0, Consts.V_WIDTH, Consts.V_HEIGHT);

        this.table = new Table();
        this.table.setFillParent(true);
        this.table.center();

        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("after_screens/after.atlas"));
        this.skin.load(Gdx.files.internal("after_screens/after.json"));

        this.gameOverImage = new Image(new Texture("after_screens/game_over.png"));

        initButtons();

        table.add(gameOverImage);
        table.getCell(gameOverImage).padBottom(20);
        table.row();
        table.add(restartButton);
        table.row();
        table.add(exitButton);
        stage.addActor(backGround);
        stage.addActor(table);
    }

    private void initButtons() {
        restartButton = new TextButton("RESTART", skin, "default");
        exitButton = new TextButton("EXIT", skin, "default");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act(delta);
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
        GameSingleton.getInstance().restoreOldInputProcessor();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
