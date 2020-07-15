package com.arco.towerdefense.game.layouts;

import com.arco.towerdefense.game.layouts.enums.Orientation;
import com.arco.towerdefense.game.layouts.enums.Position;
import com.arco.towerdefense.game.layouts.wrappers.LayoutWrapper;
import com.arco.towerdefense.game.utils.Consts;
import com.arco.towerdefense.game.utils.Utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class StackLayout {
    private SpriteBatch batch;
    private Array<LayoutWrapper> wrappers;
    private Orientation orientation;
    private float width = 0;
    private float height = 0;
    private float margin = 0;
    private float padding = 0;

    // False when we add a new sprite, so we need to recalculate some properties
    private boolean pristine;

    private float stackPosX = 0, stackPosY = 0;


    public StackLayout(SpriteBatch batch) {
        this.batch = batch;
        pristine = false;
        this.orientation = Orientation.VERTICAL;
        wrappers = new Array<>();
    }

    public StackLayout(SpriteBatch batch, Orientation orientation) {
        this.batch = batch;
        pristine = false;
        this.orientation = orientation;
        wrappers = new Array<>();
    }

    public void addWrapper(LayoutWrapper wrapper) {
        wrappers.add(wrapper);
    }

    public void setMarginBetween(float margin) {
        this.pristine = false;
        this.margin = margin;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    private void drawAtPosVertical(float x, float y) {
        float offsetX = x, offsetY = y;
        for (LayoutWrapper wrapper: wrappers) {

            wrapper.sprite.setPosition(offsetX, offsetY);
            wrapper.sprite.draw(batch);

            offsetY += margin + wrapper.sprite.getHeight();
        }
    }

    private void drawAtPosHorizontal(float x, float y) {
        float offsetX = x, offsetY = y;
        for (LayoutWrapper wrapper: wrappers) {

            wrapper.sprite.setPosition(offsetX, offsetY);
            wrapper.sprite.draw(batch);

            offsetX += margin + wrapper.sprite.getWidth();
        }
    }

    // Entry point for drawing in a specific position
    public void drawAt(float x, float y) {
        stackPosX = x;
        stackPosY = y;
        if (this.orientation == Orientation.VERTICAL) {
            this.drawAtPosVertical(x, y);
        } else {
            this.drawAtPosHorizontal(x, y);
        }
    }

    public void drawAtPos(Position pos) {
        drawAt(getXForPos(pos), getYForPos(pos));
    }

    private float getXForPos(Position pos) {
        float width = this.getWidth();

        if (pos == Position.BOTTOM_LEFT || pos == Position.TOP_LEFT || pos == Position.LEFT_MIDDLE) {
            return padding;
        } else if (pos == Position.BOTTOM_RIGHT || pos == Position.RIGHT_MIDDLE || pos == Position.TOP_RIGHT) {
            return Consts.V_WIDTH - width - padding;
        } else if (pos == Position.RIGHT_MIDDLE || pos == Position.LEFT_MIDDLE || pos == Position.MIDDLE) { // X is middle (POSITION_BOTTOM_MIDDLE || POSITION_TOP_MIDDLE || POSITION_MIDDLE)
            return (Consts.V_WIDTH - width)/2;
        }

        return 0;
    }

    private float getYForPos(Position pos) {
        float height = this.getHeight();

        if (pos == Position.BOTTOM_LEFT || pos == Position.BOTTOM_RIGHT || pos == Position.BOTTOM_MIDDLE) {
            return padding;
        } else if (pos == Position.TOP_LEFT || pos == Position.TOP_MIDDLE || pos == Position.TOP_RIGHT) {
            return Consts.V_HEIGHT - height - padding;
        } else if (pos == Position.RIGHT_MIDDLE || pos == Position.LEFT_MIDDLE || pos == Position.MIDDLE) {
            return (Consts.V_HEIGHT - height)/2;
        }

        return 0;
    }

    private float getHeight() {
        if (pristine) return this.height;

        float height = 0;

        if (orientation == Orientation.VERTICAL) {
            for (LayoutWrapper wrapper: wrappers) {
                height += wrapper.sprite.getHeight() + margin;
            }
            height = height > 0 ? height - margin : height;
        } else {
            for (LayoutWrapper wrapper: wrappers) {
                if (wrapper.sprite.getHeight() > height) {
                    height = wrapper.sprite.getHeight();
                }
            }
        }

        this.height = height;

        return height;
    }

    private float getWidth() {
        if (pristine) return this.width;

        float width = 0;

        if (orientation == Orientation.HORIZONTAL) {
            for (LayoutWrapper wrapper: wrappers) {
                width += wrapper.sprite.getWidth() + margin;
            }

            width = width > 0 ? width - margin : width;
        } else {
            for (LayoutWrapper wrapper: wrappers) {
                if (wrapper.sprite.getWidth() > width) {
                    width = wrapper.sprite.getWidth();
                }
            }
        }

        this.width = width;

        return width;
    }

    public boolean isCursorInside() {
        return Utils.isCursorInside(stackPosX, stackPosY, getWidth(), getHeight());
    }

    public void handleMouse() {
        // This function should be called only if we are sure that the cursor is inside
        // the StackLayout bounds for performance reasons.
        for(LayoutWrapper wrapper: wrappers) {
            if (Utils.isCursorInside(wrapper.sprite)) {
                wrapper.triggerOnHover();

                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    wrapper.triggerOnClick();
                }
            }
        }
    }
}
