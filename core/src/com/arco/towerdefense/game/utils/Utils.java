package com.arco.towerdefense.game.utils;

import com.arco.towerdefense.game.GameSingleton;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Utils {
    private static Vector2 utilVector = new Vector2();

    public static boolean isCursorInside(float x, float y, float width, float height) {
        return (new Rectangle(x, y, width, height)).contains(GameSingleton.getInstance().getCursorLocation());
    }

    public static boolean isCursorInside(Sprite sprite) {
        return isCursorInside(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public static boolean isInsideRectangle(Rectangle rect, float x, float y) {
        utilVector.x = x;
        utilVector.y = y;
        return rect.contains(utilVector);
    }

    public static float getScreenCenterX() {
        return Consts.V_WIDTH / 2;
    }

    public static float getScreenCenterY() {
        return Consts.V_HEIGHT / 2;
    }

    public static String getStringFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String allText = "";
            String line;

            while ((line = reader.readLine()) != null) {
                allText = allText.concat(line);
            }

            reader.close();

            return allText;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
}
