package com.example.slimesurvival;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Joystick class to control the player
 */
public class Joystick {
    private int outerCircleCenterPositionX ;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    /**
     * Handles the creation of the joystick object
     * @param centerPositionX
     * @param centerPositionY
     * @param outerCircleRadius
     * @param innerCircleRadius
     */
    public Joystick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius){
        //Positioning the joysticks on creation to be centered at the same area so that the inner circle can pull equally away in the outer circle radius
        this.outerCircleCenterPositionX = centerPositionX;
        this.outerCircleCenterPositionY = centerPositionY;//Outside joystick limits
        this.innerCircleCenterPositionX = centerPositionX;
        this.innerCircleCenterPositionY = centerPositionY;//Outside joystick limits

        //Radius' to help create the circles for the joystick
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        //Painting the circles
        this.outerCirclePaint = new Paint();
        this.outerCirclePaint.setColor(Color.GRAY);
        this.outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        this.innerCirclePaint = new Paint();
        this.innerCirclePaint.setColor(Color.GREEN);
        this.innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * Handles the drawing and displaying of the inside actuator of the joystick along with the external outline of the joystick
     * Drawing them both as circles.
     * @param canvas
     */
    public void draw(Canvas canvas) {
        //Draw Outer Circle for joystick
        canvas.drawCircle(
                outerCircleCenterPositionX,
                outerCircleCenterPositionY,
                outerCircleRadius,
                outerCirclePaint
        );

        //Draw Inner Circle for joystick
        canvas.drawCircle(
                innerCircleCenterPositionX,
                innerCircleCenterPositionY,
                innerCircleRadius,
                innerCirclePaint
        );
    }

    /**
     * This function updates the inner actuators position as it will be able the move but the rest of the joystick won't
     */
    public void update() {
        updateInnerCirclePosition();
    }

    /**
     * This function updates the position of the actuator.
     */
    private void updateInnerCirclePosition() {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX*outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY*outerCircleRadius);
    }

    /**
     * This function is used to determine if the user has pressed the joystick or not.
     * @param x
     * @param y
     * @return
     */
    public boolean isPressed(double x, double y) {
        joystickCenterToTouchDistance = Utils.getDistanceBetweenPoints(
                outerCircleCenterPositionX,
                outerCircleCenterPositionY,
                x,
                y

        );
    return joystickCenterToTouchDistance < outerCircleRadius;//If pressing inside the joystick outer circle radius return true
    }


    /**
     * This function is used to  make sure the actuator is within the range of the outer circle
     * @param x
     * @param y
     */
    public void setActuator(double x, double y) {
        double DeltaX = x - outerCircleCenterPositionX;//Calculating the distance from center on X
        double DeltaY = y - outerCircleCenterPositionY;//Calculating the distance from center on Y
        double deltaDistance = Utils.getDistanceBetweenPoints(
                0,
                0,
                DeltaX,
                DeltaY

        ); //Calculating the distance from center

        if(deltaDistance < outerCircleRadius){
            actuatorX = DeltaX/outerCircleRadius;
            actuatorY = DeltaY/outerCircleRadius;
            //Moving the actuator if inside the radius
        }else{
            actuatorX = DeltaX/deltaDistance;
            actuatorY = DeltaY/deltaDistance;
            //Handle the actuator when on the circumference of the edge
        }
    }

    /**
     * This function is used to reset the actuator when released.
     */
    public void resetActuator() {
        this.actuatorX = 0.0;
        this.actuatorY = 0.0;
    }

    /*
     * Getters and setters
     */

    public void setIsPress(boolean b) {
        this.isPressed = b;
    }

    public boolean getisPressed() {
        return this.isPressed;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
