package com.example.slimesurvival.object;

import android.graphics.Canvas;
import android.graphics.Paint;

//Parent Class of all objects such as Enemey and Player
public abstract class GameObject {
    protected double positionX;//Shared attributes between enemies and players
    protected double positionY;

    protected double velocityX;
    protected double velocityY;
    public GameObject(double positionXi,double positionYi){
        this.positionX = positionXi;
        this.positionY = positionYi;
    }
    public abstract void draw(Canvas canvas);
    public abstract void update();

    protected double getPositionX() {
        return positionX;
    }
    protected double getPositionY() {
        return positionY;
    }
}

