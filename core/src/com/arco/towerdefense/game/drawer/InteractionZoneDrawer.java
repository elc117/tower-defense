package com.arco.towerdefense.game.drawer;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.layouts.StackLayout;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InteractionZoneDrawer {
    StackLayout stackSelectionTowers;
    SpriteBatch batch;

    public InteractionZoneDrawer(SpriteBatch batch) {
        this.batch = batch;
        stackSelectionTowers = new StackLayout(batch, StackLayout.ORIENTATION_HORIZONTAL);

        initSelectionTowers();
    }

    private void initSelectionTowers() {
        String[] selectionTowers = {
                Consts.TOWER_GLOBULO_BRANCO_SELECTION
        };

        for (String selectionTower: selectionTowers) {
            stackSelectionTowers.addSprite(
                    new Sprite(GameSingleton.getInstance().getTexture(selectionTower))
            );
        }
    }

    public void drawSelectionTowers() {
        stackSelectionTowers.drawAtPos(StackLayout.POSITION_BOTTOM_LEFT);
    }
}
