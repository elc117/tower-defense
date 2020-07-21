package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class TowerEntity extends Entity {

    private float damage;
    private float timeSinceLastShoot;
    private float firingSpeed;
    private ArrayList<BulletEntity> bullets;
    private int id;

    public TowerEntity(float x, float y) {
        super(GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO), x, y);

        this.firingSpeed = 3f;
        this.timeSinceLastShoot = 0;
        this.bullets = new ArrayList<>();
    }

    private void shoot() {
        timeSinceLastShoot = 0;
        bullets.add(new BulletEntity("badlogic.jpg", x, y, 10, 10));

    }

    public void update(float delta) {
        timeSinceLastShoot += delta;
        if(timeSinceLastShoot > firingSpeed) {
            shoot();
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
}
