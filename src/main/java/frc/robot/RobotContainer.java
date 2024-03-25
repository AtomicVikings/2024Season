// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.DriveStraightAutoCmd;
import frc.robot.Commands.MechJoystickCmd;
import frc.robot.Commands.ShooterAutoCmd;
import frc.robot.Constants.MechConstants;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.Subsystems.MechSubsystem;
import frc.robot.generated.TunerConstants;

public class RobotContainer {

  // -------------- CTRE GENERATED CODE ------------------- //
  private double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity
  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController joystick = new CommandXboxController(0); // My joystick
  private final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain
  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.2) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage);
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final Telemetry logger = new Telemetry(MaxSpeed);

  // ------------- OUR CODE :D --------------------------//
  private final MechSubsystem mechSubsystem = new MechSubsystem();
  //private final MechSubsystem mechSubsystem1 = new MechSubsystem();
  private final Joystick mechJoystick = new Joystick(1);

  // Auto
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final Command kNoAuto = null;
  private final Command kDriveForward = new DriveStraightAutoCmd(drivetrain);
  //private final int kShooterJoystickThing = MechConstants.kShooterOutButton;
  private final Command kShooterAuto = new ShooterAutoCmd(mechSubsystem);
  //private final Command kAutoMan = kShooterAuto.andThen(kDriveForward);
  
  //ShooterOutButton(kShooterJoystickThin


//        if (shooterIn.get()) {
 // mechSubsystem.setShooter(1);

  private void configureBindings() {
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(-joystick.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(-joystick.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(-joystick.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
    joystick.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(-joystick.getLeftY(), -joystick.getLeftX()))));

    // reset the field-centric heading on left bumper press
    joystick.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);


    // Mechanism Controls
    mechSubsystem.setDefaultCommand(new MechJoystickCmd(
      mechSubsystem,
      () -> mechJoystick.getPOV() == 0,
      () -> mechJoystick.getRawButton(MechConstants.kShooterOutButton),
      () -> mechJoystick.getRawButton(MechConstants.kIntakeInButton),
      () -> mechJoystick.getRawButton(MechConstants.kIntakeOutButton),
      () -> mechJoystick.getRawButton(MechConstants.kElevatorUpButton),
      () -> mechJoystick.getRawButton(MechConstants.kElevatorDownButton)));

    
    //mechSubsystem1.setShooter(-1);
   // mechSubsystem1.setIntake(-1);
   // mechSubsystem.addChild(mechSubsystem, ()-> );
    //mechSubsystem1.setShooter(0);
    //mechSubsystem1.setShooter(-1);
    //mechSubsystem1.setIntake(0);
    
    
  }

  public RobotContainer() {
    configureBindings();

    // Auto Setup
    m_chooser.setDefaultOption("AllAuto", kShooterAuto.andThen(kDriveForward));
    m_chooser.setDefaultOption("ShootIt", kShooterAuto);
    //m_chooser.addOption("AHHHHH", automan());
    //m_chooser.addOption("AutoDefault", kAutoMan);
    m_chooser.addOption("Drive Forward", kDriveForward);
    m_chooser.addOption("NoAuto", kNoAuto);
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
