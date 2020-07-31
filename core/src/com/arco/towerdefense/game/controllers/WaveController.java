package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.EnemyEntity;
import com.arco.towerdefense.game.utils.Utils;
import com.arco.towerdefense.game.utils.json.Spawn;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;

public class WaveController {

    private int id;
    private ArrayList<EnemyEntity> enemiesToSpawn;
    private ArrayList<EnemyEntity> enemiesInGame;
    private ArrayList<Vector2> checkPoints;
    private ArrayList<Spawn> spawns;
    private int order;
    private float timeSinceLastSpawn;
    private boolean allEnemiesDead = true;
    private boolean first = true; // first off each order use to control the time between spawns
    public boolean completed = false;
    private int highestOrder;
    private int hearts;
    public boolean gameOver = false;

    public WaveController(int id, ArrayList<Spawn> spawns, ArrayList<Vector2> checkPoints) {
        this.spawns = spawns;
        this.checkPoints = checkPoints;
        this.enemiesToSpawn = new ArrayList<>();
        this.enemiesInGame = new ArrayList<>();
        this.order = 0;
        this.timeSinceLastSpawn = 0;
        this.id = id;
        this.highestOrder = searchHighestOrder();
        selectionToSpawn();
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public void update(float delta) {
        updateEnemiesInGame(delta);
        updateEnemiesToSpawn(delta);
    }

    private void updateEnemiesToSpawn(float delta) {
        timeSinceLastSpawn += delta;

        Iterator<EnemyEntity> it = enemiesToSpawn.iterator();
        while(it.hasNext()) {
            EnemyEntity enemy = it.next();
            if (timeSinceLastSpawn > enemy.getSpawnInterval()) {
                spawn(enemy);
                it.remove();
            }
        }
    }

    private void updateEnemiesInGame(float delta) {
        Iterator<EnemyEntity> it = enemiesInGame.iterator();
        while(it.hasNext()) {
            EnemyEntity enemy = it.next();
            enemy.update(delta);

            // se o inimigo chegar no checkPoint e o ponto ser o fim ele é morto,
            // caso contrário ele atualizará pro próx ponto
            if (enemy.inCheckPoint()) {
                if(reachedTheEnd(enemy)) {
                    enemy.setAlive(false);
                    hearts--;
                    if(hearts == 0) {
                        gameOver = true;
                    }
                }
                else {
                    changeCourse(enemy);
                }
            }

            // se o inimigo morrer ele é retirado da lista
            if (enemy.isAlive()) {
                allEnemiesDead = false;
            } else {
                it.remove();
            }
        }

        // se a order acabou passa pra próxima
        if(orderFinished()) {
            nextOrder();
        }

        // se as orders acabaram, entao a wave finalizou
        if(waveFinished()) {
            completed = true;
        }
    }

    private int searchHighestOrder() {
        int highOrder = 0;
        for(Spawn spawn : spawns) {
            if(spawn.order > highOrder) {
                highOrder = spawn.order;
            }
        }
        return highOrder;
    }

    private void selectionToSpawn() {
        for(Spawn spawn : spawns) {
            if(order == spawn.order) {
                for(int i = 0; i < spawn.quantity; i++) {
                    // the first enemy of an order has immediate spawn
                    // the others will hold according to the spawn interval
                    if (i == 0) {
                        addEnemy(spawn.enemyId, 0);
                    } else {
                        addEnemy(spawn.enemyId, spawn.spawnInterval);
                    }

                }
            }
        }
    }

    private void spawn(EnemyEntity enemy) {
        timeSinceLastSpawn = 0;
        enemiesInGame.add(enemy);
        first = false;
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
        enemiesToSpawn.add(enemyEntity);
    }

    private boolean isFinalCheckPoint(Vector2 nextCheckPoint) { return nextCheckPoint == Utils.returnLastV2FromList(checkPoints); }

    private boolean orderFinished() {
        return (enemiesInGame.isEmpty() && order < highestOrder && !first);
    }

    private boolean waveFinished() {
        return (order == highestOrder && enemiesInGame.isEmpty() && !first);
    }

    private void nextOrder() {
        order++;
        selectionToSpawn();
        timeSinceLastSpawn = 0;
        first = true;
    }

    private void changeCourse(EnemyEntity enemy) {
        Vector2 next = Utils.returnNextV2FromList(checkPoints, enemy.getNextCheckPoint());
        enemy.setNextCheckPoint(next);
        enemy.selectDirection();
    }

    private boolean reachedTheEnd(EnemyEntity enemy) {
        return (isFinalCheckPoint(enemy.getNextCheckPoint()));
    }

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
