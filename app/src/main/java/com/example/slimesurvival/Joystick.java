package com.example.slimesurvival;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

    public void update() {
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCenterPositionX = (int) (outerCircleCenterPositionX + actuatorX*outerCircleRadius);
        innerCircleCenterPositionY = (int) (outerCircleCenterPositionY + actuatorY*outerCircleRadius);
    }

    public boolean isPressed(double x, double y) {
        joystickCenterToTouchDistance = Math.sqrt(
                Math.pow(outerCircleCenterPositionX - x,2)+
                Math.pow(outerCircleCenterPositionY - y,2)
        );
        return joystickCenterToTouchDistance < outerCircleRadius;//If pressing inside the joystick outer circle radius return true
    }

    public void setIsPress(boolean b) {
        this.isPressed = b;
    }

    public boolean getisPressed() {
        return this.isPressed;
    }

    public void setActuator(double x, double y) {
        double DeltaX = x - outerCircleCenterPositionX;//Calculating the distance from center on X
        double DeltaY = y - outerCircleCenterPositionY;//Calculating the distance from center on Y
        double deltaDistance = Math.sqrt(
                Math.pow(DeltaX,2)+
                Math.pow(DeltaY,2)
        );//Calculating the distance from center

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

    public void resetActuator() {
        this.actuatorX = 0.0;
        this.actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
