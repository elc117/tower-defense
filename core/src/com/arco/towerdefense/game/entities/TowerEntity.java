package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private float rangePower2; // This is so that we store the pre-calculated value.
    private int scale;

    public TowerEntity(float x, float y) {
        super(GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO), x, y);
        this.bullets = new ArrayList<>();
        this.scale = 1;
    }


    public void setRange(float range) {
        this.range = range * scale;
        this.rangePower2 = this.range * this.range;
    }

    public float getRange() {
        return range / scale; // Here we normalize the range removing the scale
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

    public void draw(SpriteBatch batch, int scale, ShapeDrawer shapeDrawer) {
        batch.draw(txt, x*scale, y*scale, scale, scale);

        if (canShoot()) {
            shapeDrawer.setColor(Color.GREEN);
        } else {
            shapeDrawer.setColor(Color.RED);
        }

        // Little circle indicating the ability to shoot again
        int radius = 3;
        shapeDrawer.filledCircle(this.x*scale + scale/2, this.y*scale - radius, radius); // scale == width and height

        for (BulletEntity bullet : bullets) {
            bullet.draw(batch, scale);
        }
    }

    public void setTexture(String texturePath) {
        // If this throw an error means that we have not loaded the texture in our AssetManager.
        this.txt = GameSingleton.getInstance().getTexture(texturePath);
    }

    public void setScale(int scale) {
        this.scale = scale;
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
            float distance = (enemy.x - this.x)*(enemy.x - this.x) + (enemy.y - this.y)*(enemy.y - this.y);

            if (distance <= rangePower2) {
                return enemy;
            }
        }

        return null; // We could not find any
    }
}
