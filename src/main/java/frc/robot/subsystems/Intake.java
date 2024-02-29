package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeCommands.IntakePivot;

public class Intake extends SubsystemBase {
  private final CANSparkMax intakeMotor;
  private final CANSparkMax intakePivotMotor;
  private final RelativeEncoder intakePivotEncoder;
  private final DigitalInput frontLimit;
  private final DigitalInput backLimit;
  private final Joystick controller;
  // private final JoystickButton x;
  private boolean isForward;
  private double speed;

  public Intake(Joystick j) {
    intakeMotor = new CANSparkMax(15, MotorType.kBrushless);
    intakePivotMotor = new CANSparkMax(14, MotorType.kBrushless);
    intakePivotEncoder = intakePivotMotor.getEncoder();
    frontLimit = new DigitalInput(0);
    backLimit = new DigitalInput(1);
    controller = j;
    //  x = new JoystickButton(controller, 2);
    isForward = true;
    speed = 0.0;
  }

  public void initDefaultCommand() {
    // setDefaultCommand(new IntakeCommand(this, intakeMotor));
    setDefaultCommand(new IntakePivot(this, intakePivotMotor));
  }

  @Override
  public void periodic() {}

  /*public void setPeriodicStatus() {
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 500);
    IntakeMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus7, 500);
    ;
  }*/

  public CANSparkMax getintakeMotor() {

    return this.intakeMotor;
  }

  public CANSparkMax getintakePivotMotor() {
    return this.intakePivotMotor;
  }

  public boolean getisForward() {
    return this.isForward;
  }

  public void setisForward(boolean b) {
    this.isForward = b;
  }

  public double getspeed() {
    return this.speed;
  }

  public void setspeed(double s) {
    this.speed = s;
  }

  public DigitalInput getfrontLimit() {
    return this.frontLimit;
  }

  public DigitalInput getbackLimit() {
    return this.backLimit;
  }

  public Joystick getcontroller() {
    return this.controller;
  }
}
