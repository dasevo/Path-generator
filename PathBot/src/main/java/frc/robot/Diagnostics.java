package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics
{

    RobotMap robotMap;
    public Diagnostics()
    {
        robotMap = new RobotMap();
    }

    public void printOut()
    {
        SmartDashboard.putNumber("right Encoder", Constants.rightTicks);
        SmartDashboard.putNumber("left Encoder", Constants.leftTicks);
    }

    public void resetEncoders()
    {
        robotMap.talonOne.setSelectedSensorPosition(0);
        robotMap.talonTwo.setSelectedSensorPosition(0);
    }

    public void getTicks()
    {
        Constants.leftTicks = robotMap.talonOne.getSelectedSensorPosition();
        Constants.rightTicks = robotMap.talonTwo.getSelectedSensorPosition();
    }

    public void calculateDist()
    {
        Constants.leftDist = (Constants.leftTicks / Constants.ratioL) * 2 * Math.PI * Constants.radius;
        Constants.rightDist = (Constants.rightTicks / Constants.ratioR) * 2 * Math.PI * Constants.radius;
    }
    
}