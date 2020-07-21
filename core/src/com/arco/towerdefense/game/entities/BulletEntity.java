package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.arco.towerdefense.game.utils.Consts.V_WIDTH;

public class BulletEntity extends Entity {

    private float speed;
    private int damage;
    public boolean remove;

    public BulletEntity(String txt, float x, float y, float speed, int damage) {
        super(new Texture(txt), x, y);
        this.speed = speed;
        this.damage = damage;
        this.remove = false;
    }

    public void update(float delta) {
        x += delta * speed;

        if(x > Consts.V_WIDTH) {
            remove = true;
        }
    }

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(txt, x*scale, y*scale, scale, scale);
    }
}
