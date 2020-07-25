package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {

    protected Texture txt;
    protected float x;
    protected float y;
    protected int scale;

    public Entity(Texture txt, float x, float y) {
        this.txt = txt;
        this.x = x;
        this.y = y;

        scale = GameSingleton.getInstance().getGroundScale();
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

    public float getScaledX() {
        return x * scale;
    }

    public float getScaledY() {
        return y * scale;
    }
}
