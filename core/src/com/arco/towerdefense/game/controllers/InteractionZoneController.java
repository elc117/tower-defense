package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.drawer.InteractionZoneDrawer;
import com.arco.towerdefense.game.layouts.StackLayout;
import com.arco.towerdefense.game.layouts.enums.Orientation;
import com.arco.towerdefense.game.layouts.enums.Position;
import com.arco.towerdefense.game.layouts.interfaces.LayoutListener;
import com.arco.towerdefense.game.layouts.wrappers.LayoutWrapper;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InteractionZoneController {
    InteractionZoneDrawer interactionZoneDrawer;
    StackLayout stackSelectionTowers;

    public InteractionZoneController(SpriteBatch batch) {
        interactionZoneDrawer = new InteractionZoneDrawer(batch);

        stackSelectionTowers = new StackLayout(batch, Orientation.HORIZONTAL);
        stackSelectionTowers.setMarginBetween(3);
        stackSelectionTowers.setPadding(3);

        initSelectionTowers();
    }

    public void update() {
        stackSelectionTowers.drawAtPos(Position.BOTTOM_LEFT);
        handleMouse();
    }

    private void initSelectionTowers() {
        String[] selectionTowers = {
                Consts.TOWER_GLOBULO_BRANCO_SELECTION,
                Consts.TOWER_GLOBULO_BRANCO_SELECTION,
                Consts.TOWER_GLOBULO_BRANCO_SELECTION,
                Consts.TOWER_GLOBULO_BRANCO_SELECTION
        };

        for (String selectionTower: selectionTowers) {
            stackSelectionTowers.addWrapper(
                new LayoutWrapper(
                    new Sprite(GameSingleton.getInstance().getTexture(selectionTower)),
                    new LayoutListener() {
                        @Override
                        public void onClick(LayoutWrapper layoutWrapper) {
                            System.out.printf("CLICADO EM\n");
                        }

                        @Override
                        public void onHover(LayoutWrapper layoutWrapper) {
                            System.out.printf("ON HOVER EM\n");
                        }
                    }
                )
            );
        }
    }

    private void handleMouse() {
        if (stackSelectionTowers.isCursorInside()) {
            stackSelectionTowers.handleMouse();
        }
    }
}
