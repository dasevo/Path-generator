package com.team3630.splineGenerator;

public class Waypoint
{

    double x;
    double y;
    double heading;

    /**
     * Defines a waypoint on the path
     * @param x x-coordinate of the waypoint
     * @param y y-coordinate of the waypoint
     * @param heading heading of the robot at the waypoint in degrees
     */
    public Waypoint(double x, double y, double heading)
    {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

}