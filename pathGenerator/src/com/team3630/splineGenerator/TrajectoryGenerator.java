package com.team3630.splineGenerator;

public class TrajectoryGenerator
{

    public TrajectoryGenerator()
    {

    }

    public static Trajectory generate(double startVel, double startHeading, double goalPos, double goalVel, double goalHeading)
    {
        Trajectory traj;


        //we don't have to go from/to v=0 but to the start/end velocity
        double startVelAdvantage = 0.5 * startVel * startVel / Constants.maxAcc;
        double goalVelAdvantage = 0.5 * goalVel * goalVel / Constants.maxAcc;
        
        double adjustedMaxVel = Math.min(Constants.maxVel, 
                                Math.sqrt(Constants.maxAcc * goalPos - startVelAdvantage - goalVelAdvantage));

        double tRampup = (adjustedMaxVel - startVel) / Constants.maxAcc;
        double xRampup = startVel * tRampup + 0.5 * Constants.maxAcc * tRampup * tRampup;

        double tRampdown = (adjustedMaxVel - goalVel) / Constants.maxAcc;
        double xRampdown = adjustedMaxVel * tRampdown - 0.5 * Constants.maxAcc * tRampdown * tRampdown;

        double xCruise = goalPos - xRampdown - xRampup;

        int time = (int) ((xCruise / adjustedMaxVel + tRampdown + tRampup) * Constants.samples + 0.5); //length of the array

        int filter1Length = (int) Math.ceil((adjustedMaxVel / Constants.maxAcc) * Constants.samples);

        double impulse = (goalPos / adjustedMaxVel * Constants.samples) - (startVel / Constants.maxAcc * Constants.samples)
                             + startVelAdvantage + goalVelAdvantage;

        traj = secondOrderFilter(filter1Length, 1, startVel, adjustedMaxVel, impulse, time);

        double headingChange = goalHeading - startHeading;
        for(int i = 1; i < traj.segments.length; i++)
        {
            traj.segments[i].heading = startHeading + headingChange * (traj.segments[i].pos) / traj.segments[traj.segments.length - 1].pos;
        }

        return traj;
    }

    public static Trajectory secondOrderFilter(int filter1Length, int filter2Length, double startVel, 
                                        double maxVel, double impulse, int length)
    {
        Trajectory traj = new Trajectory(length);

        if(length <= 0)
        {
            return null;
        }

        Trajectory.Segment lastSeg = new Trajectory.Segment();

        lastSeg.acc = 0;
        lastSeg.vel = startVel;
        lastSeg.pos = 0;

        double[] filter1 = new double[length];
        filter1[0] = (startVel / maxVel) * filter1Length;
        double filter2;
        for(int i = 1; i < length; i++)
        {
            double input = Math.min(impulse, 1);
            if(input < 1)
            {
                input -= 1;
                impulse = 0;
            }
            else
            {
                impulse -= input;
            }

            double lastFilter1;
            if(i > 0)
            {
                lastFilter1 = filter1[i-1];
            }
            else
            {
                lastFilter1 = filter1[0];
            }
            filter1[i] = Math.max(0.0, Math.min(filter1Length, lastFilter1 + input));

            filter2 = 0;
            for(int j = 1; j <= filter2Length; j++)
            {
                if(i - j < 0)
                {
                    break;
                }

                filter2 += filter1[i - j];
            }

            filter2 = filter2 / filter1Length;
            //System.out.println(filter2);

            traj.segments[i].vel = filter2 / filter2Length * maxVel;
            traj.segments[i].pos = (lastSeg.vel + traj.segments[i].vel) * 0.5 / Constants.samples + lastSeg.pos;
            traj.segments[i].x = traj.segments[i].pos;
            traj.segments[i].y = 0;
            traj.segments[i].acc = (traj.segments[i].acc - lastSeg.acc) * Constants.samples;

            lastSeg = traj.segments[i];
        }

        return traj;
    }


}