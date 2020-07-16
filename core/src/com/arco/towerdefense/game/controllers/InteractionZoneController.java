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
        stackSelectionTowers.addWrapper(
                new LayoutWrapper(
                        new Sprite(GameSingleton.getInstance().getTexture(
                                Consts.TOWER_GLOBULO_BRANCO_SELECTION
                        )),
                        new LayoutListener() {
                            @Override
                            public void onClick(LayoutWrapper layoutWrapper) {
                                System.out.printf("CLICADO EM 1\n");
                            }

                            @Override
                            public void onHover(LayoutWrapper layoutWrapper) {
                                System.out.printf("ON HOVER EM 1\n");
                            }
                        }
                )
        );

        stackSelectionTowers.addWrapper(
                new LayoutWrapper(
                        new Sprite(GameSingleton.getInstance().getTexture(
                                Consts.TOWER_GLOBULO_BRANCO_SELECTION2
                        )),
                        new LayoutListener() {
                            @Override
                            public void onClick(LayoutWrapper layoutWrapper) {
                                System.out.printf("CLICADO EM 2\n");
                            }

                            @Override
                            public void onHover(LayoutWrapper layoutWrapper) {
                                System.out.printf("ON HOVER EM 2\n");
                            }
                        }
                )
        );
    }

    private void handleMouse() {
        if (stackSelectionTowers.isCursorInside()) {
            stackSelectionTowers.handleMouse();
        }
    }
}
