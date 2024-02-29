// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakeBackLimit extends Command {
  private Intake i;
  /** Creates a new IntakeOut. */
  public IntakeBackLimit(Intake i) {
    this.i = i;
    i.setspeed(0.0);

    i.setisForward(true);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(i);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    i.setspeed(0.0);

    i.setisForward(true);
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
