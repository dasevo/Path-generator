package com.team3630.splineGenerator;

import javax.swing.text.Segment;

import com.team3630.util.StampedeMath;

public class TrajectoryGenerator2
{

    public TrajectoryGenerator2()
    {

    }

    public static Trajectory generate(double startVel, double startHeading, double goalPos, double goalVel, double goalHeading)
    {
        Trajectory traj;

        double spikeVel = Math.sqrt(Constants.maxAcc * goalPos + (startVel * startVel * 0.5) + (goalVel * goalVel * 0.5));

        double adjustedMaxVel = Math.min(Constants.maxVel, spikeVel);
        System.out.println(adjustedMaxVel);

        double tRampup = (adjustedMaxVel - startVel) / Constants.maxAcc;
        double xRampup = startVel * tRampup + 0.5 * Constants.maxAcc * tRampup * tRampup;

        double tRampdown = (adjustedMaxVel - goalVel) / Constants.maxAcc;
        double xRampdown = adjustedMaxVel * tRampdown - 0.5 * Constants.maxAcc * tRampdown * tRampdown;

        double tMaxSpeed;

        if(StampedeMath.almostTheSame(xRampup + xRampdown, goalPos))
        {
            tMaxSpeed = adjustedMaxVel;
        }
        else
        {
            tMaxSpeed = (goalPos - (xRampdown + xRampup)) / adjustedMaxVel;
        }

        double xCruise = goalPos - xRampdown - xRampup;

        int time = (int) ((xCruise / adjustedMaxVel + tRampdown + tRampup) * Constants.samples + 0.5); //length of the array

        traj = new Trajectory(time);
        Trajectory.Segment last = new Trajectory.Segment();

        double lastAcc = 0;
        double lastVel = startVel;
        double lastPos = 0;


        double timeElapsed = 0;

        for(int i = 1; i < time; i++)
        {
            if(timeElapsed <= tRampup)
            {
                traj.segments[i].vel = lastVel + Constants.maxAcc * Constants.iterationT;
            }
            else if(timeElapsed <= tRampup + tMaxSpeed)
            {
                traj.segments[i].vel = lastVel;
            }
            else if(timeElapsed <= tRampup + tMaxSpeed + tRampdown)
            {
                traj.segments[i].vel = lastVel - Constants.maxAcc * Constants.iterationT;
                
                System.out.println(traj.segments[i].vel);
            }
            else
            {
                traj.segments[i].vel = 0;
            }


            traj.segments[i].pos = (lastVel + traj.segments[i].vel) * 0.5 / Constants.samples + lastPos;
            traj.segments[i].x = traj.segments[i].pos;
            traj.segments[i].y = 0;
            traj.segments[i].acc = (traj.segments[i].acc - lastAcc) * Constants.samples;
            lastVel = traj.segments[i].vel;
            lastPos = traj.segments[i].pos;
            lastAcc = traj.segments[i].acc;
            timeElapsed+=0.02;
        }

        System.out.println(traj.segments[150].vel);
        return traj;
    }

}