package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyEntity {
    private Texture texture;
    private float x;
    private float y;
    private float speed;

    public boolean remove = false;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public EnemyEntity(float x, float y) {
        this.texture = GameSingleton.getInstance().getTexture(Consts.ENEMY);
        this.x = x;
        this.y = y;
        speed = 5;
    }

    public void update(float delta) {
        x += delta * speed;

        if(x > Consts.V_WIDTH) {
            remove = true;
        }
    }

    public void draw(SpriteBatch batch, int scale) {
        batch.draw(texture, x*scale, y*scale, scale, scale);
    }
}
