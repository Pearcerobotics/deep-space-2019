/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1745.deepspace2019.subsystems.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Gearbox leftGearbox;
  private Gearbox rightGearbox;
  //left gear box CAN ids
  private final int LEFT_BACK_CAN_ID = 4;
  private final int LEFT_MIDDLE_CAN_ID = 5;
  private final int LEFT_FRONT_CAN_ID = 6;
  //right gear box CAN ids
  private final int RIGHT_BACK_CAN_ID = 7;
  private final int RIGHT_MIDDLE_CAN_ID = 8;
  private final int RIGHT_FRONT_CAN_ID = 9;

  private MotorType DRIVE_MOTOR_TYPE = MotorType.kBrushless;

  public Drive() {
    this.leftGearbox = new Gearbox(new CANSparkMax(LEFT_BACK_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(LEFT_MIDDLE_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(LEFT_FRONT_CAN_ID, DRIVE_MOTOR_TYPE));

    this.rightGearbox = new Gearbox(new CANSparkMax(RIGHT_BACK_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(RIGHT_MIDDLE_CAN_ID, DRIVE_MOTOR_TYPE), 
                                    new CANSparkMax(RIGHT_FRONT_CAN_ID, DRIVE_MOTOR_TYPE));
    this.leftGearbox.setRampRate(1);
    this.rightGearbox.setRampRate(1);
  }

  public void setLeftSpeed(double speed) {
    this.leftGearbox.setSpeed(speed);
  }

  public void setRightSpeed(double speed) {
    this.rightGearbox.setSpeed(speed);
  }

  public void arcadeDrive(double staightSpeed, double turnModifer) {
    this.setLeftSpeed(-(staightSpeed - turnModifer));
    this.setRightSpeed(staightSpeed + turnModifer);
  }

  public void arcadeDrive(DrivingDeltas drivingDeltas) {
    arcadeDrive(drivingDeltas.getForwardPower(), drivingDeltas.getSteeringPower());
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
