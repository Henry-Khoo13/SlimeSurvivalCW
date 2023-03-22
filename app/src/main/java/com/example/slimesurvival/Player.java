package com.example.slimesurvival;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS;//How fast the player can mvoe
    private double positionX;
    private double positionY;
    private double radius;
    private Paint paint;
    private double velocityX;
    private double velocityY;

    public Player(Context context, double positionXi, double positionYi, double radiusi){
        this.positionX = positionXi;
        this.positionY = positionYi;
        this.radius = radiusi;

        paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.player);
        paint.setColor(color);

    }
    public void draw(Canvas canvas) {

        canvas.drawCircle( (float)positionX, (float)positionY,(float)radius, paint);
    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }
}
