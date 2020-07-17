
package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.drawer.GroundDrawer;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.entities.Wave;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class GroundController extends InputAdapter {
    private GroundDrawer groundDrawer;
    private ArrayList<TowerEntity> towers;
    private Wave wave;

    private Rectangle viewRectangle;

    //constructor
    public GroundController(SpriteBatch batch,  int gridBlockSize, int viewWidth, int viewHeight) {
        viewRectangle = new Rectangle(0, 0, viewWidth, viewHeight);
        groundDrawer = new GroundDrawer(batch, gridBlockSize, viewRectangle);

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
        updateTowers(delta);
        wave.update(delta);
        groundDrawer.drawTowers(towers);
        groundDrawer.drawEnemies(wave.getEnemies());
        groundDrawer.drawScheduledItems();
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

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = Consts.V_HEIGHT - screenY;
        if (Utils.isInsideRectangle(viewRectangle, screenX, screenY) && button == Input.Buttons.LEFT) {
            int gridX = screenX / groundDrawer.getScale();
            int gridY = screenY / groundDrawer.getScale();

            addTower(gridX, gridY);

            return true;
        }

        return false; // Meaning that we have not handled the touch
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        screenY = Consts.V_HEIGHT - screenY;

        if (Utils.isInsideRectangle(viewRectangle, screenX, screenY)) {
            int gridX = screenX / groundDrawer.getScale();
            int gridY = screenY / groundDrawer.getScale();

            groundDrawer.scheduleDrawGroundSelectionAt(gridX , gridY);

            return true;
        }

        return false;
    }
}