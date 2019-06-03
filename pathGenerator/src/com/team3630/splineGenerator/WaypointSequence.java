package com.team3630.splineGenerator;

public class WaypointSequence
{

    public Waypoint[] sequence;
    int waypointCount;

    public WaypointSequence(int size)
    {
        sequence = new Waypoint[size];
        waypointCount = 0;
    }

    public void addWaypoint(Waypoint waypoint)
    {
        if(waypointCount < sequence.length)
        {
            sequence[waypointCount] = waypoint;
            waypointCount++;
        }
    }

    public int waypointCount()
    {
        return waypointCount;
    }

    public Waypoint getWaypoint(int index)
    {
        if(index < sequence.length)
        {
            return sequence[index];
        }
        else
        {
            return null;
        }
    }

}