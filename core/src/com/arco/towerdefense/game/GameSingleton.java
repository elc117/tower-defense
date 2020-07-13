package com.arco.towerdefense.game;

import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class GameSingleton {
    private static GameSingleton instance = null;

    public AssetManager assetManager;

    private GameSingleton() {
        assetManager = new AssetManager();

        initAssetManager();
    }

    public static GameSingleton getInstance() {
        if (instance == null) {
            instance = new GameSingleton();
        }

        return instance;
    }

    private void initAssetManager() {
        assetManager.load(Consts.TOWER_GLOBULO_BRANCO, Texture.class);
        assetManager.load(Consts.TOWER_GLOBULO_BRANCO_SELECTION, Texture.class);
        assetManager.load(Consts.HOME_BUTTON, Texture.class);
        assetManager.load(Consts.PLAY_BUTTON, Texture.class);
        assetManager.load(Consts.PLAY_BUTTON_HIGHLIGHT, Texture.class);
        assetManager.load(Consts.QUIT_BUTTON, Texture.class);
        assetManager.load(Consts.QUIT_BUTTON_HIGHLIGHT, Texture.class);
        assetManager.load(Consts.HELP_BUTTON, Texture.class);
        assetManager.load(Consts.HELP_BUTTON_HIGHLIGHT, Texture.class);
        assetManager.load(Consts.GROUND_GRASS, Texture.class);
        assetManager.load(Consts.GROUND_DIRT, Texture.class);

        while(!assetManager.update()); // Load all queued assets
    }

    public Texture getTexture(String internalPath) {
        return assetManager.get(internalPath, Texture.class);
    }

    public void dispose() {
        assetManager.clear();
    }
}
