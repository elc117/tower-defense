package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.TowerDefenseGame;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class HelpScreen implements Screen {
    TowerDefenseGame game;
    BitmapFont font;
    BitmapFont sinopse;
    Image backGround;
    Stage stage;
    private Skin skin;
    Table table;
    TextButton home;

    public HelpScreen(final TowerDefenseGame game) {
        this.game = game;

        font = new BitmapFont();
        sinopse = new BitmapFont();

        this.stage = new Stage(new StretchViewport(Consts.V_WIDTH, Consts.V_HEIGHT, game.camera));
        stage.clear();

        this.skin = GameSingleton.getInstance().getSkin();

        this.table = new Table();
        table.setFillParent(true);
        table.center();

        this.backGround = new Image(new Texture("menu/menu-background.png"));
        backGround.setBounds(0, 0, Consts.V_WIDTH, Consts.V_HEIGHT);

        stage.addActor(backGround);

        home = new TextButton("homeBtn", skin, "default");
        home.addAction(sequence( alpha(0), fadeIn(.5f)));

        home.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSingleton.getInstance().getConfirmSound().play();
                game.setScreen(game.mainMenuScreen);
            }
        });


        table.padTop(50);
        table.add(home);
        table.row();
        stage.addActor(table);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        game.batch.begin();
            gameInstructions();
        game.batch.end();
    }

    private void gameInstructions() {
        String text = "O Jogo consiste em uma batalha entre virus e remédios dentro de seu corpo, no formato\n" +
                "tower defense, utilize os remédios para combater virus, bacterias e fungos para que não sofra\n" +
                "com doenças, analise bem as especificidades de cada remédio, eles se comportam de modo diferente";
        font.draw(game.batch, "BEM VINDO AO PANDEMIC 101", 100, 400);
        sinopse.draw(game.batch, text, 100, 350);
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
        GameSingleton.getInstance().restoreOldInputProcessor();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
