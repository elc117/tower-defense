package com.arco.towerdefense.game.factories;

import com.arco.towerdefense.game.controllers.LevelController;
import com.arco.towerdefense.game.controllers.WaveController;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.json.LevelJson;
import com.arco.towerdefense.game.utils.json.TowerJson;
import com.arco.towerdefense.game.utils.json.Wave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class LevelGenerator {
    ArrayList<LevelJson> levelsJson;

    public LevelGenerator() {
        Json json = new Json();
        levelsJson = json.fromJson(ArrayList.class, LevelJson.class, Gdx.files.internal(Consts.LEVELS_JSON));
    }

    public LevelController createById(int id) {
        for(LevelJson level : levelsJson) {
            if(level.id == id) {
                return create(level);
            }
        }
        return null;
    }

    public static LevelController create(LevelJson level) {
        ArrayList<WaveController> waves = generateWaves(level);
        LevelController levelController = new LevelController(level, waves);

        return levelController;
    }

    private static ArrayList<WaveController> generateWaves(LevelJson level) {
        ArrayList<WaveController> waveControllers = new ArrayList<>();
        for(Wave wave : level.waves) {
            waveControllers.add(new WaveController(wave.waveID, wave.spawns, level.checkPoints));
        }
        return waveControllers;
    }
}
