// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class IntakePivot extends Command {
  private final CANSparkMax p;
  private final RelativeEncoder intakeP;
  private final Intake i;
  private final Joystick j;

  /** Creates a new IntakePivot. */
  public IntakePivot(Intake i, CANSparkMax p) {
    this.i = i;
    this.p = p;
    this.intakeP = i.getintakePivotMotor().getEncoder();
    this.j = i.getcontroller();

    this.intakeP.setPositionConversionFactor(1.0);
    // this.intakeP.reset();
    addRequirements(i);
    // this.intakeP.setPositionConversionFactor(360.0);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // p.set(0.3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // SmartDashboard.putNumber(
    //     "Intake Pivot Encoder Position", intakeP.getPositionConversionFactor());

    if (i.getisForward()) {
      i.getintakePivotMotor().set(-i.getspeed());
    } else {
      i.getintakePivotMotor().set(i.getspeed());
    }

    SmartDashboard.putBoolean("current front limit", !i.getfrontLimit().get());
    SmartDashboard.putBoolean("current back limit", !i.getbackLimit().get());
    SmartDashboard.putNumber("current speed", i.getspeed());
    SmartDashboard.putNumber(
        "intake encoder", i.getintakePivotMotor().getEncoder().getPositionConversionFactor());
    // inverted help me
    if (!i.getfrontLimit().get() && i.getisForward()) {
      i.setspeed(0);
      i.setisForward(false);
      //  new IntakeFrontLimit(i);
    } else if (!i.getbackLimit().get() && !i.getisForward()) {
      i.setspeed(0);
      i.setisForward(true);
      // new IntakeBackLimit(i);
    }

    if (j.getRawButtonPressed(2)) {
      i.setspeed(0.1);
    } else {
      p.setIdleMode(IdleMode.kBrake);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.//
  @Override
  public boolean isFinished() {
    return false;
  }
}
