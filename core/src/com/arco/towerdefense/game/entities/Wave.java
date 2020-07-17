package com.arco.towerdefense.game.entities;

import java.util.ArrayList;

public class Wave {
    private float timeSinceLastSpawn;
    private float spawnTime;
    private EnemyEntity enemy;
    private int waveEnemyNumber;
    private ArrayList<EnemyEntity> enemies;

    public ArrayList<EnemyEntity> getEnemies() {
        return enemies;
    }

    public Wave(float spawnTime, EnemyEntity enemy, int waveEnemyNumber) {
        this.spawnTime = spawnTime;
        this.enemy = enemy;
        this.waveEnemyNumber = waveEnemyNumber;
        timeSinceLastSpawn = 0;
        enemies = new ArrayList<>();
    }

    private void spawn() {
        timeSinceLastSpawn = 0;
        //enemies.add(new EnemyEntity(enemy.getX(), enemy.getY()));
    }

    public void update(float delta) {
        timeSinceLastSpawn += delta;
        if(timeSinceLastSpawn > spawnTime) {
            spawn();
        }

        //update Enemies positions
        for(EnemyEntity enemy : enemies) {
            enemy.update(delta);
        }

        for(EnemyEntity enemy : enemies) {
            enemy.update(delta);
        }
    }
}
