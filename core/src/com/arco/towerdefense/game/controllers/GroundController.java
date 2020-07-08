package com.arco.towerdefense.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.arco.towerdefense.game.TowerDefenseGame.V_HEIGHT;

public class GroundController {
    private Texture grassImg;
    private Texture laneImg;
    private int groundSize;
    private int gridBlockSize;
    private int scale;

    private int viewportWidth;
    private int viewportHeight;
    Vector2 cursorLocation;
    ShapeRenderer shapeRenderer;

    public GroundController(String grassImgPath, String laneImgPath, int groundSize, int gridBlockSize, int viewWidth, int viewHeight) {
        grassImg = new Texture(grassImgPath);
        laneImg = new Texture(laneImgPath);

        this.groundSize = groundSize;
        this.gridBlockSize = gridBlockSize;
        this.scale = gridBlockSize*groundSize;

        viewportWidth = viewWidth;
        viewportHeight = viewHeight;

        cursorLocation = new Vector2(0, 0);

        shapeRenderer = new ShapeRenderer();
    }

    public int getGridWidth() {
        return viewportWidth / scale;
    }

    public int getGridHeight() {
        return viewportHeight / scale;
    }

    public void paint(SpriteBatch batch) {
        for(int x = 0; x <= this.getGridWidth(); x++) {
            for (int y = 0; y <= this.getGridHeight(); y++) {
                drawGridBlock(x, y, grassImg, batch);
            }
        }
        //drawLane(0, 1, 12, 1, batch);
    }

    private void drawGridBlock(int x, int y, Texture texture, SpriteBatch batch) {
        for (int i = 0; i < gridBlockSize; i++) {
            int realX = x*scale;

            realX += i*groundSize;

            for (int j = 0; j < gridBlockSize; j++) {
                int realY = y*scale;
                realY += j*groundSize;
                batch.draw(texture, realX, realY);
            }
        }
    }

    private void drawLane(int start_x, int start_y, int final_x, int final_y, SpriteBatch batch) {
        if(start_x == final_x) {
            for(int y = start_y; y < final_y; y++) {
                drawGridBlock(start_x, y, laneImg, batch);
            }
        }

        if(start_y == final_y) {
            for(int x = start_x; x < final_x; x++) {
                drawGridBlock(x, start_y, laneImg, batch);
            }
        }
    }

    public void update() {
        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = V_HEIGHT - Gdx.input.getY();

        for(int x = 0; x <= this.getGridWidth(); x++) {
            for (int y = 0; y <= this.getGridHeight(); y++) {
                int realx = x*scale;
                int realy = y*scale;
                if(cursorLocation.x >= realx-scale && cursorLocation.x <= realx && cursorLocation.y >= realy-scale && cursorLocation.y <= realy) {
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                    shapeRenderer.setColor(1,0,0,1);
                    shapeRenderer.rect(realx-scale, realy-scale, scale, scale);
                    shapeRenderer.end();
                }
            }
        }
    }

    public void dispose() {
        grassImg.dispose();
        laneImg.dispose();
        shapeRenderer.dispose();
    }
}
