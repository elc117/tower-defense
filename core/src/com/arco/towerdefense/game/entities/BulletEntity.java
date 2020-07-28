package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;

import static com.arco.towerdefense.game.utils.Consts.V_WIDTH;

public class BulletEntity extends Entity {
    private float speed;
    private int damage;
    private boolean shouldRemove;
    private EnemyEntity target;

    public BulletEntity(String texturePath, float x, float y, float speed, int damage, EnemyEntity target) {
        super(new Sprite(GameSingleton.getInstance().getTexture(texturePath)), x, y);
        super.setSpriteSizeToScale();

        this.speed = speed;
        this.damage = damage;
        this.shouldRemove = false;
        this.target = target;
    }

    public void update(float delta) {
        x += adjustDeltaXAxis(delta) * speed;
        y += adjustDeltaYAxis(delta) * speed;

        if (hasHitTarget()) {
            performTargetHit();
        }

        if(!Utils.isInsideScreen(getScaledX(), getScaledY())) {
            shouldRemove = true;
        }
    }

    private void performTargetHit() {
        this.shouldRemove = true;
        target.performHit(this.damage);
    }

    public boolean hasHitTarget() {
        return Intersector.overlaps(super.getEntityRect(), target.getEntityRect());
    }

    private float adjustDeltaXAxis(float delta) {
        if (x > target.x) {
            return -delta;
        }

        return delta;
    }

    private float adjustDeltaYAxis(float delta) {
        if (y > target.y) {
            return -delta;
        }

        return delta;
    }

    public boolean shouldRemove() {
        return shouldRemove;
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
