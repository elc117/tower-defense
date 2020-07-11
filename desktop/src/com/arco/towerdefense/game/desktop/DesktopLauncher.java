package com.arco.towerdefense.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arco.towerdefense.game.TowerDefenseGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = TowerDefenseGame.TITLE;
		config.width = TowerDefenseGame.V_WIDTH;
		config.height = TowerDefenseGame.V_HEIGHT;
		//config.resizable = true;

		new LwjglApplication(new TowerDefenseGame(), config);
	}
}
