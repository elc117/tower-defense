package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnemyEntity extends Entity {

    private enum direction{DOWN, UP, LEFT, RIGHT}
    private float speed;
    private Vector2 nextCheckPoint;
    private Vector2 finalCheckPoint;
    private direction dir;
    public boolean alive;

    public EnemyEntity(Vector2 start, Vector2 nextCheckPoint, Vector2 finalCheckPoint) {
        super(GameSingleton.getInstance().getTexture(Consts.ENEMY), start.x, start.y);
        this.speed = 5;
        this.nextCheckPoint = nextCheckPoint;
        this.finalCheckPoint = finalCheckPoint;
        this.alive = true;
        this.selectDirection();
    }

    public void update(float delta) {
        if(dir == direction.DOWN)
            y -= delta * speed;

        if(dir == direction.UP)
            y += delta * speed;

        if(dir == direction.LEFT)
            x -= delta * speed;

        if(dir == direction.RIGHT)
            x += delta * speed;
    }

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(txt, x*scale, y*scale, scale, scale);
    }

    public boolean isAlive() { return alive; }

    public Vector2 getNextCheckPoint() {
        return nextCheckPoint;
    }

    public void setNextCheckPoint(Vector2 nextCheckPoint) {
        this.nextCheckPoint = nextCheckPoint;
    }

    public void selectDirection() {
        if (nextCheckPoint != null) {

            if (y > nextCheckPoint.y) {
                //baixo
                dir = direction.DOWN;
            }
            if (y < nextCheckPoint.y) {
                //cima
                dir = direction.UP;
            }
            if (x > nextCheckPoint.x) {
                //esquerda
                dir = direction.LEFT;
            }
            if (x < nextCheckPoint.x) {
                //direita
                dir = direction.RIGHT;
            }

            return;
        }
    }

    public boolean isCheckPoint() {
        if(x > nextCheckPoint.x - 0.1 && x < nextCheckPoint.x + 0.1 && y > nextCheckPoint.y - 0.1 && y < nextCheckPoint.y + 0.1) {
            x = nextCheckPoint.x;
            y = nextCheckPoint.y;
            return true;
        }

        return false;
    }

    public boolean isFinalCheckPoint() {
        return nextCheckPoint == finalCheckPoint;
    }
}
