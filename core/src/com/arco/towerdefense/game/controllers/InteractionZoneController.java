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
import com.arco.towerdefense.game.utils.json.TowerJson;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

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
        groundController.setSelectedTowerSprite(t.getTexture());
    }

    private void initSelectionTowers() {
        Json json = new Json();

        Array<TowerJson> towersJson = json.fromJson(Array.class, TowerJson.class, Gdx.files.internal(Consts.TOWERS_JSON));

        for (final TowerJson towerJson: towersJson) {
            stackSelectionTowers.addWrapper(
                new LayoutWrapper(
                    new Sprite(GameSingleton.getInstance().getTexture(
                            towerJson.selectionPath
                    )),
                    new LayoutListener() {
                        @Override
                        public void onClick(LayoutWrapper layoutWrapper) {
                            groundController.setHasSelectedTower(true);
                            TowerEntity towerEntity = GameSingleton.getInstance().getTowerFactory().create(towerJson);
                            setTowerEntityToHolder(towerEntity);
                        }

                        @Override
                        public void onHover(LayoutWrapper layoutWrapper) {}
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
