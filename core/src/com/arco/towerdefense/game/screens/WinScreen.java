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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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

    public WinScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        this.stage.clear();

        this.backGround = new Image(new Texture("after_screens/winScreenBackGround.jpg"));
        backGround.setBounds(0, 0, Consts.V_WIDTH, Consts.V_HEIGHT);

        this.table = new Table();
        this.table.setFillParent(true);
        this.table.center();

        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("after_screens/after.atlas"));
        this.skin.load(Gdx.files.internal("after_screens/after.json"));

        this.victoryImage = new Image(new Texture("after_screens/victory.png"));
        this.trophyImage = new Image(new Texture("after_screens/trophy.png"));

        this.winMusic = Gdx.audio.newMusic(Gdx.files.internal("after_screens/winning.mp3"));
        //winSound.play();

        initButtons();

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

    private void initButtons() {
        continueButton = new TextButton("CONTINUE", skin, "default");
        restartButton = new TextButton("RESTART", skin, "default");
        exitButton = new TextButton("EXIT", skin, "default");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        winMusic.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        winMusic.dispose();
    }
}
