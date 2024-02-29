package frc.robot.commands.ShooterCommands;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShooterCommand extends Command {
  private final Shooter s;
  private final RelativeEncoder e;
  private final CANSparkMax m;
  private final Double targetAngle = 120.0;
  private final Double marginOfError = 2.0;

  public ShooterCommand(Shooter s, CANSparkMax m) {
    this.s = s;
    this.e = s.getshooterPivot().getEncoder();
    this.m = m;
    e.setPositionConversionFactor(360.0);
    addRequirements(s);
  }

  @Override
  public void initialize() {
    // System.out.println("starting angle: " + m.getEncoder().getPositionConversionFactor());
  }

  @Override
  public void execute() {

    SmartDashboard.putNumber("shooter encoder", s.getshooterPivot().getEncoder().getPosition());

    // SmartDashboard.putNumber("Encoder Position ", e.getPositionConversionFactor());

    //   if (m.getEncoder().getPosition() <= targetAngle - marginOfError) {
    //     SmartDashboard.putString("shooter do", "go up");
    //   } else if (m.getEncoder().getPosition() >= targetAngle + marginOfError) {
    //     SmartDashboard.putString("shooter do", "go down");
    //   } else {
    //     SmartDashboard.putString("Shooter do", "stay put");
    //   }

    if (s.getshooterPivot().getEncoder().getPosition() <= targetAngle - marginOfError) {
      SmartDashboard.putString("shooter do", "go up");
      // s.setshooterPivot(0.5);
    } else if (s.getshooterPivot().getEncoder().getPosition() >= targetAngle + marginOfError) {
      SmartDashboard.putString("shooter do", "go down");
    } else {
      SmartDashboard.putString("Shooter do", "stay put");
    }
  }

  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
