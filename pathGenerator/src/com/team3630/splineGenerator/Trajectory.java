package com.team3630.splineGenerator;

public class Trajectory
{

    public static class Segment
    {
        public double pos;
        public double vel;
        public double acc;
        public double heading;
        public double x;
        public double y;

        public Segment()
        {

        }

        public Segment(double pos, double vel, double acc, double heading, double x, double y)
        {
            this.pos = pos;
            this.vel = vel;
            this.acc = acc;
            this.heading = heading;
            this.x = x;
            this.y = y;
        }   

        public Segment(Segment copySegment)
        {
            this.pos = copySegment.pos;
            this.vel = copySegment.pos;
            this.acc = copySegment.acc;
            this.heading = copySegment.heading;
            this.x = copySegment.x;
            this.y = copySegment.y;
        }

        public String toString()
        {
            return "pos: " + this.pos + "; vel: " + this.vel + "; acc: " + this.acc + "; heading: " 
            + this.heading + "; x: " + this.x + "; y: " + this.y + ";";
        }
    }

    Segment[] segments;

    Trajectory(int length)
    {
        segments = new Segment[length];
        for(int i = 0; i<length; i++)
        {
            segments[i] = new Segment();
        }
    }

    Trajectory(Segment[] segments)
    {
        this.segments = segments;
    }

    public Segment getSegment(int index)
    {
        if(index<segments.length)
        {
            return segments[index];
        }
        else
        {
            return new Segment();
        }
    }

    public void setSegment(Segment segment, int index)
    {
        if(index<segments.length)
        {
            segments[index] = segment;
        }
    }

    public String toString()
    {
        String str = "";
        for(int i = 0; i<segments.length; i++)
        {
            str += segments[i].pos + "\t";
            str += segments[i].vel + "\t";
            str += segments[i].acc + "\t";
            str += segments[i].heading + "\t";
            str += segments[i].x + "\t";
            str += segments[i].y + "\n";
        }
        return str;
    }

    public Trajectory copy()
    {
        Trajectory copied = new Trajectory(segments.length);
        copied.segments = this.segments;
        return copied;
    }

    public int getLength()
    {
        return segments.length;
    }

}