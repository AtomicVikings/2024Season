// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

//import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.AutoConstants;
import frc.robot.Subsystems.MechSubsystem;

public class ShooterAutoCmd extends Command {
    MechSubsystem mechSubsystem;
    Timer timer = new Timer();
    private boolean finished;

    public ShooterAutoCmd(MechSubsystem mechSubsystem) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.mechSubsystem = mechSubsystem;
        addRequirements(mechSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        finished = false;
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (timer.get() < AutoConstants.kAutoShooterTimer) {
            mechSubsystem.setShooter(-1);
        } else {
            finished = true;
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        mechSubsystem.setShooter(0);
        timer.restart();
        timer.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return finished;
    }
}
