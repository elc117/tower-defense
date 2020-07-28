package com.arco.towerdefense.game.utils.json;

import com.arco.towerdefense.game.controllers.WaveController;
import com.arco.towerdefense.game.utils.path.CheckPoint;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class LevelJson {
    public int id;
    public int dificulty;
    public ArrayList<Wave> waves;
    public ArrayList<Vector2> checkPoints;
}
