package com.arco.towerdefense.game.entities;

import com.badlogic.gdx.graphics.Texture;


public class TowerEntity {
    public Texture txt;
    public int x;
    public int y;

    public TowerEntity(int x, int y) {
        // TODO: Não precisa criar uma Texture pra cada novo TowerEntity.
        //  A gente pode usar um AssetsManager pra melhorar isso.
        txt = new Texture("towers/globuloBranco.png");
        this.x = x;
        this.y = y;
    }
}
