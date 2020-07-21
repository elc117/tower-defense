package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class WaveManager {
    private float timeSinceLastWave;
    private float timeBetweenEnemies;
    private int waveNumber;
    private int enemiesPerWave;
    private Wave currentWave;
    private ArrayList<Vector2> checkPoints;
    //private EnemyEntity[] enemyTypes;

    public WaveManager(ArrayList<Vector2> checkPoints) {
        timeSinceLastWave = 0;
        timeBetweenEnemies = 2f;
        waveNumber = 0;
        enemiesPerWave = 4;
        currentWave = null;
        this.checkPoints = checkPoints;

        newWave();
    }

    public ArrayList<EnemyEntity> getEnemiesList() {
        return currentWave.getEnemies();
    }

    public void update(float delta) {
        if (!currentWave.isCompleted())
            currentWave.update(delta);
        else {
            waveNumber++;
            timeBetweenEnemies -= 0.5f;
            enemiesPerWave ++;
            if(waveNumber < 4)
                newWave();
        }
    }

    private void newWave() {
        currentWave = new Wave(timeBetweenEnemies, enemiesPerWave, checkPoints);
        System.out.println("COMECANDO UMA NOVA WAVE :O, WAVE NUMERO: " + waveNumber);
    }

}
