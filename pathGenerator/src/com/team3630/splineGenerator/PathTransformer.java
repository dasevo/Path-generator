package com.team3630.splineGenerator;

public class PathTransformer
{

    static Trajectory base;

    public PathTransformer(Trajectory baseIn)
    {
        base = baseIn;
    }

    public static Trajectory[] generateLRTraj()
    {
        Trajectory[] result = new Trajectory[2];
        result[0] = new Trajectory(base.getLength());
        result[1] = new Trajectory(base.getLength());

        for(int i = 0; i<base.getLength(); i++)
        {
            Trajectory.Segment currSeg = base.segments[i];
            double sinHeading = Math.sin(currSeg.heading);
            double cosHeading = Math.cos(currSeg.heading);

            result[0].segments[i].x = currSeg.x + Constants.wheelbase / 2 * sinHeading;
            result[0].segments[i].y = currSeg.y - Constants.wheelbase / 2 * cosHeading;

            if(i > 0)
            {
                double distance = Math.sqrt((result[0].segments[i].x - result[0].segments[i-1].x) 
                                            * (result[0].segments[i].x - result[0].segments[i-1].x)
                                            + (result[0].segments[i].y - result[0].segments[i-1].y)
                                            * (result[0].segments[i].y - result[0].segments[i-1].y));

                result[0].segments[i].pos = result[0].segments[i-1].pos + distance;
                result[0].segments[i].vel = distance * Constants.samples;
                result[0].segments[i].acc = (result[0].segments[i].vel - result[0].segments[i-1].vel) * Constants.samples;
            }

            result[1].segments[i].x = currSeg.x - Constants.wheelbase / 2 * sinHeading;
            result[1].segments[i].y = currSeg.y + Constants.wheelbase / 2 * cosHeading;

            if(i >0)
            {
                double distance = Math.sqrt((result[1].segments[i].x - result[1].segments[i-1].x) 
                                            * (result[1].segments[i].x - result[1].segments[i-1].x)
                                            + (result[1].segments[i].y - result[1].segments[i-1].y)
                                            * (result[1].segments[i].y - result[1].segments[i-1].y));
                
                result[1].segments[i].pos = result[1].segments[i-1].pos + distance;
                result[1].segments[i].vel = distance * Constants.samples;
                result[1].segments[i].acc = (result[1].segments[i].vel - result[1].segments[i-1].vel) * Constants.samples;
            }
        }
        return result;
    }
}