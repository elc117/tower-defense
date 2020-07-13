package com.arco.towerdefense.game.desktop;

import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.arco.towerdefense.game.TowerDefenseGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Consts.TITLE;
		config.width = Consts.V_WIDTH;
		config.height = Consts.V_HEIGHT;

		config.resizable = false;
		// Resize should be disabled because if people can resize and we work with the grid system
		// and resizing means creating more space. This means more space for towers and longer lanes.

		new LwjglApplication(new TowerDefenseGame(), config);
	}
}
