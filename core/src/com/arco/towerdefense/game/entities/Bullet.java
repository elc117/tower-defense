package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.arco.towerdefense.game.utils.Consts.V_WIDTH;

public class Bullet {
    private Texture txt;
    private float x;
    private float y;
    private float speed;
    private int damage;

    public boolean remove = false;

    public Bullet(String txt, float x, float y, float speed, int damage) {
        this.txt = new Texture(txt);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public void update(float delta) {
        x += delta * speed;
        //draw() ??

        if(x > Consts.V_WIDTH) {
            remove = true;
        }
    }

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(txt, x*scale, y*scale, scale, scale);
    }
}
