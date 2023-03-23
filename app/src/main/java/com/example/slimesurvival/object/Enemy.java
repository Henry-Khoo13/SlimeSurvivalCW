package com.example.slimesurvival.object;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.GameDisplay;
import com.example.slimesurvival.GameLoop;
import com.example.slimesurvival.R;
import com.example.slimesurvival.graphics.Sprite;

/**
 * This Class supports hostile entities that the game loop generates to try and attack the player
 */
public class Enemy extends Circle{

    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;//Player speed but slower
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;//How fast the enemy can move
    private static double SPAWN_PER_MINUTE = 40;
    private static final double SPAWNS_PER_SECOND = SPAWN_PER_MINUTE/60.0;
    private static final double UPDATES_PER_SPAWN =GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    Sprite sprite;

    /**
     * Constructor to generate a enemy
     * @param context
     * @param player
     * @param sprite
     */
    public Enemy(Context context, Player player, Sprite sprite) {
        super(context,
                ContextCompat.getColor(context, R.color.enemy),
                Math.random()*1000,
                Math.random()*1000,
                64);
        this.player = player;
        this.sprite = sprite;
    }

    /**
     * This function is used to check whether an enemy should spawn on a given update loop
     * This will depend on the Spawn rate determined early in the attributes
     * @return boolean
     */
    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        }else{
            updatesUntilNextSpawn--;
            return false;
        }
    }

    /**
     * The Update function determines the distance of the enemy away from the player based on their position
     * The Direction towards the player is calculated before applying the appropriate velocity changes to position towards the player
     */
    @Override
    public void update() {
        //Update the velocity and thus position towards the player

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

    /**
     * The draw function draws the enemy
     * Displaying the sprite on the screen
     * @param canvas
     * @param gameDisplay
     */
    public void draw(Canvas canvas, GameDisplay gameDisplay){
        sprite.draw(canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(getPositionX())-sprite.getWidth()/2,
                (int)gameDisplay.gameToDisplayCoordinatesY(getPositionY())-sprite.getHeight()/2);
    }

}
