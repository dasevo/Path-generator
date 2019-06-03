package com.team3630.splineGenerator;

public class Path
{

    String name;
    static Trajectory[] trajPair;
    static Trajectory base;

    public Path(String name, Trajectory base)
    {
        this.name = name;
        this.base = base;
    }

    public Path(String name, Trajectory[] pair)
    {
        this.name = name; 
        this.trajPair = pair;
    }

    public Path()
    {

    }

    public String getName()
    {
        return name;
    }

}