package com.arco.towerdefense.game.utils;

import com.arco.towerdefense.game.GameSingleton;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Utils {
    public static boolean isCursorInside(float x, float y, float width, float height) {
        return (new Rectangle(x, y, width, height)).contains(GameSingleton.getInstance().getCursorLocation());
    }

    public static float getScreenCenterX() {
        return Consts.V_WIDTH / 2;
    }

    public static float getScreenCenterY() {
        return Consts.V_HEIGHT / 2;
    }
}
