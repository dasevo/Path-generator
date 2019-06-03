package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.util.WPILibVersion;

public class RobotMap
{

    public static WPI_TalonSRX talonOne = new WPI_TalonSRX(1); //right
    public static WPI_TalonSRX talonTwo = new WPI_TalonSRX(2);
    public static WPI_TalonSRX talonThree = new WPI_TalonSRX(3); //left
    public static WPI_TalonSRX talonFour = new WPI_TalonSRX(4);

    private static SpeedControllerGroup right = new SpeedControllerGroup(talonOne, talonThree);
    private static SpeedControllerGroup left = new SpeedControllerGroup(talonTwo, talonFour);

    public static DifferentialDrive drive = new DifferentialDrive(left, right); //(talonOne, talonTwo); 

    public static AHRS ahrs = new AHRS(Port.kMXP);

    public RobotMap()
    {
    
    }
}