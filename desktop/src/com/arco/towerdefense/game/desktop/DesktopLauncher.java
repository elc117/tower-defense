package com.arco.towerdefense.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arco.towerdefense.game.TowerDefenseGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Tower Defense";
		config.width = 800;
		config.height = 480;

		new LwjglApplication(new TowerDefenseGame(), config);
	}
}
