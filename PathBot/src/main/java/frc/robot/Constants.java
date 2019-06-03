package frc.robot;

public class Constants
{
    public static double leftTicks = 0;
    public static double rightTicks = 0;
    public static double rightDist = 0;
    public static double leftDist = 0;
    public static double drivePIDout = 0;
    public static double turnPIDout = 0;

    public static final double driveKp = 0.5;
    public static final double driveKi = 0;
    public static final double driveKd = 0;
    public static final double turnKp = 0.013;
    public static final double turnKi = 0;
    public static final double turnKd = 0;

    public static final double kP = 1.23;
    public static final double kI = 0;
    public static final double kD = 0.001;
    public static final double kV = 1/1.3;
    public static final double kA = 0; //0.12

    public static final double ratioL = -332;
    public static final double ratioR = 332;
    public static final double radius = 0.0762;
    public static final double samples = 100;

    public static double[] leftPos = {};
    public static double[] leftVel = {};
    public static double[] leftAcc = {};
    public static double[] rightPos = {};
    public static double[] rightVel = {};
    public static double[] rightAcc = {};
}