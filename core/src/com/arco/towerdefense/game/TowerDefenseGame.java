package com.arco.towerdefense.game;

import com.arco.towerdefense.game.screens.*;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerDefenseGame extends Game {
	public OrthographicCamera camera;
	public SpriteBatch batch;

	public IntroScreen introScreen;
	public MenuScreen menuScreen;
	public GameScreen gameScreen;
	public HelpScreen helpScreen;
	public LevelSelectScreen levelSelectScreen;
	public MainMenuScreen mainMenuScreen;
	public GameOverScreen gameOverScreen;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Consts.V_WIDTH, Consts.V_HEIGHT);
		batch = new SpriteBatch();

		introScreen = new IntroScreen(this);
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this, 1);
		helpScreen = new HelpScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		gameOverScreen = new GameOverScreen(this);

		this.setScreen(new WinScreen(this));
	}

	@Override
	public void render () {
		super.render();

		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		introScreen.dispose();
		menuScreen.dispose();
		gameScreen.dispose();
		helpScreen.dispose();
		levelSelectScreen.dispose();
		mainMenuScreen.dispose();
		gameOverScreen.dispose();
		GameSingleton.getInstance().dispose();
	}
}
