package com.example.slimesurvival.object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.GameDisplay;
import com.example.slimesurvival.GameLoop;
import com.example.slimesurvival.R;
import com.example.slimesurvival.Utils;
import com.example.slimesurvival.graphics.Sprite;

//Player is the playable main character of the game, controlled via joystick
//Player's hitbox is a circle and a sprite is used to display them.
//Some functions are handled by the parent class circle and it's parent class game object.
public class Player extends Circle{

    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;//How fast the player can move
    private final Joystick joystick;

    public int MAX_HEALTH_POINTS = 10;
    private HealthBar healthBar;

    private double radius;
    private Paint paint;

    private int healthPoints;
    private Sprite sprite;

    /**
     * Player constructor
     * @param context
     * @param joystick
     * @param positionXi
     * @param positionYi
     * @param radiusi
     * @param sprite
     * @param max_healthi
     */
    public Player(Context context,Joystick joystick, double positionXi, double positionYi, double radiusi,Sprite sprite,int max_healthi){
        super(context,ContextCompat.getColor(context, R.color.player),positionXi,positionYi, radiusi);//Specifying what constructor to take from
        this.joystick = joystick;
        this.healthBar = new HealthBar(context,this);
        this.healthPoints = max_healthi;
        this.MAX_HEALTH_POINTS = max_healthi;
        this.sprite = sprite;
    }


    /**
     * Update handles moving the player based on the joystick.
     */
    public void update() {
        //update speed
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        //Update Position based off speed amount
        positionX += velocityX;
        positionY += velocityY;

        //update direction
        if(velocityX != 0 || velocityY != 0){
            //Normalize velocity to get direction ( unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0,0,velocityX,velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }
    }

    /**
     * Draw handles displaying the player sprite.
     * @param canvas
     * @param gameDisplay
     */
    public void draw(Canvas canvas, GameDisplay gameDisplay){
        sprite.draw(canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(getPositionX())-sprite.getWidth()/2,
                (int)gameDisplay.gameToDisplayCoordinatesY(getPositionY())-sprite.getHeight()/2);
        healthBar.draw(canvas,gameDisplay);
    }

    /**
     * getter and setter functions
     */
    public void setPosition(double x, double y) {
        this.positionX = x;
        this.positionY = y;
    }


    public int getHealthPoints(){
        return healthPoints;
    }

    public void setHealthPoints(int i) {
        if(healthPoints >= 0 ){//prevent health going into the negative
            this.healthPoints = i;
        }

    }
}
