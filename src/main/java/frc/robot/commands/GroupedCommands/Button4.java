// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.GroupedCommands;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

// set shooter angle and spin left/right shooter wheels

public class Button4 extends Command {
  private final Shooter s;
  private final CANSparkMax p;
  private final CANSparkMax l;
  private final CANSparkMax r;
  private final Double targetAngle = 120.0;
  private final Double marginOfError = 2.0;
  private final Joystick j;
  /** Creates a new Button1. */
  public Button4(Shooter s, CANSparkMax p, CANSparkMax l, CANSparkMax r, Joystick j) {
    this.p = s.getshooterPivot();
    this.l = s.getleftShooterMotor();
    this.r = s.getrightShooterMotor();
    this.s = s;
    this.j = s.getcontroller();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if (j.getRawButtonPressed(4)) {

    l.set(0.5);
    r.set(0.5);
    // }

    // p.setIdleMode(IdleMode.kBrake);
    // l.setIdleMode(IdleMode.kBrake);
    // r.setIdleMode(IdleMode.kBrake);

    // if (p.getEncoder().getPosition() <= targetAngle - marginOfError) {
    //   SmartDashboard.putString("shooter do", "go up");
    //   // p.set(0.5);
    // } else if (p.getEncoder().getPosition() >= targetAngle + marginOfError) {
    //   SmartDashboard.putString("shooter do", "go down");
    // } else {
    //   SmartDashboard.putString("Shooter do", "stay put");
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
