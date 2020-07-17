
package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.drawer.GroundDrawer;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.entities.Wave;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Path;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GroundController extends InputAdapter {
    private GroundDrawer groundDrawer;
    private ArrayList<TowerEntity> towers;
    private ArrayList<EnemyEntity> enemies;
    private ArrayList<Vector2> checkPoints;
    private Path path;

    private Rectangle viewRectangle;

    //constructor
    public GroundController(SpriteBatch batch,  int gridBlockSize, int viewWidth, int viewHeight) {

        path = new Path();
        path.setCheckPoints();

        viewRectangle = new Rectangle(0, 0, viewWidth, viewHeight);
        groundDrawer = new GroundDrawer(batch, gridBlockSize, viewRectangle, path.getLanes());

        towers = new ArrayList<>();
        enemies = new ArrayList<>();

        Vector2 startCheckPoint = path.returnStartCheckPoint();
        Vector2 nextCheckPoint = path.returnNextCheckPoint(startCheckPoint);
        Vector2 finalCheckPoint = path.returnFinalCheckPoint();

        enemies.add(new EnemyEntity(startCheckPoint, nextCheckPoint, finalCheckPoint));
    }

    //add tower to tower list
    private void addTower(int x, int y) {
        towers.add(new TowerEntity(x, y));
    }

    //update call in game screen (call all the update methods to run the game)
    public void update(float delta) {
        groundDrawer.drawGround();
        updateTowers(delta);
        updateEnemies(delta);
        groundDrawer.drawTowers(towers);
        groundDrawer.drawEnemies(enemies);
        groundDrawer.drawScheduledItems();
    }

    //update tower and bullets movements
    private void updateTowers(float delta) {
        for(TowerEntity tower : towers) {
            tower.update(delta);
        }
    }

    private void updateEnemies(float delta) {
        ArrayList<EnemyEntity> enemiesToRemove = new ArrayList<>();
        for(EnemyEntity enemy : enemies) {
            System.out.println("X POSITION: " + (int) enemy.getX() + "  Y POSITION: " + (int) enemy.getY());
            //System.out.println(enemy.getNextCheckPoint().x + "  " + enemy.getNextCheckPoint().y );
            if(enemy.isCheckPoint()) {
                System.out.println("entrei ak");
                if(enemy.isFinalCheckPoint()) {
                    enemy.remove = true;
                } else {
                    Vector2 aux = path.returnNextCheckPoint((enemy.getNextCheckPoint()));
                    enemy.setNextCheckPoint(aux);
                    enemy.selectDirection();
                }
            }

            if(enemy.remove) {
                enemiesToRemove.add(enemy);
            }

            enemy.update(delta);
        }
        enemies.removeAll(enemiesToRemove);
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

            //addTower(gridX, gridY);

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