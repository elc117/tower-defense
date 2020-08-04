package com.arco.towerdefense.game;

import com.arco.towerdefense.game.controllers.SoundController;
import com.arco.towerdefense.game.factories.EnemyFactory;
import com.arco.towerdefense.game.factories.LevelGenerator;
import com.arco.towerdefense.game.factories.TowerFactory;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameSingleton {
    private static GameSingleton instance = null;

    private Vector2 cursorLocation;
    private AssetManager assetManager;
    private InputProcessor inputProcessor;
    public SoundController soundController;
    private TowerFactory towerFactory;
    private LevelGenerator levelGenerator;
    private EnemyFactory enemyFactory;
    private Skin skin;
    private Sound confirmSound;
    private int groundScale;

    private int hearts;
    private int money;


    private GameSingleton() {
        assetManager = new AssetManager();
        cursorLocation = new Vector2(0, 0);
        soundController = new SoundController();
        towerFactory = new TowerFactory();
        levelGenerator = new LevelGenerator();
        enemyFactory = new EnemyFactory();
        groundScale = 1; // Default
        hearts = 0;
        money = 0;
        confirmSound = Gdx.audio.newSound(Gdx.files.internal("buttons/confirmation.wav"));

        initSkin();
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
        assetManager.load(Consts.GROUND_LANE, Texture.class);
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

    private void initSkin() {
        this.skin = new Skin();
        this.skin.addRegions(getTextureAtlas("buttons/buttons.atlas"));
        this.skin.load(Gdx.files.internal("buttons/buttons.json"));
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

    public Skin getSkin() {
        return skin;
    }

    public Sound getConfirmSound() { return confirmSound; }

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

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public boolean decreaseHeartsBy(int val) {
        int result = hearts - val;

        if (result < 0) return false;

        this.hearts = result;

        return true;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void increaseMoneyBy(int val) {
        this.money += val;
    }

    // Return false if it was not possible to decrease money and true otherwise
    public boolean decreaseMoneyBy(int val) {
        int result = money - val;

        if (result < 0) return false;

        this.money = result;

        return true;
    }

    public boolean isGameOver() {
        return hearts == 0;
    }


}
