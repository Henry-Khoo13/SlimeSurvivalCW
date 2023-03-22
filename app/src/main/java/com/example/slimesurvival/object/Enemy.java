package com.example.slimesurvival.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.GameLoop;
import com.example.slimesurvival.R;

//Enemy that is moving towards the player to do damage
public class Enemy extends Circle{

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;//Player speed but slower
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;//How fast the enemy can move
    private final Player player;

    public Enemy(Context context, Player player, double positionXi, double positionYi, double radiusi) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionXi, positionYi, radiusi);
        this.player = player;
    }

    @Override
    public void update() {
        //Update the volocity towards the player

        //Calculate vector from enemy to player (x AND y)
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;


        //Calculate distance between enemy and player
        double distanceToPlayer = GameObjectgetDistanceBetweenObjects(this, player);


        //Calculate direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        //set velocity in the direction to the player
        if(distanceToPlayer >0){ //Avoiding divide by zero such as if the object is ontop of another object
            velocityX = directionX*MAX_SPEED;
            velocityY= directionY*MAX_SPEED;
        } else{
            velocityX = 0;
            velocityY = 0;
        }

        //Update the position of the enemy
        positionX += velocityX;
        positionY += velocityY;
    }

    private double GameObjectgetDistanceBetweenObjects(GameObject object1, GameObject object2) {
        return Math.sqrt(
                Math.pow(object2.getPositionX() - object1.getPositionX(),2)+
                        Math.pow(object2.getPositionY() - object1.getPositionY(),2)
        );
    }
}
