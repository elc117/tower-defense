package com.arco.towerdefense.game.drawer;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.layouts.StackLayout;
import com.arco.towerdefense.game.layouts.interfaces.LayoutListener;
import com.arco.towerdefense.game.layouts.wrappers.LayoutWrapper;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InteractionZoneDrawer {
    SpriteBatch batch;

    public InteractionZoneDrawer(SpriteBatch batch) {
        this.batch = batch;
    }
}
