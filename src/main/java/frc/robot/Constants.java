package frc.robot;

public class Constants {
    public static class MechConstants {
        // CAN ID's
        public static final int kShooterPrimaryId = 16;
        public static final int kShooterSecondaryId = 17;
        public static final int kIntakeId = 15;
        public static final int kElevatorPrimaryId = 18;
        public static final int kElevatorSecondaryId = 19;
        
        // Mechanism Configs
        public static final boolean kShooterInverted = true;
        public static final boolean kIntakeInverted = false;
        public static final double kShooterPowerOffset = 1;
    }

    public static class AutoConstants {
        public static final double kTimeForward = 1.3; // Time forward in seconds
        public static final double kSpeedForward = .37; // Speed forward (no need to change).
        public static final double kAutoShooterTimer = 1.3;
    }

    public static class ElevatorConstants {
        public static final boolean kElevatorInverted = false;
        public static final int kElevatorPrimaryId = 18;
        public static final int kElevatorSecondaryId = 19;

        public static final int kElevatorUpButton = 4;
        public static final int kElevatorDownButton = 1;
    }
}
