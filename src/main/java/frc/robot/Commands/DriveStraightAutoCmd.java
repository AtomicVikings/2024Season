package frc.robot.Commands;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
//import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.SteerRequestType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.generated.TunerConstants;

public class DriveStraightAutoCmd extends Command {
    private final CommandSwerveDrivetrain swerveSubsystem;
    private final SwerveRequest.FieldCentric m_driveRequest = new SwerveRequest.FieldCentric()
        .withDeadband(TunerConstants.kSpeedAt12VoltsMps * 0.05).withRotationalDeadband(TunerConstants.kSpeedAt12VoltsMps * 0.05)
        .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

    Timer timer = new Timer();
    private boolean finished;

    public DriveStraightAutoCmd(CommandSwerveDrivetrain swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        finished = false;
        timer.start();
    }

    @Override
    public void execute() {
       if (timer.get() < AutoConstants.kTimeForward) {
        swerveSubsystem.setControl(
            m_driveRequest
            .withVelocityX(TunerConstants.kSpeedAt12VoltsMps * AutoConstants.kSpeedForward) 
            .withVelocityY(0) 
            .withRotationalRate(0)
        );
       } else {
            finished = true;
       }
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.setControl(new SwerveRequest.Idle());
        timer.restart();
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
