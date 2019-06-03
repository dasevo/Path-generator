package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import frc.robot.Constants;

public class DrivePID implements PIDOutput
{

    public PIDController driveController;
    EncoderSource source;
    public MyCustomTolerance driveTolerance;

    public DrivePID()
    {

    }

    public void config()
    {
        driveTolerance = new MyCustomTolerance();
        source = new EncoderSource();
        driveController = new PIDController(Constants.driveKp, Constants.driveKi, Constants.driveKd, source, this);
        driveController.setOutputRange(-0.8, 0.8);
        //driveController.setTolerance(driveTolerance);
        driveController.setAbsoluteTolerance(0.4);
        driveController.disable();
    }

    public void pidWrite(double output)
    {
        Constants.drivePIDout = output;
    }


    public class MyCustomTolerance implements Tolerance 
    {
        private final double m_posTolerance;
        private final double m_velocityLimit;

        MyCustomTolerance() 
        {
            m_posTolerance = 0.5;
            m_velocityLimit = 0.1; 
        }

        @Override
        public boolean onTarget() 
        {
            return Math.abs(driveController.getError()) < m_posTolerance && Math.abs(driveController.get()) < m_velocityLimit;
        }
    }
}