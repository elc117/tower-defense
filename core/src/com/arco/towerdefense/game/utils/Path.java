package com.arco.towerdefense.game.utils;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Path {

    //checkPoints to guide enemy way
    private ArrayList<Vector2> checkPoints;

    //lanes to draw
    private ArrayList<Lane> lanes;

    public Path() {
        this.checkPoints = new ArrayList<>();
        this.lanes = new ArrayList<>();
    }

    public ArrayList<Vector2> getCheckPoints() {
        return checkPoints;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    public void setCheckPoints() {

        checkPoints.add(new Vector2(0, 6));
        checkPoints.add(new Vector2(6, 6));
        checkPoints.add(new Vector2(6, 12));
        checkPoints.add(new Vector2(14, 12));
        checkPoints.add(new Vector2(14, 10));
        checkPoints.add(new Vector2(10, 10));
        checkPoints.add(new Vector2(10, 6));
        checkPoints.add(new Vector2(18, 6));
        checkPoints.add(new Vector2(18, 12));
        checkPoints.add(new Vector2(25, 12));

        setLanes();
    }

    private void setLanes() {
        for (int i = 0; i < checkPoints.size() - 1; i++) {
            Vector2 checkPoint = checkPoints.get(i);
            Vector2 nextPoint = checkPoints.get(i+1);

            lanes.add(new Lane((int) checkPoint.x, (int) checkPoint.y, (int) nextPoint.x, (int) nextPoint.y));
        }
    }

    public Vector2 returnNextCheckPoint(Vector2 ant) {
        int num = checkPoints.indexOf(ant);

        if(checkPoints.get(num+1) == null)
            return null;

        return checkPoints.get(num+1);
    }

    public Vector2 returnStartCheckPoint() {
        return checkPoints.get(0);
    }

    public Vector2 returnFinalCheckPoint() {
        int index = checkPoints.size() -1 ;

        return checkPoints.get(index);
    }


}
