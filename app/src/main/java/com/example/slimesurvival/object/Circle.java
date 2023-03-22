package com.example.slimesurvival.object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

//Circle is an abstract class which implements a draw method from game object to draw it specifically as a circle
public abstract class Circle extends GameObject {

    protected double radius;
    protected Paint paint;
    public Circle(Context context, int color, double positionXi, double positionYi, double radiusi) {
        super(positionXi, positionYi);
        //radius of circle
        this.radius = radiusi;

        //Colours of circle
        paint = new Paint();
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {

        canvas.drawCircle( (float)positionX, (float)positionY,(float)radius, paint);
    }
}
