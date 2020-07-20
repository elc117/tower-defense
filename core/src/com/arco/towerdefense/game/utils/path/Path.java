package com.arco.towerdefense.game.utils.path;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Path {

    private ArrayList<Vector2> checkPoints;
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
        checkPoints.add(new Vector2(24, 12));


        setLanes();
    }

    private void setLanes() {
        for (int i = 0; i < checkPoints.size() - 1; i++) {
            Vector2 checkPoint = checkPoints.get(i);
            Vector2 nextPoint = checkPoints.get(i+1);

            lanes.add(new Lane((int) checkPoint.x, (int) checkPoint.y, (int) nextPoint.x, (int) nextPoint.y));
        }
    }
}
