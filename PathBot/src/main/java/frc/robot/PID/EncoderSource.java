package frc.robot.PID;

import frc.robot.Constants;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class EncoderSource implements PIDSource
{
    
    RobotMap robotMap;

    public EncoderSource()
    {
        robotMap = new RobotMap();
    }

    public PIDSourceType getPIDSourceType()
    {
        return PIDSourceType.kDisplacement;
    }

    public void setPIDSourceType(PIDSourceType type)
    {
        
    }

    public double pidGet()
    {
        return (Constants.leftDist + Constants.rightDist) / 2;
    }

    

}