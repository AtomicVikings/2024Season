package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

public class MechSubsystem extends SubsystemBase{
    private final CANSparkMax shooterPrimary = new CANSparkMax(MechConstants.kShooterPrimaryId, MotorType.kBrushless);
    private final CANSparkMax shooterSecondary = new CANSparkMax(MechConstants.kShooterSecondaryId, MotorType.kBrushless);
    private final CANSparkMax intake = new CANSparkMax(MechConstants.kIntakeId, MotorType.kBrushless);

    public MechSubsystem() {
        shooterSecondary.setInverted(MechConstants.kShooterInverted);
        shooterPrimary.setInverted(MechConstants.kShooterInverted);
        intake.setInverted(MechConstants.kIntakeInverted);
    }

    public void setShooter(double setValue) {
        setValue *= MechConstants.kShooterPowerOffset;
        shooterPrimary.set(setValue);
        shooterSecondary.set(setValue);
        intake.set(setValue * .5);
    }
}
