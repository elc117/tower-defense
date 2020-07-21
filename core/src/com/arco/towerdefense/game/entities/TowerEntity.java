package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class TowerEntity{
    private Texture texture;
    private float x;
    private float y;
    private float damage;
    private float timeSinceLastShoot;
    private float firingSpeed;
    private ArrayList<Bullet> bullets;
    private int id;

    public TowerEntity(float x, float y) {
        texture = GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO);
        this.x = x;
        this.y = y;
        this.firingSpeed = 3f;
        this.timeSinceLastShoot = 0;
        //this.damage = ??
        bullets = new ArrayList<>();
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setTexture(String texturePath) {
        // If this throw an error means that we have not loaded the texture in our AssetManager.
        this.texture = GameSingleton.getInstance().getTexture(texturePath);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
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

    public Texture getTexture() {
        return texture;
    }
}
