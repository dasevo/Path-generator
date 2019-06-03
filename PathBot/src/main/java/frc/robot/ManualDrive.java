package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualDrive
{
    RobotMap robotMap;
    double leftTicks, rightTicks, distanceR, distanceL, tempDist, velocity;
    Timer speed, delay;

    public ManualDrive()
    {
        robotMap = new RobotMap();
        speed = new Timer();
        delay = new Timer();
    }

    public void driveRobot()
    {
        robotMap.drive.arcadeDrive(0.8*Robot.oi.getLeftY(), 0.8*Robot.oi.getRightX());
    }

    public void resetEncoders()
    {
        robotMap.talonOne.setSelectedSensorPosition(0);
        robotMap.talonTwo.setSelectedSensorPosition(0);
    }

    public void measureSpeed()
    {
        if(Robot.oi.buttonA.get())
        {
            delay.start();
        }
        if(delay.get() >= 1)
        {
            delay.reset();
            delay.stop();
            resetEncoders();
            speed.start();
        }
        if(speed.get() >= 2)
        {
            tempDist = (distanceL + distanceR) / 2;
            velocity = tempDist / 2;
            SmartDashboard.putNumber("key", tempDist);
            SmartDashboard.putNumber("max velocity", velocity);
            speed.reset();
            speed.stop();
        }

    }

    public void measureAcceleration()
    {

    }

    public void setEncoderPosition()
    {
        leftTicks = robotMap.talonOne.getSelectedSensorPosition();
        rightTicks = robotMap.talonTwo.getSelectedSensorPosition();
    }

    public void ticks2metric()
    {
        distanceL = (leftTicks / Constants.ratioL) * 2 * Math.PI * Constants.radius;
        distanceR = (rightTicks / Constants.ratioR) * 2 * Math.PI * Constants.radius;
        SmartDashboard.putNumber("distanceL", distanceL);
        SmartDashboard.putNumber("distanceR", distanceR);
    }
}