package com.arco.towerdefense.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player {
    Texture txt;
    int x;
    int y;

    public Player(int x, int y) {
        txt = new Texture("dirt.png");
        this.x = x;
        this.y = y;
    }
}
