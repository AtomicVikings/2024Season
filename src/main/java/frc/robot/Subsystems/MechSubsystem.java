package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

public class MechSubsystem extends SubsystemBase{
    private final CANSparkMax shooterPrimary = new CANSparkMax(MechConstants.kShooterPrimaryId, MotorType.kBrushless);
    private final CANSparkMax shooterSecondary = new CANSparkMax(MechConstants.kShooterSecondaryId, MotorType.kBrushless);
    private final CANSparkMax shooterThird = new CANSparkMax(MechConstants.kIntakeId, MotorType.kBrushless);
    private final CANSparkMax elevatorPrimary = new CANSparkMax(MechConstants.kElevatorPrimaryId, MotorType.kBrushless);
    private final CANSparkMax elevatorSecondary = new CANSparkMax(MechConstants.kElevatorSecondaryId, MotorType.kBrushless);

    public MechSubsystem() {
        shooterSecondary.setInverted(MechConstants.kShooterInverted);
        shooterPrimary.setInverted(MechConstants.kShooterInverted);
        shooterThird.setInverted(false);
        elevatorPrimary.setInverted(false);
        elevatorSecondary.setInverted(true);
    }

    public void setIntake(double setValue) {
        //intake.set(setValue);
    }

    public void setShooter(double setValue) {
        shooterPrimary.set(setValue);
        shooterSecondary.set(setValue);
        shooterThird.set(setValue);
    }

    public void setElevator(double setValue) {
        elevatorPrimary.set(setValue);
        elevatorSecondary.set(setValue);
    }
}
