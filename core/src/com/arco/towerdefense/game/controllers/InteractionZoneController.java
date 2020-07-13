package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.drawer.InteractionZoneDrawer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InteractionZoneController {
    InteractionZoneDrawer interactionZoneDrawer;
    public InteractionZoneController(SpriteBatch batch) {
        interactionZoneDrawer = new InteractionZoneDrawer(batch);
    }

    public void update() {
        interactionZoneDrawer.drawSelectionTowers();
    }
}
