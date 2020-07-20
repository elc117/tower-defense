package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Wave {
    private float timeSinceLastSpawn;
    private float timeBetweenEnemies;
    private int enemiesPerWave;
    private int enemiesPerWaveCounter;
    private ArrayList<EnemyEntity> enemies;
    private ArrayList<Vector2> checkPoints;
    private boolean waveCompleted;
    private boolean first;
    //private EnemyEntity enemy;

    public Wave(float timeBetweenEnemies, int enemiesPerWave, ArrayList<Vector2> checkPoints) {
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.checkPoints = checkPoints;

        timeSinceLastSpawn = 0;
        enemiesPerWaveCounter = 0;
        enemies = new ArrayList<>();
        waveCompleted = false;
        first = true;
    }

    public ArrayList<EnemyEntity> getEnemies() {
        return enemies;
    }

    private void spawn() {
        timeSinceLastSpawn = 0;
        enemiesPerWaveCounter++;
        addEnemy();
    }

    private void addEnemy() {
        Vector2 startCheckPoint = Utils.returnFirstV2FromList(checkPoints);
        Vector2 nextCheckPoint = Utils.returnNextV2FromList(checkPoints, startCheckPoint);
        Vector2 finalCheckPoint = Utils.returnLastV2FromList(checkPoints);
        EnemyEntity enemyEntity = new EnemyEntity(startCheckPoint, nextCheckPoint, finalCheckPoint);
        enemies.add(enemyEntity);
        first = false;
    }

    public void update(float delta) {
        if(enemiesPerWaveCounter < enemiesPerWave) {
            timeSinceLastSpawn += delta;
            if(timeSinceLastSpawn > timeBetweenEnemies) {
                spawn();
            }
        }

        updateEnemies(delta);
    }

    private void updateEnemies(float delta) {
        ArrayList<EnemyEntity> enemiesToRemove = new ArrayList<>();
        boolean allEnemiesDead = true;

        for(EnemyEntity enemy : enemies) {
            if (enemy.isCheckPoint()) {
                if (enemy.isFinalCheckPoint()) {
                    enemy.alive = false;
                } else {
                    Vector2 next = Utils.returnNextV2FromList(checkPoints, enemy.getNextCheckPoint());
                    enemy.setNextCheckPoint(next);
                    enemy.selectDirection();
                }
            }

            if (enemy.isAlive()) {
                allEnemiesDead = false;
            } else {
                enemiesToRemove.add(enemy);
            }

            enemy.update(delta);
        }
        enemies.removeAll(enemiesToRemove);
        if(allEnemiesDead && !first)
           waveCompleted = true;
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

}
