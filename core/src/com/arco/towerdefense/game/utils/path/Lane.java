package com.arco.towerdefense.game.utils.path;

public class Lane {
    private int startX;
    private int startY;
    private int finalX;
    private int finalY;

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public Lane(int startX, int startY, int finalX, int finalY) {
        this.startX = startX;
        this.startY = startY;
        this.finalX = finalX;
        this.finalY = finalY;
    }
}
