package com.arco.towerdefense.game.drawer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class GroundDrawer{
    private  SpriteBatch batch;
    private Texture grassImg;
    private Texture laneImg;

    private int groundSize;
    private int gridBlockSize;
    private int scale;

    private Rectangle viewRectangle;
    private Texture textureShapeDrawer;
    private TextureRegion regionShapeDrawer;
    private ShapeDrawer shapeDrawer;

    public GroundDrawer(SpriteBatch batch, int gridBlockSize, Rectangle viewRectangle) {
        this.batch = batch;
        grassImg = new Texture("ground/grasstop.png");
        laneImg = new Texture("ground/dirt.png");

        groundSize = grassImg.getHeight();
        this.gridBlockSize = gridBlockSize;
        this.scale = gridBlockSize*groundSize;
        this.viewRectangle = viewRectangle;

        initShapeDrawer();
    }

    private void initShapeDrawer() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        textureShapeDrawer = new Texture(pixmap);
        pixmap.dispose();
        regionShapeDrawer = new TextureRegion(textureShapeDrawer, 0, 0, 1, 1);

        shapeDrawer = new ShapeDrawer(batch, regionShapeDrawer);
    }

    public void drawGround() {
        batch.disableBlending();
        for(int x = 0; x <= this.getGridWidth(); x++) {
            for (int y = 0; y <= this.getGridHeight(); y++) {
                drawGridBlock(x, y, grassImg);
            }
        }
        drawLane(0, 1, 12, 1);
        batch.enableBlending();
    }

    public void drawGroundSelection(int gridX, int gridY) {
        shapeDrawer.setColor(Color.RED);
        shapeDrawer.rectangle(gridX*scale, gridY*scale, scale, scale);
    }

    public int getGridWidth() {
        return Math.round(viewRectangle.width) / scale;
    }

    public int getGridHeight() {
        return Math.round(viewRectangle.height) / scale;
    }

    public int getScale() {
        return scale;
    }

    public void dispose() {
        grassImg.dispose();
        laneImg.dispose();
        textureShapeDrawer.dispose();
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

    private void drawLane(int startX, int startY, int finalX, int finalY) {
        if(startX == finalX) {
            for(int y = startY; y < finalY; y++) {
                drawGridBlock(startX, y, laneImg);
            }
        }

        if(startY == finalY) {
            for(int x = startX; x < finalX; x++) {
                drawGridBlock(x, startY, laneImg);
            }
        }
    }
}
