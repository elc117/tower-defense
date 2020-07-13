package com.arco.towerdefense.game.layouts;

import com.arco.towerdefense.game.utils.Consts;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class StackLayout {
    public static final int ORIENTATION_VERTICAL = 0;
    public static final int ORIENTATION_HORIZONTAL = 1;

    public static final int POSITION_BOTTOM_LEFT = 0;

    private SpriteBatch batch;
    private Array<Sprite> sprites;
    private int orientation;
    private float width = 0;
    private float height = 0;
    private float margin = 0;
    private float padding = 3;
    // False when we add a new sprite, so we need to recalculate some properties
    private boolean pristine;


    public StackLayout(SpriteBatch batch) {
        this.batch = batch;
        pristine = false;
        this.orientation = StackLayout.ORIENTATION_VERTICAL;
        sprites = new Array<>();
    }

    public StackLayout(SpriteBatch batch, int orientation) {
        this.batch = batch;
        pristine = false;
        this.orientation = orientation;
        sprites = new Array<>();
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }


    public void setMargin(float margin) {
        this.pristine = false;
        this.margin = margin;
    }

    private void drawAtPosVertical(float x, float y) {
        float offsetX = x, offsetY = y;
        for (Sprite sprite: sprites) {

            sprite.setPosition(offsetX, offsetY);
            sprite.draw(batch);

            offsetY += margin + sprite.getHeight();
        }
    }

    private void drawAtPosHorizontal(float x, float y) {
        float offsetX = x, offsetY = y;
        for (Sprite sprite: sprites) {

            sprite.setPosition(offsetX, offsetY);
            sprite.draw(batch);

            offsetX += margin + sprite.getWidth();
        }
    }

    // Entry point for drawing in a specific position
    public void drawAt(float x, float y) {
        if (this.orientation == ORIENTATION_VERTICAL) {
            this.drawAtPosVertical(x, y);
        } else {
            this.drawAtPosHorizontal(x, y);
        }
    }

    public void drawAtPos(int pos) {
        if (pos == POSITION_BOTTOM_LEFT) {
            drawAt(padding, padding);
        }
    }

    private float getHeight() {
        if (pristine) return this.height;

        float height = 0;

        if (orientation == ORIENTATION_VERTICAL) {
            for (Sprite sprite: sprites) {
                height = sprite.getHeight() + margin;
            }
            height = height > 0 ? height - margin : height;
        } else {
            for (Sprite sprite: sprites) {
                if (sprite.getHeight() > height) {
                    height = sprite.getHeight();
                }
            }
        }

        this.height = height;

        return height;
    }

    private float getWidth() {
        if (pristine) return this.width;

        float width = 0;

        if (orientation == ORIENTATION_HORIZONTAL) {
            for (Sprite sprite: sprites) {
                width = sprite.getWidth() + margin;
            }

            width = width > 0 ? width - margin : width;
        } else {
            for (Sprite sprite: sprites) {
                if (sprite.getWidth() > width) {
                    width = sprite.getWidth();
                }
            }
        }

        this.width = width;

        return width;
    }
}
