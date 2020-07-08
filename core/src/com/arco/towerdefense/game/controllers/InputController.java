package com.arco.towerdefense.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class InputController {
    Pixmap pm;

    public InputController() {
        pm = new Pixmap(Gdx.files.internal("slimecursor.png"));

        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm,pm.getWidth()/2,pm.getHeight()/2));
    }


    public void dispose() {
        pm.dispose();
    }
}
