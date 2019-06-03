package com.team3630.splineGenerator;


import java.io.IOException;

import com.team3630.util.*;

public class Main
{
    static Spline spline;

    public static Trajectory left, right;
    public static ArrayGeneration getArr;
    public static void main(String[] args) throws IOException
    {
        WaypointSequence sequence = new WaypointSequence(3);
        sequence.addWaypoint(new Waypoint(0,0,0));
        sequence.addWaypoint(new Waypoint(2, 1.5, 0));
        sequence.addWaypoint(new Waypoint(4, 0, 0));
        sequence.addWaypoint(new Waypoint(6, 1.5, 0));
        //sequence.addWaypoint(new Waypoint(1, -1, 0));

        Path path = PathGenerator.generatePath(sequence, "name");
        PathTransformer transformer = new PathTransformer(path.base);
        Trajectory[] pair = PathTransformer.generateLRTraj();

        left = pair[0];
        right = pair[1];

        ArrayGeneration getArr = new ArrayGeneration(left, right);
        //System.out.println(path.trajPair[0].getSegment(100).y + "||||||" + path.trajPair[1].getSegment(100).y);
        getArr.setArrays();
        //getArr.printArrays();
        getArr.writeData();
        
        
    }
}