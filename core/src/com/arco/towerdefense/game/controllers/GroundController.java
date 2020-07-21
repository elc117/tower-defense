
package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.drawer.GroundDrawer;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.path.Path;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class GroundController extends InputAdapter {
    private GroundDrawer groundDrawer;
    private ArrayList<TowerEntity> towers;
    private Path path;

    private Rectangle viewRectangle;

    private boolean hasSelectedTower;
    private TowerEntity towerEntityHolder;

    private WaveController currentWave;

    public GroundController(SpriteBatch batch,  int gridBlockSize, int viewWidth, int viewHeight) {

        path = new Path();
        path.setCheckPoints();

        viewRectangle = new Rectangle(0, 0, viewWidth, viewHeight);
        groundDrawer = new GroundDrawer(batch, gridBlockSize, viewRectangle, path.getLanes());

        towers = new ArrayList<>();

        towerEntityHolder = null;
        hasSelectedTower = false;

        currentWave = null;
    }

    private boolean existsTowerAt(float x, float y) {
        for (TowerEntity tower: towers) {
            if (tower.getX() == x && tower.getY() == y) return true;
        }

        return false;
    }

    private void addTower(int x, int y) {
        if (towerEntityHolder == null) return;

        towerEntityHolder.setX(x);
        towerEntityHolder.setY(y);

        towers.add(towerEntityHolder);

        groundDrawer.removeScheduleOfGroundSelection();
        hasSelectedTower = false;
        towerEntityHolder = null;
    }

    //update call in game screen (call all the update methods to run the game)
    public void update(float delta) {
        groundDrawer.drawGround();
        updateTowers(delta);
        updateWaves(delta);
        groundDrawer.drawTowers(towers);
        groundDrawer.drawEnemies(currentWave.getEnemies());
        groundDrawer.drawScheduledItems();
        drawSelectedTowerUnderCursor();
    }

    //update tower and bullets movements
    private void updateTowers(float delta) {
        for(TowerEntity tower : towers) {
            tower.update(delta);
        }
    }

    private void updateWaves(float delta) {
        if(currentWave == null)
            newWave();

        if (!currentWave.isCompleted())
            currentWave.update(delta);
        else {
            if(currentWave.getWaveNumber() < 4)
                newWave();
        }
    }

    private void newWave() {
        if(currentWave == null) {
            currentWave = new WaveController(1, 2f, 4, path.getCheckPoints());
            return;
        }

        int waveNumber = currentWave.getWaveNumber()+1;
        float timeBetweenEnemies = currentWave.getEnemiesPerWave() - 1f;
        int enemiesPerWave = currentWave.getEnemiesPerWave() + 1;

        currentWave = new WaveController(waveNumber, timeBetweenEnemies, enemiesPerWave, path.getCheckPoints());
        System.out.println("COMECANDO UMA NOVA WAVE :O, WAVE NUMERO: " + currentWave.getWaveNumber());
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = Consts.V_HEIGHT - screenY;
        if (Utils.isInsideRectangle(viewRectangle, screenX, screenY) && button == Input.Buttons.LEFT) {
            int gridX = screenX / groundDrawer.getScale();
            int gridY = screenY / groundDrawer.getScale();

            if (!existsTowerAt(gridX, gridY)) {
                addTower(gridX, gridY);
            }

            return true;
        }

        return false; // Meaning that we have not handled the touch
    }

    public void setHasSelectedTower(boolean hasSelectedTower) {
        this.hasSelectedTower = hasSelectedTower;
    }

    public void setTowerEntityHolder(TowerEntity towerEntity) {
        this.towerEntityHolder = towerEntity;

    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        screenY = Consts.V_HEIGHT - screenY;

        if (Utils.isInsideRectangle(viewRectangle, screenX, screenY) && hasSelectedTower) {
            int gridX = screenX / groundDrawer.getScale();
            int gridY = screenY / groundDrawer.getScale();

            groundDrawer.setPositionSelectedTower(screenX, screenY);
            groundDrawer.scheduleDrawGroundSelectionAt(gridX , gridY);

            return true;
        }

        return false;
    }


    public void setSelectedTowerSprite(Texture texture) {
        groundDrawer.setSelectedTowerSprite(texture);
    }

    private void drawSelectedTowerUnderCursor() {
        if (hasSelectedTower) {
            groundDrawer.drawSelectedTowerUnderCursor();
        }
    }

    public void dispose() {
        this.groundDrawer.dispose();
    }
}