/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.manipulator;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Manipulator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final int DOUBLESOLENOID_FORWARD_PORT_ID = 0;
  private final int DOUBLESOLENOID_REVERSE_PORT_ID = 1;

  private final int HATCH_SPINNER_CAN_ID = 11;
  private final int ARM_CAN_ID = 10;

  private DoubleSolenoidActuator doubleSolenoid;
  private CANSparkMax hatchSpinnerController;
  private CANSparkMax armController;
  private ArmPidController armPidController;
  
  public Manipulator() {
    this.doubleSolenoid = new DoubleSolenoidActuator(new DoubleSolenoid(DOUBLESOLENOID_FORWARD_PORT_ID, DOUBLESOLENOID_REVERSE_PORT_ID));
    this.hatchSpinnerController = new CANSparkMax(HATCH_SPINNER_CAN_ID, MotorType.kBrushed);
    this.armController = new CANSparkMax(ARM_CAN_ID, MotorType.kBrushless);
    this.armController.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 5);
    this.armController.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 5);
    this.armController.enableVoltageCompensation(12.0);
    this.armPidController = new ArmPidController(armController);
  }

  public void actuate() {
    this.doubleSolenoid.toggle();
  }

  public void deployArm() {
    this.armPidController.deploy();
  }

  public void resetEncPosition() {
    this.armController.setEncPosition(0);
  }

  public void retractArm() {
    this.armPidController.retract();
  }

  public void spinHatch(double power) {
    this.hatchSpinnerController.set(power);
  }

  public void runArm(double power) {
    this.armController.set(power);
  }

  @Override
  public void initDefaultCommand() {
  }
}
