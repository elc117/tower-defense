package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

    protected Sprite sprite;
    protected float x;
    protected float y;
    protected int scale;
    protected Rectangle rect;

    public Entity(Sprite sprite, float x, float y) {
        this.x = x;
        this.y = y;
        this.scale = GameSingleton.getInstance().getGroundScale();
        this.sprite = sprite;
        this.sprite.setPosition(x*scale, y*scale);

        this.rect = new Rectangle();
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    private void updateSpritePositions() {
        sprite.setPosition(getScaledX(), getScaledY());
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getScaledX() {
        return x * scale;
    }

    public float getScaledY() {
        return y * scale;
    }

    public void setSpriteSizeToScale() {
        this.sprite.setSize(scale, scale);
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public Rectangle getEntityRect() {
        rect.setPosition(getScaledX(), getScaledY());
        rect.setSize(getWidth(), getHeight());

        return rect;
    }

    public void draw(SpriteBatch batch) {
        updateSpritePositions();

        sprite.draw(batch);
    }
}
