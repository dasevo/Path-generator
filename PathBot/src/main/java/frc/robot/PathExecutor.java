package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PathExecutor
{
    public double leftTicks, rightTicks, distanceL, distanceR, lastErrorL, errorL, errorR, lastErrorR, outputL, outputR;
    public RobotMap robotMap;
    int i;

    public PathExecutor()
    {
        robotMap = new RobotMap();
        i = 0;
    }

    public void setEncoderPosition()
    {
        leftTicks = Constants.leftTicks;
        rightTicks = Constants.rightTicks;
    }

    public void ticks2metric()
    {
        distanceL = Constants.leftDist;
        distanceR = Constants.rightDist;
        SmartDashboard.putNumber("distL", distanceL);
        SmartDashboard.putNumber("distR", distanceR);
    }

    public void driveRobot()
    {
        
        if(i < Constants.leftPos.length)
        {
            errorL = Constants.leftPos[i] - distanceL;
            outputL = Constants.kP * errorL + Constants.kD * ((errorL - lastErrorL) / Constants.leftVel[i]) + (Constants.kV * Constants.leftVel[i] + Constants.kA * Constants.leftAcc[i]);
            lastErrorL = errorL;

            errorR = Constants.rightPos[i] - distanceR;
            outputR = Constants.kP * errorR + Constants.kD * ((errorR - lastErrorR) / Constants.rightVel[i]) + (Constants.kV * Constants.rightVel[i] + Constants.kA * Constants.rightAcc[i]);
            lastErrorR = errorR;
            i++;

            SmartDashboard.putNumber("leftOutput", outputL);
            SmartDashboard.putNumber("rightOutput", outputR);
        }
        else
        {
            System.out.println("not going");
            outputL = 0;
            outputR = 0;
        }
        
        /*
        if(i < Constants.leftVel.length)
        {
            outputL = Constants.leftVel[i] / 1.28;
            outputR = Constants.rightVel[i] / 1.28;
            i++;
        }
        else
        {
            outputL = 0;
            outputR = 0;

        }
        */
        SmartDashboard.putNumber("leftOutput", outputL);
        SmartDashboard.putNumber("rightOutput", outputR);
        robotMap.drive.tankDrive(-outputL, -outputR);
    }
}