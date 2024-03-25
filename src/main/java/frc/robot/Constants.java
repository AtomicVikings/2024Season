package frc.robot;

public class Constants {
    public static class MechConstants {
        // CAN ID's
        public static final int kShooterPrimaryId = 16;
        public static final int kShooterSecondaryId = 17;
        public static final int kIntakeId = 15;
        
        // Mechanism Configs
        public static final boolean kShooterInverted = true;
        public static final boolean kIntakeInverted = true;

        // Controller bindings
        //public static final int kShooterInButton = 1;
        public static final int kShooterOutButton = 5;
        public static final int kIntakeInButton = 4;
        public static final int kIntakeOutButton = 6;
    }

    public static class AutoConstants {
        public static final double kTimeForward = 2.2; // Time forward in seconds
        public static final double kSpeedForward = .3; // Speed forward (no need to change).
    }

    public static class ElevatorConstants {
        public static final boolean kElevatorInverted = false;
        public static final int kElevatorPrimaryId = 20;
        public static final int kElevatorUpButton = 1;
        public static final int kElevatorDownButton = 2;
    }
}
