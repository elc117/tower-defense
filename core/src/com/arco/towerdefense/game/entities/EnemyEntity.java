package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class EnemyEntity {
    private Texture texture;
    private float x;
    private float y;
    private float speed;
    private Vector2 nextCheckPoint;
    private Vector2 finalCheckPoint;
    private int direction;
    public boolean alive;

    public EnemyEntity(Vector2 start, Vector2 nextCheckPoint, Vector2 finalCheckPoint) {
        this.texture = GameSingleton.getInstance().getTexture(Consts.ENEMY);
        this.x = start.x;
        this.y = start.y;

        speed = 5;

        this.nextCheckPoint = nextCheckPoint;
        this.finalCheckPoint = finalCheckPoint;

        alive = true;

        selectDirection();
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isAlive() { return alive; }

    public Vector2 getNextCheckPoint() {
        return nextCheckPoint;
    }

    public void setNextCheckPoint(Vector2 nextCheckPoint) {
        this.nextCheckPoint = nextCheckPoint;
    }

    public void update(float delta) {
        if(direction == 0)
            y -= delta * speed;

        if(direction == 1)
            y += delta * speed;

        if(direction == 2)
            x -= delta * speed;

        if(direction == 3)
            x += delta * speed;

    }

    public void selectDirection() {
        if (nextCheckPoint != null) {

            if (y > nextCheckPoint.y) {
                //baixo
                direction = 0;
            }
            if (y < nextCheckPoint.y) {
                //cima
                direction = 1;
            }
            if (x > nextCheckPoint.x) {
                //esquerda
                direction = 2;
            }
            if (x < nextCheckPoint.x) {
                //direita
                direction = 3;
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

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(texture, x*scale, y*scale, scale, scale);
    }
}
