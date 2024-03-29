/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PID.DrivePID;
import frc.robot.PID.TurnPID;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  public static OI oi = new OI();
  public static ManualDrive manual = new ManualDrive();
  public static PathExecutor pathFollower = new PathExecutor();
  public static Diagnostics diagnostics = new Diagnostics();
  public static TransferData transfer = new TransferData();
  public static DrivePID driveControl = new DrivePID();
  public static TurnPID turnControl = new TurnPID();
  public static DriveTurn driveTurn = new DriveTurn();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {

    diagnostics.resetEncoders();

    transfer.setData();

    driveControl.config();

    turnControl.config();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {

    diagnostics.printOut();

    diagnostics.getTicks();

    diagnostics.calculateDist();

    

  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() 
  {
    pathFollower = new PathExecutor();
    diagnostics.resetEncoders();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {

    pathFollower.setEncoderPosition();

    pathFollower.ticks2metric();

    //pathFollower.driveRobot();

    driveTurn.sequence();

    driveTurn.driveRobot();

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {

    manual.driveRobot();

    manual.measureSpeed();

    manual.setEncoderPosition();

    manual.ticks2metric();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() 
  {
  }
}
