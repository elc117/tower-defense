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
	public GameScreen gameScreen;
	public HelpScreen helpScreen;
	public LevelSelectScreen levelSelectScreen;
	public MainMenuScreen mainMenuScreen;
	public GameOverScreen gameOverScreen;
	public WinScreen winScreen;
	public FinalGameScreen finalGameScreen;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Consts.V_WIDTH, Consts.V_HEIGHT);
		batch = new SpriteBatch();

		initScreens();

		this.setScreen(mainMenuScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		introScreen.dispose();
		helpScreen.dispose();
		levelSelectScreen.dispose();
		mainMenuScreen.dispose();
		gameOverScreen.dispose();
		winScreen.dispose();
		finalGameScreen.dispose();
		GameSingleton.getInstance().dispose();
		if(gameScreen != null)
			gameScreen.dispose();
	}

	public void initScreens() {
		introScreen = new IntroScreen(this);
		gameScreen = null;
		helpScreen = new HelpScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		gameOverScreen = new GameOverScreen(this);
		winScreen = new WinScreen(this);
		finalGameScreen = new FinalGameScreen(this);
	}
}
