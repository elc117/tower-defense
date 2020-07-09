package com.arco.towerdefense.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import space.earlygrey.shapedrawer.ShapeDrawer;

import static com.arco.towerdefense.game.TowerDefenseGame.V_HEIGHT;

public class GroundController {
    private SpriteBatch batch;
    private Texture grassImg;
    private Texture laneImg;
    private int groundSize;
    private int gridBlockSize;
    private int scale;

    private int viewportWidth;
    private int viewportHeight;
    Vector2 cursorLocation;

    Texture texture;
    TextureRegion region;
    ShapeDrawer shapeDrawer;

    public GroundController(SpriteBatch batch, String grassImgPath, String laneImgPath, int groundSize, int gridBlockSize, int viewWidth, int viewHeight) {
        this.batch = batch;

        grassImg = new Texture(grassImgPath);
        laneImg = new Texture(laneImgPath);

        this.groundSize = groundSize;
        this.gridBlockSize = gridBlockSize;
        this.scale = gridBlockSize*groundSize;

        viewportWidth = viewWidth;
        viewportHeight = viewHeight;

        cursorLocation = new Vector2(0, 0);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        region = new TextureRegion(texture, 0, 0, 1, 1);

        shapeDrawer = new ShapeDrawer(batch, region);
    }

    public int getGridWidth() {
        return viewportWidth / scale;
    }

    public int getGridHeight() {
        return viewportHeight / scale;
    }

    public void paint() {
        for(int x = 0; x <= this.getGridWidth(); x++) {
            for (int y = 0; y <= this.getGridHeight(); y++) {
                drawGridBlock(x, y, grassImg);
            }
        }
//        drawLane(0, 1, 12, 1, batch);
    }

    private void drawGridBlock(int x, int y, Texture texture) {
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

    private void drawLane(int start_x, int start_y, int final_x, int final_y) {
        if(start_x == final_x) {
            for(int y = start_y; y < final_y; y++) {
                drawGridBlock(start_x, y, laneImg);
            }
        }

        if(start_y == final_y) {
            for(int x = start_x; x < final_x; x++) {
                drawGridBlock(x, start_y, laneImg);
            }
        }
    }

    public void update() {

        this.paint();

        cursorLocation.x = Gdx.input.getX();
        cursorLocation.y = V_HEIGHT - Gdx.input.getY();

        for(int x = 0; x <= this.getGridWidth(); x++) {
            for (int y = 0; y <= this.getGridHeight(); y++) {
                int realx = x*scale;
                int realy = y*scale;
                if(cursorLocation.x >= realx-scale && cursorLocation.x <= realx && cursorLocation.y >= realy-scale && cursorLocation.y <= realy) {
                    shapeDrawer.setColor(Color.RED);
                    shapeDrawer.rectangle(realx-scale, realy-scale, scale, scale);
                }
            }
        }
    }

    public void dispose() {
        grassImg.dispose();
        laneImg.dispose();
        texture.dispose();
    }
}
