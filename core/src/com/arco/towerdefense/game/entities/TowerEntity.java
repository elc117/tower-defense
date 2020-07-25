package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.Iterator;


public class TowerEntity extends Entity {
    private int damage;
    private float timeSinceLastShoot;
    private float firingSpeed;
    private ArrayList<BulletEntity> bullets;
    private int id;
    private float range;
    private Vector2 centerTower;
    private float width;
    private float height;

    public TowerEntity(int gridX, int gridY) {
        super(GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO), gridX, gridY);
        this.bullets = new ArrayList<>();

        centerTower = new Vector2();
    }


    public void setRange(float range) {
        this.range = range * scale;
    }

    public Vector2 getCenterTower() {
        centerTower.x = getScaledX() + width/2;
        centerTower.y = getScaledY() + height/2;
        return centerTower;
    }

    public Circle getCircleRange() {
        return new Circle(getCenterTower(), range);
    }

    private void shoot(EnemyEntity enemyTarget) {
        timeSinceLastShoot = 0;
        bullets.add(new BulletEntity("badlogic.jpg", x, y, 10, damage, enemyTarget));

    }

    public void update(float delta, ArrayList<EnemyEntity> enemies) {
        timeSinceLastShoot += delta;
        if (canShoot()) {
            EnemyEntity enemyTarget = this.getEnemyInRange(enemies);
            if (enemyTarget != null) {
                shoot(enemyTarget);
            }
        }

        updateBullets(delta);
    }

    private boolean canShoot() {
        return timeSinceLastShoot >= firingSpeed;
    }

    public void updateBullets(float delta) {
        ArrayList<BulletEntity> bulletsToRemove = new ArrayList<>();

        for (BulletEntity bullet : bullets) {

        }
        Iterator<BulletEntity> it = bullets.iterator();
        while (it.hasNext()) {
            BulletEntity bullet = it.next();
            bullet.update(delta, scale);
            if (bullet.shouldRemove()) {
                bullet.dispose();
                it.remove();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(txt, getScaledX(), getScaledY(), width, height);

        for (BulletEntity bullet : bullets) {
            bullet.draw(batch);
        }
    }

    public void setTexture(String texturePath) {
        // If this throw an error means that we have not loaded the texture in our AssetManager.
        this.txt = GameSingleton.getInstance().getTexture(texturePath);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setFiringSpeed(float firingSpeed) {
        this.firingSpeed = firingSpeed;
        this.timeSinceLastShoot = firingSpeed; // So that the first shot is immediate
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnemyEntity getEnemyInRange(ArrayList<EnemyEntity> enemies) {
        for (EnemyEntity enemy: enemies) {
            // Here I am thinking in optimization since this will happen lots of times
//            System.out.printf("CIRCLE: %s", this.getCircleRange().toString());
            if (Intersector.overlaps(this.getCircleRange(), enemy.getEnemyRect())) {
                return enemy;
            }
        }

        return null; // We could not find any
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
