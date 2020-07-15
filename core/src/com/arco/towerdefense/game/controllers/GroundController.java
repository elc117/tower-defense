
package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.drawer.GroundDrawer;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.entities.Wave;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GroundController {
    private GroundDrawer groundDrawer;
    private Vector2 cursorLocation;
    private ArrayList<TowerEntity> towers;
    private Wave wave;

    private Rectangle viewRectangle;

    //constructor
    public GroundController(SpriteBatch batch,  int gridBlockSize, int viewWidth, int viewHeight) {
        viewRectangle = new Rectangle(0, 0, viewWidth, viewHeight);
        groundDrawer = new GroundDrawer(batch, gridBlockSize, viewRectangle);
        cursorLocation = new Vector2(0, 0);

        towers = new ArrayList<>();

        EnemyEntity enemy = new EnemyEntity(5, 5);
        wave = new Wave(1f, enemy, 5);
    }

    //add tower to tower list
    private void addTower(int x, int y) {
        towers.add(new TowerEntity(x, y));
    }

    //update call in game screen (call all the update methods to run the game)
    public void update(float delta) {
        groundDrawer.drawGround();
        handleCursor();
        updateTowers(delta);
        wave.update(delta);
        groundDrawer.drawTowers(towers);
        groundDrawer.drawEnemies(wave.getEnemies());
    }

    //cursor location to set towers
    private void handleCursor() {
        updateCursor();

        if (viewRectangle.contains(cursorLocation)) {
            int gridX = (int) Math.ceil(cursorLocation.x / groundDrawer.getScale());
            int gridY = (int) Math.ceil(cursorLocation.y / groundDrawer.getScale());

            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                addTower(gridX - 1, gridY - 1);
            }

            groundDrawer.drawGroundSelection(gridX - 1 , gridY - 1);
        }
    }

    //update cursor location
    private void updateCursor() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = Consts.V_HEIGHT - Gdx.input.getY();
    }

    //update tower and bullets movements
    private void updateTowers(float delta) {
        for(TowerEntity tower : towers) {
            tower.update(delta);
        }
    }

    //dispose game drawer
    public void dispose() {
        this.groundDrawer.dispose();
    }


}