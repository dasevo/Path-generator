package com.team3630.splineGenerator;

import com.team3630.util.StampedeMath;

public class Spline
{

    double _a; //ax^3
    double _b; //bx^2
    double _c; //cx

    double xOrigin;
    double yOrigin;
    double xFinal;
    double yFinal;
    double directDistance;
    double directAngle;

    public Spline()
    {

    }


    public static Spline generateSplineFromWaypoints(Waypoint start, Waypoint goal, Spline target)
    {
        return generateSpline(start.x, start.y, start.heading, goal.x, goal.y, goal.heading, target);
    }

    /**
     * 
     * @param x0
     * @param y0
     * @param heading0 in radians
     * @param x1
     * @param y1
     * @param heading1 in radians
     */
    public static Spline generateSpline(double x0, double y0, double heading0, double x1, double y1, double heading1, Spline target)
    {
        target.xOrigin = x0;
        target.yOrigin = y0;
        target.xFinal = x1;
        target.yFinal = y1;

        double directWay = Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
        if(directWay == 0)
        {
            //do not create a spline -> error
            target = null;
            return target;
        }

        target.directDistance = directWay;
        target.directAngle = Math.atan2(y1 - y0, x1 - x0);

        double theta0_offset = StampedeMath.boundedAngleDifferenceRadians(target.directAngle, heading0);
        double theta1_offset = StampedeMath.boundedAngleDifferenceRadians(target.directAngle, heading1);
//        double theta0_offset = ChezyMath.getDifferenceInAngleRadians(directAngle, heading0);
//        double theta1_offset = ChezyMath.getDifferenceInAngleRadians(directAngle, heading1);
//        System.out.println(StampedeMath.boundedAngleDifferenceRadians(directAngle, heading0));

        if(Math.abs(theta0_offset - theta1_offset) >= Math.PI / 2)
        {
            //do not create a spline -> error
            target = null;
            return null;
        }

        double dTheta0 = Math.tan(theta0_offset);
        double dTheta1 = Math.tan(theta1_offset);

        target._a = (dTheta1 + dTheta0) / (target.directDistance * target.directDistance);
        target._b = -(2*dTheta0 + dTheta1) / target.directDistance;
        target._c = dTheta0;

        System.out.println(target._a + "   " + target._b + "  " + target._c);

        return target;
        
    }

    public double calculateLength()
    {
        double arcLength = 0;
        double lastArcLength = 0;
        double t;
        double dydt;
        double integrand;
        double lastIntegrand = Math.sqrt(1 + (derivativeAt(0) * derivativeAt(0))) / Constants.derivIntervals;


        for(int i = 0; i<Constants.derivIntervals; i++)
        {
            t = ((double) (i)) / Constants.derivIntervals;
            dydt = derivativeAt(t);
            integrand = Math.sqrt(1 + dydt * dydt) / Constants.derivIntervals;
            arcLength += (integrand + lastIntegrand) / 2;
            lastIntegrand = integrand;
        }

        arcLength = arcLength * directDistance;

        return arcLength;
    }

    public double derivativeAt(double value) //in percent of the position
    {
        double percentage = Math.max(Math.min(value, 1), 0);

        //System.out.println(percentage);
        double position = percentage * directDistance;
        double dydt = 3 * _a * position * position + 2 * _b * position + _c;
        //System.out.println(_a + "   " + _b);
        return dydt;
    }

    public double percentageFromDist(double dist)
    {
        double arcLength = 0.0;
        double t = 0;
        double lastArcLength = 0;
        double dydt;
        double integrand;
        //System.out.println(derivativeAt(0));
        double lastIntegrand = Math.sqrt(1 + derivativeAt(0) * derivativeAt(0)) / Constants.derivIntervals;
        //System.out.println(derivativeAt(0));
        dist /= directDistance;

        for(int i = 0; i < Constants.derivIntervals; i++)
        {
            t = ((double) (i)) / Constants.derivIntervals;
            dydt = derivativeAt(t);
            integrand = Math.sqrt(1 + dydt * dydt) / Constants.derivIntervals;
            arcLength = lastArcLength + ((integrand + lastIntegrand) / 2);

            

            if(arcLength > dist)
            {
                //System.out.println("longArc");
                break;
            }
            lastIntegrand = integrand;
            lastArcLength = arcLength;
        }
        
        double interpolated = t;
        if(arcLength != lastArcLength)
        {
            interpolated += ((dist - lastArcLength) / (arcLength - lastArcLength) - 1) / (double) Constants.derivIntervals;
        }
        //System.out.println(interpolated);
        return interpolated;
    }

    public double angleAt(double percentage)
    {
        double angle = StampedeMath.boundAngle0to2pi(Math.atan(derivativeAt(percentage) + directAngle));
        return angle;
    }

    public double[] getXandY(double percentage)
    {
        double[] toReturn = new double[2];

        percentage = Math.max(Math.min(percentage, 1), 0);
        double currentX = percentage * directDistance;
        //System.out.println(percentage);
        double currentY = _a * currentX * currentX * currentX + _b * currentX * currentX + _c * currentX;

        double cosHeading = Math.cos(directAngle);
        double sinHeading = Math.sin(directAngle);

        toReturn[0] = currentX * cosHeading - currentY * sinHeading + xOrigin;
        toReturn[1] = currentX * sinHeading + currentY * cosHeading + yOrigin;

        return toReturn;
    }

    public String toString()
    {
        return _a + "x^3 + " + _b + "x^2 + " + _c + "x";
    }

}