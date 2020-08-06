package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;


public class FinalGameScreen implements Screen {
    final TowerDefenseGame game;
    private Stage stage;
    private Table table;
    private Image developersImage;
    private Label label;
    private Label labelNames;
    private Music finalMusic;

    public FinalGameScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        this.stage.clear();
        this.finalMusic = Gdx.audio.newMusic(Gdx.files.internal("final/finalMusic.mp3"));
        this.initActors();
        this.composeScene();
        this.initActions();
    }

    private void initActors() {
        this.table = new Table();
        this.table.setFillParent(true);
        this.developersImage = new Image(new Texture("final/developers.jpeg"));

        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
        this.label = new Label("OBRIGADO POR JOGAR! :) ", labelStyle);
        this.labelNames = new Label("DEVELOPERS: DANIEL SANTOS, GUSTAVO REIS E JOAO FRITSCH", labelStyle);
    }

    private void composeScene() {
        table.center();
        table.add(label).padBottom(30);
        table.row();
        table.add(developersImage);
        table.row();
        table.add(labelNames).pad(50);
        stage.addActor(table);
    }

    private void initActions() {
        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                Gdx.app.exit();
            }
        };
        
        label.addAction(sequence( alpha(0), fadeIn(20f), delay(20f), fadeOut(20f)));
        labelNames.addAction(sequence( alpha(0), fadeIn(20f), delay(20f), fadeOut(20f)));
        developersImage.addAction(sequence(alpha(0), fadeIn(20f), delay(20f), fadeOut(20f), run(transitionRunnable)));
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
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
        finalMusic.play();
    }

    @Override
    public void hide() {
        GameSingleton.getInstance().restoreOldInputProcessor();
    }

    @Override
    public void dispose() {
        stage.dispose();
        finalMusic.dispose();
    }
}
