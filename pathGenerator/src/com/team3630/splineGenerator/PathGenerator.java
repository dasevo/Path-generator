package com.team3630.splineGenerator;

public class PathGenerator
{

    public static Path generatePath(WaypointSequence waypoints, String name)
    {
        Path generatedPath = new Path(name, generateBaseFromSeq(waypoints));
        return generatedPath;
    }

    public static Trajectory generateBaseFromSeq(WaypointSequence waypoints)
    {
        return generateFromPath(waypoints);
    }

    public static Trajectory generateFromPath(WaypointSequence path)
    {
        if(path.sequence.length < 2)
        {
            System.out.println("short waypoints");
            return null;
        }

        Spline[] splines = new Spline[path.sequence.length - 1];
        double[] splineLengths = new double[splines.length];
        double totalDistance = 0;

        for(int i = 0; i < splines.length; i++)
        {
            Spline tempSpline = new Spline();
            tempSpline = Spline.generateSplineFromWaypoints(path.getWaypoint(i), path.getWaypoint(i + 1), tempSpline);

            if(tempSpline == null)
            {
                return null;
            }

            splines[i] = tempSpline;
            splineLengths[i] = splines[i].calculateLength();
            totalDistance += splineLengths[i];

        }

        Trajectory traj = TrajectoryGenerator.generate(0.0, path.getWaypoint(0).heading, totalDistance, 0.0, path.getWaypoint(1).heading);

        int splineIndex = 0;
        double currentStartPos = 0.0;
        double processedLength = 0.0;

        for(int i = 0; i < traj.segments.length; i++)
        {
            double currentPos = traj.getSegment(i).pos;

            boolean splineFound = false;
            while(!splineFound)
            {
                double relCurrentPos = currentPos - currentStartPos;

                if(relCurrentPos <= splineLengths[splineIndex])
                {
                    double percentage = splines[splineIndex].percentageFromDist(relCurrentPos);
                    traj.getSegment(i).heading = splines[splineIndex].angleAt(percentage);
                    double[] coordinates = splines[splineIndex].getXandY(percentage);
                    traj.segments[i].x = coordinates[0];
                    traj.segments[i].y = coordinates[1];
                    splineFound = true;
                    //System.out.println(traj.segments[i].x + "   " + traj.segments[i].y);
                }
                else if(splineIndex < splines.length -1)
                {
                    processedLength += splineLengths[splineIndex];
                    currentStartPos = processedLength;
                    splineIndex++;
                }
                else
                {
                    traj.getSegment(i).heading = splines[splines.length - 1].angleAt(1);
                    double[] coordinates = splines[splines.length - 1].getXandY(1);
                    traj.getSegment(i).x = coordinates[0];
                    traj.getSegment(i).y = coordinates[1];
                    splineFound = true;
                    //System.out.println(traj.segments[i].x + "   " + traj.segments[i].y);
                }
                
            }
        }
        return traj;
    }

}