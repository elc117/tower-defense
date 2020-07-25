package com.arco.towerdefense.game.factories;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.json.TowerJson;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class TowerFactory {
    Array<TowerJson> towersJson;

    public TowerFactory() {
        Json json = new Json();
        towersJson = json.fromJson(Array.class, TowerJson.class, Gdx.files.internal(Consts.TOWERS_JSON));
    }

    public TowerEntity createById(int id) {
        for (TowerJson towerJson: towersJson) {
            if (towerJson.id == id) {
                return create(towerJson);
            }
        }

        return null;
    }

    public TowerEntity create(TowerJson towerJson) {
        TowerEntity towerEntity = new TowerEntity(0, 0); //Create tower at generic point
        towerEntity.setDamage(towerJson.damage);
        towerEntity.setFiringSpeed(towerJson.firing_speed);
        towerEntity.setId(towerJson.id);
        towerEntity.setTexture(towerJson.skinPath);
        towerEntity.setRange(towerJson.range);
        towerEntity.setHeight(GameSingleton.getInstance().getGroundScale());
        towerEntity.setWidth(GameSingleton.getInstance().getGroundScale());

        return towerEntity;
    }
}
