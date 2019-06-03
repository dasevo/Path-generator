package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTurn
{

    RobotMap robotMap;
    boolean init;
    int step = 0;

    public DriveTurn()
    {
        robotMap = new RobotMap();
        init = true;
    }

    public void sequence()
    {
        if(init&&step==0)
        {
            Robot.driveControl.driveController.enable();
            Robot.driveControl.driveController.setSetpoint(1.0);
            init = false;
        }
        else if(Robot.driveControl.driveController.onTarget()&&step==0)
        {
            init = true;
            step++;
            Robot.driveControl.driveController.disable();
            Robot.turnControl.turnController.enable();
        }
        else if(step==1&init)
        {
            Robot.turnControl.turnController.setSetpoint(90);
            init = false;
        }
        else if(step==1&&Robot.turnControl.turnController.onTarget())
        {
            init = true;
            step++;
            Robot.turnControl.turnController.disable();
            Robot.driveControl.driveController.enable();
        }
        else if(step==2&&init)
        {
            Robot.diagnostics.resetEncoders();
            Robot.driveControl.driveController.setSetpoint(1.5);
            init = false;
        }
        else if(step==2&&Robot.driveControl.driveController.onTarget())
        {
            Robot.driveControl.driveController.disable();
            init = true;
            step++;
        }
        else if(step==3&&init)
        {
            Robot.turnControl.turnController.enable();
            Robot.turnControl.turnController.setSetpoint(0);
            init = false;
        }
        else if(step==3&&Robot.turnControl.turnController.onTarget())
        {
            Robot.diagnostics.resetEncoders();
            Robot.turnControl.turnController.disable();
            init = true;
            step++;
        }
        else if(step==4&&init)
        {
            Robot.diagnostics.resetEncoders();
            Robot.driveControl.driveController.enable();
            Robot.driveControl.driveController.setSetpoint(2);
            init = false;
        }
        else if(step==4&&Robot.driveControl.driveController.onTarget())
        {
            Robot.driveControl.driveController.disable();
            init = true;
            step++;
        }
        else if(step==5&&init)
        {
            Robot.turnControl.turnController.enable();
            Robot.turnControl.turnController.setSetpoint(-90);
            init = false;
        }
        else if(step==5&&Robot.turnControl.turnController.onTarget())
        {
            Robot.diagnostics.resetEncoders();
            Robot.turnControl.turnController.disable();
            init = true;
            step++;
        }
        else if(step==6&&init)
        {
            Robot.diagnostics.resetEncoders();
            Robot.driveControl.driveController.enable();
            Robot.driveControl.driveController.setSetpoint(1.3);
            init = false;
        }
        else if(step==6&&Robot.driveControl.driveController.onTarget())
        {
            Robot.driveControl.driveController.disable();
            init = true;
            step++;
        }
        else if(step==7&&init)
        {
            Robot.turnControl.turnController.enable();
            Robot.turnControl.turnController.setSetpoint(0);
            init = false;
        }
        else if(step==7&&Robot.turnControl.turnController.onTarget())
        {
            Robot.diagnostics.resetEncoders();
            Robot.turnControl.turnController.disable();
            init = true;
            step++;
        }
        else if(step==8&&init)
        {
            Robot.diagnostics.resetEncoders();
            Robot.driveControl.driveController.enable();
            Robot.driveControl.driveController.setSetpoint(1.5);
            init = false;
        }
        else if(step==8&&Robot.driveControl.driveController.onTarget())
        {
            Robot.driveControl.driveController.disable();
            init = true;
            step++;
        }

        SmartDashboard.putNumber("step", step);
        SmartDashboard.putBoolean("enabledDrive", Robot.driveControl.driveController.isEnabled());
        SmartDashboard.putBoolean("enabledTurn", Robot.turnControl.turnController.isEnabled());
        SmartDashboard.putNumber("heading", robotMap.ahrs.getAngle());

    }   

    public void driveRobot()
    {
        double drive;
        double turn;

        if(Robot.driveControl.driveController.isEnabled())
        {
            drive = Constants.drivePIDout;
        }
        else
        {
            drive = 0;
        }

        if(Robot.turnControl.turnController.isEnabled())
        {
            turn = Constants.turnPIDout;
        }
        else
        {
            turn = 0;
        }

        robotMap.drive.arcadeDrive(drive, turn);
    }

}