package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.json.LevelJson;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelController {
    private int id;
    private float dificulty;
    private int initMoney;
    private ArrayList<WaveController> waves;
    private WaveController currentWave;
    private ArrayList<Vector2> checkPoints;
    public boolean completed = false;

    public LevelController(LevelJson levelJson, ArrayList<WaveController> waves) {
        this.id = levelJson.id;
        this.dificulty = levelJson.dificulty;
        this.waves = waves;
        this.initMoney = levelJson.money;
        this.currentWave = null;
        this.checkPoints = levelJson.checkPoints;
    }

    public ArrayList<Vector2> getCheckPoints() {
        return checkPoints;
    }

    public WaveController getCurrentWave() {
        return currentWave;
    }

    private WaveController getNextWave(WaveController wave) {
        int next = waves.indexOf(wave) + 1;

        if(waves.get(next) == null)
            return null;

        return waves.get(next);
    }

    public int getTotalWaves() {
        return waves.size();
    }

    private WaveController getFirstWave() {
        return this.waves.get(0);
    }
    
    public void update(float delta) {
        updateWaves(delta);
    }

    private void updateWaves(float delta) {
        if(currentWave == null)
            newWave();

        if (!currentWave.completed) {
            currentWave.update(delta);
        } else {
            if(currentWave.getId() < waves.size())
                newWave();
        }

        if(currentWave.getId() == waves.size() && currentWave.completed) {
            completed = true;
        }
    }


    private void newWave() {
        GameSingleton gameSingleton = GameSingleton.getInstance();

        if(currentWave == null) {
            currentWave = getFirstWave();
            gameSingleton.setHearts(10);
            gameSingleton.setMoney(initMoney);
        } else {
            currentWave = waves.get(currentWave.getId());
        }
    }

    public int getWaveID() {
        return currentWave.getId();
    }



}
