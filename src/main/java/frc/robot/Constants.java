package frc.robot;

public class Constants {
    public static class MechConstants {
        // CAN ID's
        public static final int kShooterPrimaryId = 16;
        public static final int kShooterSecondaryId = 17;
        public static final int kIntakeId = 15;
        
        // Mechanism Configs
        public static final boolean kShooterInverted = false;
        public static final boolean kIntakeInverted = false;

        // Controller bindings
        public static final int kShooterInButton = 1;
        public static final int kShooterOutButton = 2;
        public static final int kIntakeInButton = 3;
        public static final int kIntakeOutButton = 4;
    }

    public static class AutoConstants {
        public static final double kTimeForward = 2.2; // Time forward in seconds
        public static final double kSpeedForward = .3; // Speed forward (no need to change).
    }
}
