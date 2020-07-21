package com.arco.towerdefense.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    protected Texture txt;
    protected float x;
    protected float y;

    public Entity(Texture txt, float x, float y) {
        this.txt = txt;
        this.x = x;
        this.y = y;
    }

    public Texture getTexture() {
        return txt;
    }

    public void setTexture(Texture txt) {
        this.txt = txt;
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

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch, int scale);
}
