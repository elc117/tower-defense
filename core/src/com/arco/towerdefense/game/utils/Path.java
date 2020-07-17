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

        //checkPoints.add(new Vector2(0, 14));
        //checkPoints.add(new Vector2(0, 9));
        //checkPoints.add(new Vector2(5, 9));

        checkPoints.add(new Vector2(0,5));
        checkPoints.add(new Vector2(10,5));
        checkPoints.add(new Vector2(10,7));
        checkPoints.add(new Vector2(12,7));
        checkPoints.add(new Vector2(12,12));
        //checkPoints.add(new Vector2(15,12));
        checkPoints.add(new Vector2(9,12));
        checkPoints.add(new Vector2(9,10));
        //checkPoints.add(new Vector2(12,2));
        //checkPoints.add(new Vector2(20,2));
        //checkPoints.add(new Vector2(10,2));
        //checkPoints.add(new Vector2(10,0));

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
