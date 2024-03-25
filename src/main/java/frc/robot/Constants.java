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
        public static final boolean kIntakeInverted = true;

        // Controller bindings
        //public static final int kShooterInButton = 1;
        public static final int kShooterOutButton = 5;
        public static final int kElevatorUpButton = 4;
        public static final int kElevatorDownButton = 1;
        public static int kIntakeOutButton;
        public static int kIntakeInButton;
    }

    public static class AutoConstants {
        public static final double kTimeForward = 2.2; // Time forward in seconds
        public static final double kSpeedForward = .3; // Speed forward (no need to change).
        public static final double kshootautotimer = 1.3;
    }
}
