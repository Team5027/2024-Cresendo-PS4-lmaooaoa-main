package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.ShooterCommands.ShooterCommand;

public class Shooter extends SubsystemBase {
  private final CANSparkMax leftShooterMotor;
  private final CANSparkMax rightShooterMotor;
  private final CANSparkMax bottomShooterMotor;
  private final CANSparkMax shooterPivot;
  private final RelativeEncoder pivotEncoder;
  private final Joystick controller;
  double motorPosition = 180; // integer between 0-360
  double rate;
  // pivotEncoder.reset();

  public Shooter(Joystick j) {
    leftShooterMotor = new CANSparkMax(17, MotorType.kBrushless);
    rightShooterMotor = new CANSparkMax(16, MotorType.kBrushless);
    bottomShooterMotor = new CANSparkMax(19, MotorType.kBrushless);
    shooterPivot = new CANSparkMax(18, MotorType.kBrushless);
    controller = j;
    pivotEncoder = shooterPivot.getEncoder();
    // rate = pivotEncoder.getRate();

    // this.setDefaultCommand(new ShooterCommand(this, pivotEncoder));
  }

  public void initDefaultCommand() {
    setDefaultCommand(new ShooterCommand(this, shooterPivot));
    // setDefaultCommand(new ShooterCommand(this, leftShooterMotor));
  }

  @Override
  public void periodic() {}

  public void setPeriodicStatus() {
    /*leftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 500);
      leftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 500);
      LeftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 500);
      LeftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 500);
      LeftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 500);
      LeftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 500);
      LeftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 500);
      LeftShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus7, 500);

      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 500);
      RightShooterMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus7, 500);
    }*/
  }

  public CANSparkMax getleftShooterMotor() {

    return this.leftShooterMotor;
  }

  public CANSparkMax getrightShooterMotor() {
    return this.rightShooterMotor;
  }

  public CANSparkMax getbottomShooterMotor() {
    return this.bottomShooterMotor;
  }

  public CANSparkMax getshooterPivot() {
    return this.shooterPivot;
  }

  public Joystick getcontroller() {
    return this.controller;
  }
}
