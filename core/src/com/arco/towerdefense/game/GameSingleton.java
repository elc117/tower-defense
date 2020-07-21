package com.arco.towerdefense.game;

import com.arco.towerdefense.game.factories.TowerFactory;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Music;

public class GameSingleton {
    private static GameSingleton instance = null;

    private Vector2 cursorLocation;
    public AssetManager assetManager;
    private InputProcessor inputProcessor;
    private TowerFactory towerFactory;

    //Volume settings
    float effectsVolume;
    float musicVolume;

    private GameSingleton() {
        assetManager = new AssetManager();
        cursorLocation = new Vector2(0, 0);
        towerFactory = new TowerFactory();

        setVolume();
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
        assetManager.load(Consts.TOWER_GLOBULO_BRANCO2, Texture.class);
        assetManager.load(Consts.TOWER_GLOBULO_BRANCO_SELECTION, Texture.class);
        assetManager.load(Consts.TOWER_GLOBULO_BRANCO_SELECTION2, Texture.class);
        assetManager.load(Consts.HOME_BUTTON, Texture.class);
        assetManager.load(Consts.CONFIG_BUTTON, Texture.class);
        assetManager.load(Consts.HIGH_CONFIG_BUTTON, Texture.class);
        assetManager.load(Consts.PLAY_BUTTON, Texture.class);
        assetManager.load(Consts.QUIT_BUTTON, Texture.class);
        assetManager.load(Consts.HELP_BUTTON, Texture.class);
        assetManager.load(Consts.GROUND_GRASS, Texture.class);
        assetManager.load(Consts.GROUND_DIRT, Texture.class);
        assetManager.load(Consts.ENEMY, Texture.class);

        assetManager.finishLoading(); // Load all queued assets
    }

    public Texture getTexture(String internalPath) {
        return assetManager.get(internalPath, Texture.class);
    }

    public Vector2 getCursorLocation() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = Consts.V_HEIGHT - Gdx.input.getY();

        return cursorLocation;
    }


    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public float getEffectsVolume() {
        return effectsVolume;
    }

    private void setEffectsVolume(float volume) {
        this.effectsVolume = volume;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    private void setMusicVolume(float volume) {
        this.musicVolume = volume;
    }

    public void turnUpVolume(Music music){
        GameSingleton.getInstance().setMusicVolume(GameSingleton.getInstance().getMusicVolume() + 0.1f);
        music.setVolume(GameSingleton.getInstance().getMusicVolume());
    }

    public void turnDownVolume(Music music){
        GameSingleton.getInstance().setMusicVolume(GameSingleton.getInstance().getMusicVolume() - 0.1f);
        music.setVolume(GameSingleton.getInstance().getMusicVolume());
    }

    private void setVolume() {
        setEffectsVolume(0.5f);
        setMusicVolume(0.5f);
    }


    public TowerFactory getTowerFactory() {
        return towerFactory;
    }

    public void dispose() {
        assetManager.clear();
    }
}
