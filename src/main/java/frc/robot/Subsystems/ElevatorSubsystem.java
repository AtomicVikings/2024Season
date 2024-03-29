package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase{
    private final CANSparkMax elevatorPrimary = new CANSparkMax(ElevatorConstants.kElevatorPrimaryId, MotorType.kBrushless);
    private final CANSparkMax elevatorSecondary = new CANSparkMax(ElevatorConstants.kElevatorSecondaryId, MotorType.kBrushless);

    public ElevatorSubsystem() {
        elevatorPrimary.setInverted(ElevatorConstants.kElevatorInverted);
        elevatorSecondary.setInverted(!ElevatorConstants.kElevatorInverted);
    }

    public void setElevator(double setValue) {
        elevatorPrimary.set(setValue);
        elevatorSecondary.set(setValue);
    }
}
