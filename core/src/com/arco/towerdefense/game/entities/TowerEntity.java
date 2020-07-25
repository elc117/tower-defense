package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;


public class TowerEntity extends Entity {

    private float damage;
    private float timeSinceLastShoot;
    private float firingSpeed;
    private ArrayList<BulletEntity> bullets;
    private int id;
    private float range;
    private float rangePower2; // This is so that we store the pre-calculated value.

    public TowerEntity(float x, float y) {
        super(GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO), x, y);

        this.firingSpeed = 3f;
        this.timeSinceLastShoot = 0;
        this.bullets = new ArrayList<>();
        this.setRange(2);
    }


    public void setRange(float range) {
        this.range = range;
        this.rangePower2 = range * range;
    }

    public float getRange() {
        return range;
    }

    private void shoot() {
        timeSinceLastShoot = 0;
        bullets.add(new BulletEntity("badlogic.jpg", x, y, 10, 10));

    }

    public void update(float delta, ArrayList<EnemyEntity> enemies) {
        timeSinceLastShoot += delta;
        if(timeSinceLastShoot > firingSpeed) {
            shoot();
        }
        EnemyEntity e = this.getEnemyInRanger(enemies);

        if (e != null) {
            System.out.println(e.getTargetID());
        }

        //Update and remove bullets positions
        ArrayList<BulletEntity> bulletsToRemove = new ArrayList<>();
        for(BulletEntity bullet : bullets) {
            bullet.update(delta);
            if(bullet.remove) {
                bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);
    }

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(txt, x*scale, y*scale, scale, scale);

        for(BulletEntity bullet : bullets) {
            bullet.draw(batch, scale);
        }
    }

    public void setTexture(String texturePath) {
        // If this throw an error means that we have not loaded the texture in our AssetManager.
        this.txt = GameSingleton.getInstance().getTexture(texturePath);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setFiringSpeed(float firingSpeed) {
        this.firingSpeed = firingSpeed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnemyEntity getEnemyInRanger(ArrayList<EnemyEntity> enemies) {
        for (EnemyEntity enemy: enemies) {

            // Here I am thinking in optimization since this will happen lots of times
            float distance = (enemy.x - this.x)*(enemy.x - this.x) + (enemy.y - this.y)*(enemy.y - this.y);

            if (distance < rangePower2) {
                return enemy;
            }
        }

        return null; // We could not find any
    }
}
