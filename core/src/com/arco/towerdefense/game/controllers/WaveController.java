package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class WaveController {

    private int currentWaveNumber;
    private float timeSinceLastSpawn;
    private float timeBetweenEnemies;
    private int enemiesPerWave;
    private int enemiesPerWaveCounter;
    private ArrayList<EnemyEntity> enemies;
    private ArrayList<Vector2> checkPoints;
    private boolean waveCompleted;
    private boolean first;
    private int[] enemyTypes;

    public WaveController(int currentWaveNumber, float timeBetweenEnemies, int enemiesPerWave, ArrayList<Vector2> checkPoints, int[] enemyTypes) {
        this.timeBetweenEnemies = timeBetweenEnemies;
        this.enemiesPerWave = enemiesPerWave;
        this.checkPoints = checkPoints;
        this.currentWaveNumber = currentWaveNumber;

        timeSinceLastSpawn = 0;
        enemiesPerWaveCounter = 0;
        enemies = new ArrayList<>();
        waveCompleted = false;
        first = true;

        initTypes(enemyTypes);
    }

    private void initTypes(int[] enemyTypes) {

        this.enemyTypes = new int[enemyTypes.length];

        for(int i = 0; i < enemyTypes.length; i++) {
            this.enemyTypes[i] = enemyTypes[i];
        }
    }

    private int selectTypeToSpawn() {
        Random random = new Random();

        int type = random.nextInt(2);

        if(enemyTypes[type] == 0) {
            if(type == 0)
                type = 1;
            else
                type = 0;
        }

        enemyTypes[type]--;
        return type + 1;
    }

    private void addEnemy() {
        Vector2 startCheckPoint = Utils.returnFirstV2FromList(checkPoints);
        Vector2 nextCheckPoint = Utils.returnNextV2FromList(checkPoints, startCheckPoint);

        Random random = new Random();
        int type = random.nextInt(2);

        EnemyEntity enemyEntity = GameSingleton.getInstance().getEnemyFactory().createById(1);
        enemyEntity.setNextCheckPoint(nextCheckPoint);
        enemyEntity.setX(startCheckPoint.x);
        enemyEntity.setY(startCheckPoint.y);
        enemyEntity.selectDirection();
        enemies.add(enemyEntity);
        first = false;
    }

    private void spawn() {
        timeSinceLastSpawn = 0;
        enemiesPerWaveCounter++;
        addEnemy();
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
            //System.out.println("ID : " + enemy.getTargetID());
            if (enemy.isCheckPoint()) {
                if (isFinalCheckPoint(enemy.getNextCheckPoint())) {
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

    public boolean isFinalCheckPoint(Vector2 nextCheckPoint) {
        return nextCheckPoint == Utils.returnLastV2FromList(checkPoints);
    }

    public boolean isCompleted() {
        return waveCompleted;
    }

    public int getCurrentWaveNumber() {
        return currentWaveNumber;
    }

    public int getEnemiesPerWave() {
        return enemiesPerWave;
    }

    public ArrayList<EnemyEntity> getEnemies() {
        return enemies;
    }

    public float getTimeBetweenEnemies() {
        return timeBetweenEnemies;
    }
}
