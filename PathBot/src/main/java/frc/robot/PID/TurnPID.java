package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDBase.Tolerance;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class TurnPID implements PIDOutput
{

    public PIDController turnController;
    MyCustomTolerance turnTolerance;
    RobotMap robotMap;

    public TurnPID()
    {
        robotMap = new RobotMap();
    }

    public void config()
    {
        turnTolerance = new MyCustomTolerance();
        turnController = new PIDController(Constants.turnKp, Constants.turnKi,Constants.turnKd, robotMap.ahrs, this);
        //turnController.setTolerance(turnTolerance);
        turnController.setAbsoluteTolerance(2);
        turnController.setOutputRange(-0.6, 0.6);
        turnController.setInputRange(-180, 180);
        turnController.setContinuous(true);
    }

    public void pidWrite(double output)
    {
        Constants.turnPIDout = output;
    }

    public class MyCustomTolerance implements Tolerance 
    {
        private final double m_posTolerance;
        private final double m_velocityLimit;

        MyCustomTolerance() 
        {
            m_posTolerance = 2;
            m_velocityLimit = 0.1; 
        }

        @Override
        public boolean onTarget() 
        {
            return Math.abs(turnController.getError()) < m_posTolerance && Math.abs(turnController.get()) < m_velocityLimit;
        }
    }
}