package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

    protected Sprite sprite;
    protected float gridX;
    protected float gridY;
    protected int scale;

    public Entity(Sprite sprite, float gridX, float gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.scale = GameSingleton.getInstance().getGroundScale();
        this.sprite = sprite;
        this.updateEntityPositionAndBounds();
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

    private void updateEntityPositionAndBounds() {
        setBounds(getScaledX(), getScaledY(), getWidth(), getHeight());
        setPosition(getScaledX(), getScaledY());
        sprite.setPosition(getScaledX(), getScaledY());
    }

    public float getGridX() {
        return gridX;
    }

    public void setGridX(float x) {
        this.gridX = x;
    }

    public float getGridY() {
        return gridY;
    }

    public void setGridY(float y) {
        this.gridY = y;
    }

    public float getScaledX() {
        return gridX * scale;
    }

    public float getScaledY() {
        return gridY * scale;
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
        return new Rectangle(getScaledX(), getScaledY(), getWidth(), getHeight());
    }

    public Vector2 getCenter() {
        return new Vector2(getScaledX() + getWidth()/2, getScaledY() + getHeight()/2);
    }

    public void draw(SpriteBatch batch) {
        updateEntityPositionAndBounds();
        sprite.draw(batch);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.draw((SpriteBatch) batch);
    }

    protected void setTextureRegionToSprite(TextureRegion textureRegion) {
        this.sprite = new Sprite(textureRegion);
        this.updateEntityPositionAndBounds();
        this.setSpriteSizeToScale();
    }
}
