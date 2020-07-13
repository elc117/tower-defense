package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Texture;


public class TowerEntity {
    private Texture texture;
    private int x;
    private int y;

    public Texture getTexture() {
        return texture;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TowerEntity(int x, int y) {
        texture = GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO);
        this.x = x;
        this.y = y;
    }
}
