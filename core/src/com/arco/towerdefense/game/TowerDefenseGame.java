package com.arco.towerdefense.game;

import com.arco.towerdefense.game.screens.GameScreen;
import com.arco.towerdefense.game.screens.MenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.awt.SunHints;

public class TowerDefenseGame extends Game {
	public static final String TITLE = "Tower Defense";
	public static final int V_WIDTH = 800;
	public static final int V_HEIGHT = 480;

	public OrthographicCamera camera;
	public SpriteBatch batch;

	public MenuScreen menuScreen;
	public GameScreen gameScreen;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, V_WIDTH, V_HEIGHT);
		batch = new SpriteBatch();

		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);

		this.setScreen(menuScreen);
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
		menuScreen.dispose();
		gameScreen.dispose();
	}
}
