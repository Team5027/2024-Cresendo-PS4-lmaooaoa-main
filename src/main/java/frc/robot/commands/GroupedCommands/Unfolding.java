// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.GroupedCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeCommands.MoveIntake;
import frc.robot.commands.ShooterCommands.ShooterUp;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// button 2?

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Unfolding extends SequentialCommandGroup {
  /** Creates a new Unfolding. */
  public Unfolding(Shooter s, Intake i) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ShooterUp(s).andThen(new MoveIntake(i)));
  }
}
