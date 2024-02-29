// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.GroupedCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.StopCommands.StopIntake;
import frc.robot.commands.StopCommands.StopStorageShoot;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// button 3

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StopStorage extends ParallelCommandGroup {
  /** Creates a new StopStorage. */
  public StopStorage(Intake i, Shooter s) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new StopIntake(i),
        new StopStorageShoot(
            s, s.getleftShooterMotor(), s.getrightShooterMotor(), s.getbottomShooterMotor()));
  }
}
