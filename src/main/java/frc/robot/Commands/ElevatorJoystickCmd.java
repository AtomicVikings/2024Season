package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorJoystickCmd extends Command {
    Supplier<Boolean> elevatorUp, elevatorDown;
    ElevatorSubsystem elevatorSubsystem;

    public ElevatorJoystickCmd(ElevatorSubsystem elevatorSubsystem, Supplier<Boolean> elevatorUp, Supplier<Boolean> elevatorDown) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.elevatorDown = elevatorDown;
        this.elevatorUp = elevatorUp;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (elevatorUp.get()) {
            elevatorSubsystem.setElevator(1);
        } else if (elevatorDown.get()) {
            elevatorSubsystem.setElevator(-1);
        } else {
            elevatorSubsystem.setElevator(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.setElevator(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
