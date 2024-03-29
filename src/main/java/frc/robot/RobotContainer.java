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
import frc.robot.Commands.ElevatorJoystickCmd;
import frc.robot.Commands.MechJoystickCmd;
import frc.robot.Commands.ShooterAutoCmd;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.MechConstants;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.MechSubsystem;
import frc.robot.generated.TunerConstants;

public class RobotContainer {

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
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final Joystick mechJoystick = new Joystick(1);

  // Auto
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  private final Command kNoAuto = null;
  private final Command kDriveForward = new DriveStraightAutoCmd(drivetrain);
  private final Command kShooterAuto = new ShooterAutoCmd(mechSubsystem);
  private final Command kAllAuto = new ShooterAutoCmd(mechSubsystem).andThen(kDriveForward);

  private final Command kShooterAuto = new ShooterAutoCmd(mechSubsystem);

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
      () -> mechJoystick.getPOV() == 180,
      () -> mechJoystick.getPOV() == 0));

    elevatorSubsystem.setDefaultCommand(new ElevatorJoystickCmd(
      elevatorSubsystem,
      () -> mechJoystick.getRawButton(ElevatorConstants.kElevatorUpButton), 
      () -> mechJoystick.getRawButton(ElevatorConstants.kElevatorDownButton)));
  }

  public RobotContainer() {
    configureBindings();

    m_chooser.setDefaultOption("Drive Forward", kDriveForward);
    m_chooser.addOption("Shooter", kShooterAuto);
    m_chooser.addOption("All Autos", kAllAuto);
    m_chooser.addOption("NoAuto", kNoAuto);
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
