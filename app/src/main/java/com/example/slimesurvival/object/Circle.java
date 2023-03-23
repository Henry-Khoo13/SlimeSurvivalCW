package com.example.slimesurvival.object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.slimesurvival.GameDisplay;

//Circle is an abstract class which implements a draw method from game object to draw it specifically as a circle
public abstract class Circle extends GameObject {

    protected double radius;
    protected Paint paint;

    /**
     * Circle constructor
     * @param context
     * @param color
     * @param positionXi
     * @param positionYi
     * @param radiusi
     */
    public Circle(Context context, int color, double positionXi, double positionYi, double radiusi) {
        super(positionXi, positionYi);
        //radius of circle
        this.radius = radiusi;

        //Colours of circle
        paint = new Paint();
        paint.setColor(color);
    }

    /**
     * This function checks if two objects are colliding based on position and radi
     * @param object1
     * @param object2
     * @return
     */
    public static boolean isColliding(Circle object1, Circle object2) {
        double distance = getDistanceBetweenObjects(object1,object2);
        double distanceToCollision = object1.getRadius() + object2.getRadius();
        //If the 'distance' between objects lower than 'distancetocollision' total they are colliding
        if(distance<distanceToCollision)
            return true;
        else
            return false;
    }

    /**
     * Draws the circle if there isn't a sprite.
     * @param canvas
     * @param gameDisplay
     */
    public void draw(Canvas canvas, GameDisplay gameDisplay) {

        canvas.drawCircle(
                (float)gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float)gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float)radius,
                paint);
    }

    /**
     * getter and setter
     */
    private double getRadius() {
        return radius;
    }//return radius

}
