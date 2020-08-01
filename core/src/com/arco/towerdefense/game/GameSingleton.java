package com.arco.towerdefense.game;

import com.arco.towerdefense.game.controllers.SoundController;
import com.arco.towerdefense.game.factories.EnemyFactory;
import com.arco.towerdefense.game.factories.LevelGenerator;
import com.arco.towerdefense.game.factories.TowerFactory;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class GameSingleton {
    private static GameSingleton instance = null;

    private Vector2 cursorLocation;
    private AssetManager assetManager;
    private InputProcessor inputProcessor;
    public SoundController soundController;
    private TowerFactory towerFactory;
    private LevelGenerator levelGenerator;
    private EnemyFactory enemyFactory;
    private int groundScale;


    private GameSingleton() {
        assetManager = new AssetManager();
        cursorLocation = new Vector2(0, 0);
        soundController = new SoundController();
        towerFactory = new TowerFactory();
        levelGenerator = new LevelGenerator();
        enemyFactory = new EnemyFactory();
        groundScale = 1; // Default


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
        assetManager.load(Consts.GROUND_VEINS, Texture.class);
        assetManager.load(Consts.BADLOGIC, Texture.class);
        assetManager.load(Consts.MENU_BACKGROUND, Texture.class);

        assetManager.load(Consts.BACTERIA_ENEMY, Texture.class);
        assetManager.load(Consts.FUNGUS_ENEMY, Texture.class);
        assetManager.load(Consts.VIRUS_ENEMY, Texture.class);

        assetManager.load(Consts.TOWER1, Texture.class);
        assetManager.load(Consts.TOWER2, Texture.class);
        assetManager.load(Consts.TOWER3, Texture.class);
        assetManager.load(Consts.TOWER4, Texture.class);


        assetManager.finishLoading(); // Load all queued assets
    }

    public Texture getTexture(String internalPath) {
        return assetManager.get(internalPath, Texture.class);
    }

    public TextureAtlas getTextureAtlas(String internalPath) {
        if (!assetManager.isLoaded(internalPath)) {
            assetManager.load(internalPath, TextureAtlas.class);
            assetManager.finishLoading();
        }

        return assetManager.get(internalPath, TextureAtlas.class);
    }

    public Vector2 getCursorLocation() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = Consts.V_HEIGHT - Gdx.input.getY();

        return cursorLocation;
    }

    public TowerFactory getTowerFactory() {
        return towerFactory;
    }

    public EnemyFactory getEnemyFactory() {
        return enemyFactory;
    }

    public LevelGenerator getLevelGenerator() {
        return levelGenerator;
    }

    public AssetManager getAssetManager() { return assetManager; }

    public int getGroundScale() {
        return groundScale;
    }

    public void setGroundScale(int groundScale) {
        this.groundScale = groundScale;
    }

    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public void dispose() {
        assetManager.clear();
    }

    public void saveCurrentInputProcessor() {
        this.setInputProcessor(Gdx.input.getInputProcessor());
    }

    public void restoreOldInputProcessor() {
        Gdx.input.setInputProcessor(getInputProcessor());
    }

    public int initGroundScale(int gridBlockSize) {
//        int groundSize = GameSingleton.getInstance().getTexture(Consts.GROUND_DIRT).getHeight();

//        setGroundScale(gridBlockSize*groundSize);
        setGroundScale(32);

        return getGroundScale();
    }
}