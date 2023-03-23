package com.example.slimesurvival.object;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.slimesurvival.GameDisplay;

//Parent Class of all objects such as Enemy and Player
public abstract class GameObject {

    protected double positionX;//Shared attributes between enemies and players
    protected double positionY;

    protected double velocityX = 0;
    protected double velocityY= 0;
    protected double directionX= 1;
    protected double directionY= 0;

    /**
     * GameObject constructor to group some common functions together
     * @param positionXi
     * @param positionYi
     */
    public GameObject(double positionXi,double positionYi){
        this.positionX = positionXi;
        this.positionY = positionYi;
    }

    /**
     * Draw is used for drawing objects to the screen
     * @param canvas
     * @param gameDisplay
     */
    public abstract void draw(Canvas canvas , GameDisplay gameDisplay);

    /**
     * This is the abstract class to update the object
     */
    public abstract void update();

    /**
     * This function handles the distance between two given objects returning the result.
     * @param object1
     * @param object2
     * @return
     */
    protected static double GameObjectgetDistanceBetweenObjects(GameObject object1, GameObject object2) {
        return Math.sqrt(
                Math.pow(object2.getPositionX() - object1.getPositionX(),2)+
                        Math.pow(object2.getPositionY() - object1.getPositionY(),2)
        );
    }

    /**
     * Getters and setters
     */
    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }


    protected double getDirectionX() {
        return directionX;
    }
    protected double getDirectionY() {
        return directionY;
    }
}

