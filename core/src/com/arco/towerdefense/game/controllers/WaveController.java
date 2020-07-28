package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.utils.Utils;
import com.arco.towerdefense.game.utils.json.Spawn;
import com.arco.towerdefense.game.utils.json.Wave;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public class WaveController {

    private int id;
    private ArrayList<EnemyEntity> enemies;
    private ArrayList<Vector2> checkPoints;
    private ArrayList<Spawn> spawns;
    private int order;
    private ArrayList<EnemyEntity> enemiesInGame;
    private float timeSinceLastSpawn;
    private boolean allEnemiesDead = true;
    private boolean first = true; // first off each order use to control the time between spawns
    public boolean completed = false;
    private int highestOrder;


    public WaveController(int id, ArrayList<Spawn> spawns, ArrayList<Vector2> checkPoints) {
        this.spawns = spawns;
        this.checkPoints = checkPoints;
        this.enemies = new ArrayList<>();
        this.order = 0;
        this.enemiesInGame = new ArrayList<>();
        this.timeSinceLastSpawn = 0;
        this.id = id;
        this.highestOrder = searchHighestOrder();
        selectionToSpawn();
    }

    private int searchHighestOrder() {
        int highOrder = 0;
        for(Spawn spawn : spawns) {
            if(spawn.order > highOrder) {
                highOrder = spawn.order;
            }
        }
        System.out.println("High order: " + highOrder);

        return highOrder;
    }

    private void selectionToSpawn() {
        for(Spawn spawn : spawns) {
            if(order == spawn.order) {
                for(int i = 0; i < spawn.quantity; i++) {
                    addEnemy(spawn.enemyId, spawn.spawnInterval);
                }
            }
        }
    }

    private void addEnemy(int id, float spawnInterval) {
        Vector2 startCheckPoint = Utils.returnFirstV2FromList(checkPoints);
        Vector2 nextCheckPoint = Utils.returnNextV2FromList(checkPoints, startCheckPoint);

        EnemyEntity enemyEntity = GameSingleton.getInstance().getEnemyFactory().createById(id);
        enemyEntity.setNextCheckPoint(nextCheckPoint);
        enemyEntity.setX(startCheckPoint.x);
        enemyEntity.setY(startCheckPoint.y);
        enemyEntity.setSpawnInterval(spawnInterval);
        enemyEntity.selectDirection();
        enemies.add(enemyEntity);
    }

    private void spawn(EnemyEntity enemy) {
        timeSinceLastSpawn = 0;
        enemiesInGame.add(enemy);
        first = false;
    }

    public void update(float delta) {
        updateEnemies(delta);
        timeSinceLastSpawn += delta;
        ArrayList<EnemyEntity> enemiesToRemove = new ArrayList<>();
        for(EnemyEntity enemy : enemies) {
            if (timeSinceLastSpawn > enemy.getSpawnInterval()) {
                spawn(enemy);
                enemiesToRemove.add(enemy);
            }
        }
        enemies.removeAll(enemiesToRemove);
    }

    private void updateEnemies(float delta) {
        ArrayList<EnemyEntity> enemiesToRemove = new ArrayList<>();
      
        for(EnemyEntity enemy : enemiesInGame) {
            if (enemy.isCheckPoint()) {
                if (isFinalCheckPoint(enemy.getNextCheckPoint())) {
                    enemy.setAlive(false);
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
        enemiesInGame.removeAll(enemiesToRemove);
        if(enemiesInGame.isEmpty() && order < highestOrder && !first) {
            order++;
            selectionToSpawn();
            timeSinceLastSpawn = 0;
            first = true;
        } else if(order == highestOrder && enemiesInGame.isEmpty() && !first){
            completed = true;
        }
    }

    public boolean isFinalCheckPoint(Vector2 nextCheckPoint) { return nextCheckPoint == Utils.returnLastV2FromList(checkPoints); }

    public ArrayList<EnemyEntity> getEnemiesInGame() {
        return enemiesInGame;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id  = id;
    }
}
