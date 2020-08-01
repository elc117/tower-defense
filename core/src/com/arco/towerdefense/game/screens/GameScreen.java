package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.controllers.GroundController;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.controllers.HudController;
import com.arco.towerdefense.game.controllers.InteractionZoneController;
import com.arco.towerdefense.game.controllers.LevelController;
import com.arco.towerdefense.game.factories.LevelGenerator;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.sun.org.glassfish.gmbal.GmbalException;

public class GameScreen implements Screen {
    final TowerDefenseGame game;
    private GroundController groundController;
    private InteractionZoneController interactionZoneController;
    private Texture homeButton;
    private LevelController levelController;
    private int level;
    private HudController hudController;
    private GameSingleton gameSingleton;

    public GameScreen(TowerDefenseGame game, int level) {
        this.game = game;
        this.gameSingleton = GameSingleton.getInstance();
        this.level = level;

        int GRID_BLOCK_SIZE = 2;

        gameSingleton.initGroundScale(GRID_BLOCK_SIZE);

        this.levelController = GameSingleton.getInstance().getLevelGenerator().createById(level);
        this.groundController = new GroundController(game.batch, GRID_BLOCK_SIZE, Consts.V_WIDTH, Consts.V_HEIGHT, levelController, game.camera);
        this.hudController = new HudController(game.batch, groundController, game.camera);
        //interactionZoneController = new InteractionZoneController(game.batch, groundController);

        homeButton = GameSingleton.getInstance().getTexture(Consts.HOME_BUTTON);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        groundController.update(delta);
            //interactionZoneController.update();
            //homeButtonUpdate();
        game.batch.end();

        if(GameSingleton.getInstance().isGameOver()) {
            game.setScreen(game.gameOverScreen);
        }

        hudController.update(delta, groundController.getCurrentWaveId(), gameSingleton.getMoney(), gameSingleton.getHearts());
    }

    public void homeButtonUpdate() {
        game.batch.draw(homeButton, Consts.V_WIDTH - 32, Consts.V_HEIGHT - 32, 32, 32);

        if(Utils.isCursorInside(Consts.V_WIDTH - 32, Consts.V_HEIGHT - 32, 32, 32)) {
            if (Gdx.input.isTouched()) {
                game.setScreen(game.menuScreen);
            }
        }
    }

    @Override
    public void dispose() {
        groundController.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        //multiplexer.addProcessor(interactionZoneController);
        multiplexer.addProcessor(hudController.getStage());
        groundController.selfIncludeToMultiplexer(multiplexer);
        GameSingleton.getInstance().saveCurrentInputProcessor();
        Gdx.input.setInputProcessor(multiplexer);
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
}
