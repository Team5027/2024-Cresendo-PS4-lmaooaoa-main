// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootSpeaker extends Command {
  private final CANSparkMax l;
  private final CANSparkMax r;
  private final CANSparkMax b;
  private final Shooter s;
  private final Joystick j;
  private final double speed = 0.5;
  /** Creates a new ShootSpeaker. */
  public ShootSpeaker(Shooter s) {
    this.s = s;
    this.l = s.getleftShooterMotor();
    this.r = s.getrightShooterMotor();
    this.b = s.getbottomShooterMotor();
    this.j = s.getcontroller();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // if (j.getRawButtonPressed(4)) {
    //   // shooter angle too king
    //   l.set(-speed);
    //   r.set(speed);
    // } else {
    //   // l.setIdleMode(IdleMode.kBrake);
    //   // r.setIdleMode(IdleMode.kBrake);
    // }

    // if (j.getRawButtonPressed(6)) {
    b.set(speed);
    // } else {
    // b.setIdleMode(IdleMode.kBrake);
    // }
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
