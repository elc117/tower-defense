package com.arco.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroundController {
    private Texture grassImg;
    private Texture laneImg;
    private int groundSize;
    private int gridBlockSize;

    private int viewportWidth;
    private int viewportHeight;

    public GroundController(String grassImgPath, String laneImgPath, int groundSize, int gridBlockSize, int viewWidth, int viewHeight) {
        grassImg = new Texture(grassImgPath);
        laneImg = new Texture(laneImgPath);

        this.groundSize = groundSize;
        this.gridBlockSize = gridBlockSize;

        viewportWidth = viewWidth;
        viewportHeight = viewHeight;
    }

    public int getGridWidth() {
        return viewportWidth / (groundSize * gridBlockSize);
    }

    public int getGridHeight() {
        return viewportHeight / (groundSize * gridBlockSize);
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
            int realX = x*(gridBlockSize*groundSize);

            realX += i*groundSize;

            for (int j = 0; j < gridBlockSize; j++) {
                int realY = y*(gridBlockSize*groundSize);
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

    public void dispose() {
        grassImg.dispose();
        laneImg.dispose();
    }
}
