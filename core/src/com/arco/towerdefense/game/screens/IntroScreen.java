package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class IntroScreen implements Screen {
    final TowerDefenseGame game;
    private Stage stage;
    private Image ufsmLogo;

    public IntroScreen(TowerDefenseGame game) {
        this.game = game;
        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        stage.clear();

        this.ufsmLogo = new Image(new Texture(Gdx.files.internal("intro/ufsm.png")));
        this.ufsmLogo.setOrigin(ufsmLogo.getWidth() / 2, ufsmLogo.getHeight() / 2);

        stage.addActor(ufsmLogo);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void initScene() {
        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                game.setScreen(game.mainMenuScreen);
            }
        };

        ufsmLogo.addAction(sequence( alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(2f, 2f, 2.5f, Interpolation.pow5),
                        moveTo(stage.getWidth() / 2 - 100, stage.getHeight() / 2 - 100, 2f, Interpolation.swing)),
                delay(1.5f), fadeOut(1.25f), run(transitionRunnable)));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        initScene();
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
