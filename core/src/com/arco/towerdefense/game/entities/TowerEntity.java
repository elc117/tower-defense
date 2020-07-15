package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;


public class TowerEntity{
    private Texture texture;
    private float x;
    private float y;
    private int damage;
    private float timeSinceLastShoot;
    private float firingSpeed;
    private ArrayList<Bullet> bullets;
    private Random random;

    public TowerEntity(float x, float y) {
        texture = GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO);
        this.x = x;
        this.y = y;
        this.firingSpeed = 3f;
        this.timeSinceLastShoot = 0;
        //this.damage = ??
        bullets = new ArrayList<>();
        Random random;
    }

    private void shoot() {
        timeSinceLastShoot = 0;
        bullets.add(new Bullet("badlogic.jpg", x, y, 10, 10));

    }

    public void update(float delta) {
       timeSinceLastShoot += delta;
       if(timeSinceLastShoot > firingSpeed) {
           shoot();
       }

       //Update and remove bullets positions
       ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
       for(Bullet bullet : bullets) {
            bullet.update(delta);
            if(bullet.remove) {
                bulletsToRemove.add(bullet);
            }
       }
       bullets.removeAll(bulletsToRemove);
    }

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(texture, x*scale, y*scale, scale, scale);

        for(Bullet bullet : bullets) {
            bullet.draw(batch, scale);
        }
    }
}
