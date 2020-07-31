package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.UUID;

public class EnemyEntity extends Entity {

    private enum direction{DOWN, UP, LEFT, RIGHT}
    private direction dir;
    private int id;
    private float speed;
    private Vector2 nextCheckPoint;
    private boolean alive;
    private UUID targetID;
    private Animation<TextureRegion> animation;
    private float stateTime;
    private int healthPoints;
    private int maxHealthPoints;
    private float spawnInterval;

    public EnemyEntity(int id, float speed, int health, String txt, UUID targetID) {
        super(new Sprite(GameSingleton.getInstance().getTexture(txt)), 0, 0);
        super.setSpriteSizeToScale();

        this.id = id;
        this.speed = speed;
        this.alive = true;
        this.targetID = targetID;
        this.animation = Utils.createAnimation(txt, 3, 1);
        this.stateTime = 0f;
        this.maxHealthPoints = health;
        this.healthPoints = health;
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

    public void draw(SpriteBatch batch, ShapeDrawer shapeDrawer) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, getScaledX(), getScaledY(), getWidth(), getHeight());

        drawHealthBar(shapeDrawer);
    }

    private void drawHealthBar(ShapeDrawer shapeDrawer) {
        float totalWidth = getWidth();
        float height = 5;

        float posY = getScaledY() + getHeight() + 3; // 5 is the margin

        float percentHP = (float) healthPoints / maxHealthPoints;
        float widthHP = percentHP*totalWidth;
        shapeDrawer.filledRectangle(getScaledX(), posY, widthHP, height, Color.GREEN);
        shapeDrawer.filledRectangle(getScaledX() + widthHP, posY, totalWidth - widthHP, height, Color.RED);
    }

    public void performHit(int damage) {
        healthPoints -= damage;

        System.out.println("TOMA "+damage);

        if (healthPoints <= 0) {
            alive = false;
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() { return alive; }

    public Vector2 getNextCheckPoint() {
        return nextCheckPoint;
    }

    public void setNextCheckPoint(Vector2 nextCheckPoint) {
        this.nextCheckPoint = nextCheckPoint;
    }

    public void selectDirection() {
        if (nextCheckPoint == null) return;

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
    }

    public boolean inCheckPoint() {
        if(x > nextCheckPoint.x - 0.1 && x < nextCheckPoint.x + 0.1 && y > nextCheckPoint.y - 0.1 && y < nextCheckPoint.y + 0.1) {
            x = nextCheckPoint.x;
            y = nextCheckPoint.y;
            return true;
        }

        return false;
    }

    public UUID getTargetID() {
        return targetID;
    }

    public void setTargetID(UUID targetID) {
        this.targetID = targetID;
    }

    public float getSpawnInterval() {
        return spawnInterval;
    }

    public void setSpawnInterval(float spawnInterval) {
        this.spawnInterval = spawnInterval;
    }
}
