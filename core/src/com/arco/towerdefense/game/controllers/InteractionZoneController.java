package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.GameSingleton;
import com.arco.towerdefense.game.drawer.InteractionZoneDrawer;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.layouts.StackLayout;
import com.arco.towerdefense.game.layouts.enums.Orientation;
import com.arco.towerdefense.game.layouts.enums.Position;
import com.arco.towerdefense.game.layouts.interfaces.LayoutListener;
import com.arco.towerdefense.game.layouts.wrappers.LayoutWrapper;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class InteractionZoneController extends InputAdapter {
    InteractionZoneDrawer interactionZoneDrawer;
    StackLayout stackSelectionTowers;
    GroundController groundController;

    public InteractionZoneController(SpriteBatch batch, GroundController groundController) {
        interactionZoneDrawer = new InteractionZoneDrawer(batch);

        stackSelectionTowers = new StackLayout(batch, Orientation.HORIZONTAL);
        stackSelectionTowers.setMarginBetween(3);
        stackSelectionTowers.setPadding(3);

        this.groundController = groundController;

        initSelectionTowers();
    }

    public void update() {
        stackSelectionTowers.drawAtPos(Position.BOTTOM_LEFT);
    }

    private void setTowerEntityToHolder(TowerEntity t) {
        groundController.setTowerEntityHolder(t);
    }

    private void initSelectionTowers() {
        String towersJSON = Utils.getStringFromFile(Consts.TOWERS_JSON);
        JsonValue allTowersDescription = new JsonReader().parse(towersJSON);

        for (int i = 0; i < allTowersDescription.size; i++) {
            JsonValue towerDescription = allTowersDescription.get(i);

            final TowerEntity towerEntity = new TowerEntity(0, 0); //Create tower at generic point
            towerEntity.setDamage(towerDescription.getFloat("damage"));
            towerEntity.setFiringSpeed(towerDescription.getFloat("firing_speed"));
            towerEntity.setId(towerDescription.getInt("id"));
            towerEntity.setTexture(towerDescription.getString("skinPath"));

            stackSelectionTowers.addWrapper(
                    new LayoutWrapper(
                            new Sprite(GameSingleton.getInstance().getTexture(
                                    towerDescription.getString("selectionPath")
                            )),
                            new LayoutListener() {
                                @Override
                                public void onClick(LayoutWrapper layoutWrapper) {
                                    groundController.setHasSelectedTower(true);

                                    setTowerEntityToHolder(towerEntity);
                                }

                                @Override
                                public void onHover(LayoutWrapper layoutWrapper) {
                                    System.out.printf("ON HOVER EM 1\n");
                                }
                            }
                    )
            );
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (stackSelectionTowers.isCursorInside()) {
            return stackSelectionTowers.handleTouchUp();
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if (stackSelectionTowers.isCursorInside()) {
            return stackSelectionTowers.handleMouseMoved();
        }

        return false;
    }
}
