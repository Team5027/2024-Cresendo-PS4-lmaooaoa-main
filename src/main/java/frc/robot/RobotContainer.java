package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.GroupedCommands.StopStorage;
import frc.robot.commands.GroupedCommands.Storage;
import frc.robot.commands.GroupedCommands.Unfolding;
import frc.robot.commands.ShooterCommands.ShootSpeaker;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOSim;
import frc.robot.subsystems.drive.ModuleIOSparkMax;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final Drive drive;
  private final Climber climberSubsystem = new Climber();
  private final Indexer indexerSubsystem = new Indexer();
  private final Intake intakeSubsystem;
  private final Shooter shooterSubsystem;

  // Controller
  private final Joystick controller = new Joystick(0);
  private JoystickButton x;
  private JoystickButton b;
  private JoystickButton y;
  private JoystickButton rB;
  // private final XboxController xboxController = new XboxController(0);

  // Dashboard inputs
  private final LoggedDashboardChooser<Command> autoChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real robot, instantiate hardware IO implementations
        drive =
            new Drive(
                new GyroIOPigeon2(false),
                new ModuleIOSparkMax(0),
                new ModuleIOSparkMax(1),
                new ModuleIOSparkMax(2),
                new ModuleIOSparkMax(3));
        shooterSubsystem = new Shooter(controller);
        shooterSubsystem.initDefaultCommand();
        intakeSubsystem = new Intake(controller);
        intakeSubsystem.initDefaultCommand();

        // flywheel = new Flywheel(new FlywheelIOSparkMax());
        // drive = new Drive(
        // new GyroIOPigeon2(true),
        // new ModuleIOTalonFX(0),
        // new ModuleIOTalonFX(1),
        // new ModuleIOTalonFX(2),
        // new ModuleIOTalonFX(3));
        // flywheel = new Flywheel(new FlywheelIOTalonFX());
        break;

      case SIM:
        // Sim robot, instantiate physics sim IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIOSim(),
                new ModuleIOSim(),
                new ModuleIOSim(),
                new ModuleIOSim());
        shooterSubsystem = new Shooter(controller);
        shooterSubsystem.initDefaultCommand();
        intakeSubsystem = new Intake(controller);
        intakeSubsystem.initDefaultCommand();

        // flywheel = new Flywheel(new FlywheelIOSim());
        break;

      default:
        // Replayed robot, disable IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {});
        shooterSubsystem = new Shooter(controller);
        shooterSubsystem.initDefaultCommand();
        intakeSubsystem = new Intake(controller);
        intakeSubsystem.initDefaultCommand();

        // flywheel = new Flywheel(new FlywheelIO() {});
        break;
    }

    /*  climberSubsystem.setPeriodicStatus();
    indexerSubsystem.setPeriodicStatus();
    intakeSubsystem.setPeriodicStatus();
    shooterSubsystem.setPeriodicStatus();*/

    // Set up auto routines
    // NamedCommands.registerCommand(
    //     "Run Flywheel",
    //     Commands.startEnd(
    //             () -> flywheel.runVelocity(flywheelSpeedInput.get()), flywheel::stop, flywheel)
    //         .withTimeout(5.0));
    autoChooser = new LoggedDashboardChooser<>("Auto Choices", AutoBuilder.buildAutoChooser());

    // Set up SysId routines
    autoChooser.addOption(
        "Drive SysId (Quasistatic Forward)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Reverse)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    autoChooser.addOption(
        "Drive SysId (Dynamic Forward)", drive.sysIdDynamic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Dynamic Reverse)", drive.sysIdDynamic(SysIdRoutine.Direction.kReverse));
    // autoChooser.addOption(
    //     "Flywheel SysId (Quasistatic Forward)",
    //     flywheel.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    // autoChooser.addOption(
    //     "Flywheel SysId (Quasistatic Reverse)",
    //     flywheel.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    // autoChooser.addOption(
    //     "Flywheel SysId (Dynamic Forward)",
    // flywheel.sysIdDynamic(SysIdRoutine.Direction.kForward));
    // autoChooser.addOption(
    //     "Flywheel SysId (Dynamic Reverse)",
    // flywheel.sysIdDynamic(SysIdRoutine.Direction.kReverse));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link PS4Controller}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*  controller.y().onTrue(new ClimberPIDCommand(climberSubsystem, 60));
    controller.a().onTrue(new ClimberPIDCommand(climberSubsystem, 2));
    controller.povLeft().onTrue(new InstantCommand(() -> climberSubsystem.resetEncoder()));
    controller.povUp().whileTrue(new ClimberCommand(climberSubsystem, 1));
    controller.povDown().whileTrue(new ClimberCommand(climberSubsystem, -1));*/

    drive.setDefaultCommand(
        DriveCommands.joystickDrive(
            drive,
            () -> -controller.getRawAxis(0),
            () -> -controller.getRawAxis(1),
            () -> -controller.getRawAxis(4)));
    // controller.square().onTrue(Commands.runOnce(drive::stopWithsquare, drive));
    // controller // uhhhhh
    //     .getRawButtonPressed(3)
    //     .onTrue(
    //         Commands.runOnce(
    //                 () ->
    //                     drive.setPose(
    //                         new Pose2d(drive.getPose().getTranslation(), new Rotation2d())),
    //                 drive)
    //             .ignoringDisable(true)); // uhhhhh

    x = new JoystickButton(controller, 3);
    x.onTrue(new Storage(intakeSubsystem, shooterSubsystem));
    x.onFalse(new StopStorage(intakeSubsystem, shooterSubsystem));

    b = new JoystickButton(controller, 2);
    b.toggleOnTrue(new Unfolding(shooterSubsystem, intakeSubsystem));

    y = new JoystickButton(controller, 4);
    // y.toggleOnTrue(new Button4(shooterSubsystem, , null, null, controller))

    // x = new JoystickButton(controller, 2);
    // x.onTrue(new MoveIntake(intakeSubsystem));

    // a.onTrue(new ChangeIntake(controllerSubsystem));

    rB = new JoystickButton(controller, 6);
    rB.toggleOnTrue(new ShootSpeaker(shooterSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.get();
  }
}
