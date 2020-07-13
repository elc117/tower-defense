package com.arco.towerdefense.game.entities;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.Texture;


public class TowerEntity {
    public Texture texture;
    public int x;
    public int y;

    public TowerEntity(int x, int y) {
        texture = GameSingleton.getInstance().getTexture(Consts.TOWER_GLOBULO_BRANCO);
        this.x = x;
        this.y = y;
    }
}
