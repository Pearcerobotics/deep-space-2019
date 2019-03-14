// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1745.deepspace2019.subsystems.LedPWMController;
import org.usfirst.frc1745.deepspace2019.subsystems.drive.Drive;
import org.usfirst.frc1745.deepspace2019.subsystems.drive.DrivingDeltas;
import org.usfirst.frc1745.deepspace2019.subsystems.manipulator.Manipulator;
import org.usfirst.frc1745.deepspace2019.subsystems.vision.Limelight;
import org.usfirst.frc1745.deepspace2019.subsystems.vision.Vision;

import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();
    

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private Controls controls;
    private Drive drive;
    private Manipulator manipulator; 
    private Limelight limelight;
    private LedPWMController ledPWMController;
    private Vision vision;
    private final int JOYSTICK_PORT = 0;
    private final int LED_PWM_PORT = 0;
    private final int LEFT_ULTRASONIC_PORT = 0;
    private final int RIGHT_ULTRASONIC_PORT = 1;
    private final double DEADZONE = 0.05;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        this.drive = new Drive();
        this.controls = new Controls(new Joystick(JOYSTICK_PORT));
        this.manipulator = new Manipulator();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI(controls, manipulator);
        CameraServer.getInstance().startAutomaticCapture();
        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        this.drive = new Drive();
        this.controls = new Controls(new Joystick(JOYSTICK_PORT));
        this.ledPWMController = new LedPWMController(LED_PWM_PORT);
        this.limelight = new Limelight();
        this.vision = new Vision(limelight, LEFT_ULTRASONIC_PORT, RIGHT_ULTRASONIC_PORT, ledPWMController);
        Compressor compressor = new Compressor();
        compressor.clearAllPCMStickyFaults();
    }

    /**
     * This function is called when the disabled button is hit. You can use it to
     * reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        DrivingDeltas calculatedDeltas = vision.targetDelta();

        // Go to the target
        if (controls.getBButton()) {
            drive.arcadeDrive(calculatedDeltas.getForwardPower(), calculatedDeltas.getSteeringPower());
        } else {
            drive.arcadeDrive(controls.getLeftY(DEADZONE), controls.getRightX(DEADZONE) * 0.75);
        }

        //Deploy Code
        if (controls.getLeftBumper()) {
            manipulator.spinHatch(-.3);
        } else if(controls.getRightBumper()) {
            manipulator.spinHatch(.3);
        } else {
            manipulator.spinHatch(0);
        }
        

    }

}
