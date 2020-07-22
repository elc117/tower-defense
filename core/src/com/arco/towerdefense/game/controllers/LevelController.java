package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.utils.json.LevelJson;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelController {
    private int id;
    private float dificulty;
    private int monstersPerWave;
    private int waveNumber;
    private WaveController currentWave;
    private ArrayList<Vector2> checkPoints;
    private int[] enemyTypes;
    private boolean completed;

    public LevelController(LevelJson levelJson) {
        this.id = levelJson.id;
        this.dificulty = levelJson.dificulty;
        this.monstersPerWave = levelJson.monstersPerWave;
        this.waveNumber = levelJson.waveNumber;
        this.currentWave = null;
        this.checkPoints = null;
        this.enemyTypes = levelJson.enemyTypes;
        this.completed = false;
    }

    public void setCheckPoints(ArrayList<Vector2> checkPoints) {
        this.checkPoints = checkPoints;
    }

    public void update(float delta) {
        updateWaves(delta);
    }

    private void updateWaves(float delta) {
        if(currentWave == null)
            newWave();

        if (!currentWave.isCompleted())
            currentWave.update(delta);
        else {
            if(currentWave.getCurrentWaveNumber() < waveNumber)
                newWave();
            else
                completed = true;
        }
    }

    private void newWave() {
        if(currentWave == null) {
            currentWave = new WaveController(1, 2f, monstersPerWave, checkPoints, enemyTypes);
        } else {
            int nextWaveNumber = currentWave.getCurrentWaveNumber() + 1;
            float timeBetweenEnemies = currentWave.getTimeBetweenEnemies() - 0.5f;
            int monsterPerWave = this.monstersPerWave + 1;
            currentWave = new WaveController(nextWaveNumber, timeBetweenEnemies, monsterPerWave, checkPoints, enemyTypes);
        }

        System.out.println("WAVE NUMERO: " + currentWave.getCurrentWaveNumber());
    }

    public WaveController getCurrentWave() {
        return currentWave;
    }

    public boolean isCompleted() {
        return completed;
    }
}
