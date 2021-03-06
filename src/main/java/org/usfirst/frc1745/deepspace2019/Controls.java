
package org.usfirst.frc1745.deepspace2019;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class Controls {
    private Joystick joystick;
    private final int LEFT_Y = 1;
    private final int RIGHT_X = 4;
    private final int RIGHT_Y = 5;

    private final int RIGHT_BUMPER_ID = 6;
    private final int RIGHT_TRIGGER_ID = 3;

    private final int LEFT_BUMPER_ID = 5;
    private final int LEFT_TRIGGER_ID = 2;

    private final int B_BUTTON_ID = 2;
    private final int Y_BUTTON_ID = 4;
    private final int A_BUTTON_ID = 1;
    private final int X_BUTTON_ID = 3;

    public Controls(Joystick joystick) {
        this.joystick = joystick;
    }
    
    public boolean getBButton() {
        return joystick.getRawButton(B_BUTTON_ID);
    }

    public boolean getAButton() {
        return joystick.getRawButton(A_BUTTON_ID);
    }

    public boolean getXButton() {
        return joystick.getRawButton(X_BUTTON_ID);
    }

    public JoystickButton getYJoystickButton(){
        return new JoystickButton(joystick, Y_BUTTON_ID);
    }

    public JoystickButton getAJoystickButton(){
        return new JoystickButton(joystick, A_BUTTON_ID);
    }

    public double getRightX(double deadzone) {
        double x = joystick.getRawAxis(RIGHT_X);
        if(Math.abs(x) < deadzone) {
            return 0.0;
        } 
        return x;
    }
    
    public double getLeftY(double deadzone) {
        double y = joystick.getRawAxis(LEFT_Y);
        if(Math.abs(y) < deadzone) {
            return 0.0;
        } 
        return y;
    }

    public double getRightY(double deadzone) {
        double y = joystick.getRawAxis(RIGHT_Y);
        if(Math.abs(y) < deadzone) {
            return 0.0;
        } 
        return y;
    }

    public boolean getRightBumper() {
        return joystick.getRawButton(RIGHT_BUMPER_ID);
    }

    public JoystickButton getRightJoystickBumper(){
        return new JoystickButton(joystick, RIGHT_BUMPER_ID);
    }

    public boolean getRightTrigger() {
        return joystick.getRawAxis(RIGHT_TRIGGER_ID) > 0;
    }

    public boolean getLeftBumper() {
        return joystick.getRawButton(LEFT_BUMPER_ID);
    }

    public boolean getLeftTrigger() {
        return joystick.getRawAxis(LEFT_TRIGGER_ID) > 0;
    }
}
