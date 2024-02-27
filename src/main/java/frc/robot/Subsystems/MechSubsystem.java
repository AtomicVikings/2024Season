package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

public class MechSubsystem extends SubsystemBase{
    private final CANSparkMax shooterPrimary = new CANSparkMax(MechConstants.kShooterPrimaryId, MotorType.kBrushless);
    private final CANSparkMax shooterSecondary = new CANSparkMax(MechConstants.kShooterSecondaryId, MotorType.kBrushless);
    private final CANSparkMax intake = new CANSparkMax(MechConstants.kIntakeId, MotorType.kBrushed);

    public MechSubsystem() {
        shooterSecondary.setInverted(MechConstants.kShooterInverted);
        shooterPrimary.setInverted(MechConstants.kShooterInverted);
        intake.setInverted(MechConstants.kIntakeInverted);
    }

    public void setIntake(double setValue) {
        intake.set(setValue);
    }

    public void setShooter(double setValue) {
        shooterPrimary.set(setValue);
        shooterSecondary.set(setValue);
    }
}
