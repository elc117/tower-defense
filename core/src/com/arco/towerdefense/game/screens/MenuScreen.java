package com.arco.towerdefense.game.screens;

import com.arco.towerdefense.game.TowerDefenseGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import static com.arco.towerdefense.game.TowerDefenseGame.V_HEIGHT;

public class MenuScreen implements Screen {

    final TowerDefenseGame game;
    Texture playButton;
    Texture playButtonHigh;
    Texture quitButton;
    Texture quitButtonHigh;
    Texture helpButton;
    Texture helpButtonHigh;

    Vector2 cursorLocation = new Vector2(0, 0);

    public MenuScreen(TowerDefenseGame game) {
        this.game = game;
        initButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
            update();
        game.batch.end();

    }

    public void update() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = V_HEIGHT - Gdx.input.getY();

        playButtonUpdate();
        quitButtonUpdate();

    }

    public void playButtonUpdate() {
        if (cursorLocation.x > game.V_WIDTH / 2 - 90 && cursorLocation.x < game.V_WIDTH / 2 + 40 && cursorLocation.y > game.V_HEIGHT / 2 + 60 && cursorLocation.y < game.V_HEIGHT / 2 + 165) {
            game.batch.draw(playButtonHigh, game.V_WIDTH / 2 - playButtonHigh.getWidth() / 8, game.V_HEIGHT / 2, 200, 200);
            if (Gdx.input.isTouched()) {
                game.setScreen(game.gameScreen);
            }
        }
        else {
            game.batch.draw(playButton, game.V_WIDTH / 2 - playButton.getWidth() / 8, game.V_HEIGHT / 2, 200, 200);
        }
    }

    public void quitButtonUpdate() {
        if (cursorLocation.x > game.V_WIDTH / 2 - 90 && cursorLocation.x < game.V_WIDTH / 2 + 40 && cursorLocation.y > game.V_HEIGHT / 2 + 60 - 120 && cursorLocation.y < game.V_HEIGHT / 2 + 165 - 120) {
            game.batch.draw(quitButton, game.V_WIDTH / 2 - quitButton.getWidth() / 8, game.V_HEIGHT / 2 - 120, 200, 200);
            game.batch.draw(quitButtonHigh, game.V_WIDTH / 2 - quitButtonHigh.getWidth() / 8, game.V_HEIGHT / 2 - 120, 200, 200);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quitButton, game.V_WIDTH / 2 - quitButton.getWidth() / 8, game.V_HEIGHT / 2 - 120, 200, 200);
        }
    }

    @Override
    public void dispose() {

    }

    public void initButtons() {
        playButton = new Texture("menu/PlayButton.png");
        playButtonHigh = new Texture("menu/PlayButtonHighlight.png");
        quitButton = new Texture("menu/QuitButton.png");
        quitButtonHigh = new Texture("menu/QuitButtonHighlight.png");
        helpButton = new Texture("menu/HelpButton.png");
        helpButtonHigh = new Texture("menu/HelpButtonHighlight.png");
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
}