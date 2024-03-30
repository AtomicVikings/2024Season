package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.MechSubsystem;

public class MechJoystickCmd extends Command {
    Supplier<Boolean> shooterIn, shooterOut;
    MechSubsystem mechSubsystem;

    public MechJoystickCmd(MechSubsystem mechSubsystem,
                            Supplier<Boolean> shooterIn,
                            Supplier<Boolean> shooterOut) {
        this.mechSubsystem = mechSubsystem;
        this.shooterIn = shooterIn;
        this.shooterOut = shooterOut;
        addRequirements(mechSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (shooterIn.get()) {
            mechSubsystem.setShooter(1);
        } else if (shooterOut.get()) {
            mechSubsystem.setShooter(-1);
        } else {
            mechSubsystem.setShooter(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        mechSubsystem.setShooter(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
