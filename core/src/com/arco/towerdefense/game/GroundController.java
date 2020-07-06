package com.arco.towerdefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroundPainter {
    private Texture grassImg;
    private Texture laneImg;
    private int groundSize;
    private int gridBlockSize;

    private int viewportWidth;
    private int viewportHeight;

    public GroundPainter(String grassImgPath, String laneImgPath, int groundSize, int gridBlockSize, int viewWidth, int viewHeight) {
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
}
