// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.StopCommands;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class StopIntake extends Command {
  private final CANSparkMax in;

  private final double defaultSpeed = 1.0;

  /**
   * Creates a new IntakeCommand.
   *
   * @param intakePivot
   * @param intakeMotor
   * @param intake
   */
  public StopIntake(Intake intake) {
    this.in = intake.getintakeMotor();
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.

  @Override
  public void initialize() {
    in.set(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
