package com.example.slimesurvival.object;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.slimesurvival.GameDisplay;

//Parent Class of all objects such as Enemey and Player
public abstract class GameObject {

    protected double positionX;//Shared attributes between enemies and players
    protected double positionY;

    protected double velocityX = 0;
    protected double velocityY= 0;
    protected double directionX= 1;
    protected double directionY= 0;

    public GameObject(double positionXi,double positionYi){
        this.positionX = positionXi;
        this.positionY = positionYi;
    }
    public abstract void draw(Canvas canvas , GameDisplay gameDisplay);
    public abstract void update();

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }
    protected static double GameObjectgetDistanceBetweenObjects(GameObject object1, GameObject object2) {
        return Math.sqrt(
                Math.pow(object2.getPositionX() - object1.getPositionX(),2)+
                        Math.pow(object2.getPositionY() - object1.getPositionY(),2)
        );
    }

    protected double getDirectionX() {
        return directionX;
    }
    protected double getDirectionY() {
        return directionY;
    }
}

