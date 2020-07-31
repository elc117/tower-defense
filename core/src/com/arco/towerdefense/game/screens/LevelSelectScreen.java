package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class LevelSelectScreen implements Screen {

    final TowerDefenseGame game;
    BitmapFont testFont;
    private Stage stage;
    private Skin skin;
    private Button btnLvl1, btnLvl2, btnLvl3, btnLvl4, btnLvl5;
    private Table table;

    public LevelSelectScreen(TowerDefenseGame game) {
        this.game = game;

        testFont = new BitmapFont();

        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        this.stage.clear();
        Gdx.input.setInputProcessor(this.stage);

        this.skin = new Skin();
        this.skin.addRegions(GameSingleton.getInstance().getTextureAtlas("levels/btnLevels.atlas"));
        this.skin.load(Gdx.files.internal("levels/btnLevels.json"));

        this.table = new Table(skin);

        initButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
            testFont.draw(game.batch, "CLICA AI NO BAGUI PRA JOGAR O JOGO :", 300,350 );
        game.batch.end();
        update(delta);
        this.stage.draw();
    }

    private void initButtons() {
        btnLvl1 = new Button(skin, "btnLvlOne");
        btnLvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.gameScreen = new GameScreen(game, 1));
            }
        });
        btnLvl1.addAction(sequence( alpha(0), fadeIn(.5f)));


        btnLvl2 = new Button(skin, "btnLvlTwo");
        btnLvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.gameScreen = new GameScreen(game, 2));
            }
        });
        btnLvl2.addAction(sequence( alpha(0), fadeIn(.5f)));


        btnLvl3 = new Button(skin, "btnLvlThr");
        btnLvl3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.gameScreen = new GameScreen(game, 3));
            }
        });
        btnLvl3.addAction(sequence( alpha(0), fadeIn(.5f)));


        btnLvl4 = new Button(skin, "btnLvlFou");
        btnLvl4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.gameScreen = new GameScreen(game, 4));
            }
        });
        btnLvl4.addAction(sequence( alpha(0), fadeIn(.5f)));


        btnLvl5 = new Button(skin, "btnLvlFiv");
        btnLvl5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(game.gameScreen = new GameScreen(game, 5));
            }
        });
        btnLvl5.addAction(sequence( alpha(0), fadeIn(.5f)));

        this.table.setBounds(0, -25, stage.getWidth(), stage.getHeight());

        this.table.add(btnLvl1);
        this.table.add(btnLvl2).padLeft(20);
        this.table.add(btnLvl3).padLeft(20);
        this.table.add(btnLvl4).padLeft(20);
        this.table.add(btnLvl5).padLeft(20);

        this.stage.addActor(this.table);
    }

    private void update(float delta) {
        this.stage.act(delta);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}
