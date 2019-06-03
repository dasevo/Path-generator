package com.team3630.util;

public class StampedeMath// a growing class of whatever calculations we want to use
{

    /**
     * Converts angles in degrees to radians
     * @param degrees size of an angle in degrees
     * @return size of the angle in radians
     */
    public static double degreesToRadians(double degrees)
    {
        double conversion = 180/Math.PI;
        double radians = degrees*conversion;
        return radians;
    }

    /**
     * Converts angles in radians to degrees
     * @param radians size of an angle in radians
     * @return size of the angle in degrees
     */
    public static double radiansToDegrees(double radians)
    {
        double conversion = Math.PI/180;
        double degrees = radians*conversion;
        return degrees;
    }

    /**
     * Compares two numbers and returns if they are almost the same - in a range of 1E-6
     * @param a to be compared to b
     * @param b to be compared to a
     * 
     */
    public static boolean almostTheSame(double a, double b)
    {
        boolean almostEqual = Math.abs(a-b) < 1E-6;
        return almostEqual;
    }

    /**
     * Calculates a difference between two angles and bouns it in the range betwen -pi and pi
     * @param from 
     * @param to 
     * @return bounded diffenrence of angles in radians
     */
    public static double boundedAngleDifferenceRadians(double from, double to)
    {
        double difference = to - from;
        while(difference>Math.PI)
        {
            difference-=2 * Math.PI;
        }
        while(difference<-Math.PI)
        {
            difference+=2 * Math.PI;
        }
        return difference;
    }

    public static double boundAngle0to2pi(double angle)
    {
        while(angle >= 2 * Math.PI)
        {
            angle -= 2 * Math.PI;
        }
        while(angle <= 2 * Math.PI)
        {
            angle += 2 * Math.PI;
        }
        return angle;
    }
}
