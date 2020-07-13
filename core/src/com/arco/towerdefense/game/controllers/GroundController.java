
package com.arco.towerdefense.game.controllers;

import com.arco.towerdefense.game.drawer.GroundDrawer;
import com.arco.towerdefense.game.entities.TowerEntity;
import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GroundController {
    private GroundDrawer groundDrawer;
    private Vector2 cursorLocation;
    private Array<TowerEntity> towers;

    private Rectangle viewRectangle;

    public GroundController(SpriteBatch batch,  int gridBlockSize, int viewWidth, int viewHeight) {
        viewRectangle = new Rectangle(0, 0, viewWidth, viewHeight);
        groundDrawer = new GroundDrawer(batch, gridBlockSize, viewRectangle);
        cursorLocation = new Vector2(0, 0);
        towers = new Array<>();
    }

    private void addTower(int x, int y) {
        towers.add(new TowerEntity(x, y));
    }

    public void update() {
        groundDrawer.drawGround();
        // TODO: Criar a função que desenha a torre. Obs.: N pode usar o groundDrawer.drawGridBlock
        //  pq ele so serve pra desenhar os blocos do chão. Tem q criar a função pra isso. Acho que
        //  a torre que coloquei la ta mto grande (64x64) redimensiona pra 32x32 e ver. Seria assim:
        // groundDrawer.drawTowers(towers);
        this.handleCursor();
    }

    public void handleCursor() {
        updateCursor();

        if (viewRectangle.contains(cursorLocation) || true) {
            int gridX = (int) Math.ceil(cursorLocation.x / groundDrawer.getScale());
            int gridY = (int) Math.ceil(cursorLocation.y / groundDrawer.getScale());

            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                this.addTower(gridX, gridY);
            }

            groundDrawer.drawGroundSelection(gridX - 1 , gridY - 1);
        }
    }

    private void updateCursor() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = Consts.V_HEIGHT - Gdx.input.getY();
    }

    public void dispose() {
        this.groundDrawer.dispose();
    }


}