package com.example.slimesurvival;

import com.example.slimesurvival.object.GameObject;

/**
 * Handles the calculations to center the game screen on the player when they're moving around
 */
public class GameDisplay {

    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;

    private GameObject centerObject;

    /**
     * GameDisplay constructor to initilise the gamedisplay on a centered object, in this case it will be used to center on the player
     * @param widthPixels
     * @param heightPixels
     * @param centerObject
     */
    public GameDisplay(int widthPixels, int heightPixels,GameObject centerObject){
        this.centerObject = centerObject;
        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    /**
     * This function is used to update the position of the game display
     * Offsetting accordly to the centered object the player
     */
    public void update(){//Utilising vectors to handle the offset
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();
        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }

    /**
     * This returns the Display X coordinates
     */
    public double gameToDisplayCoordinatesX(double positionX) {
        return positionX + gameToDisplayCoordinatesOffsetX;
    }

    /**
     * This returns the Display Y coordinates
     */
    public double gameToDisplayCoordinatesY(double positionY) {
        return positionY + gameToDisplayCoordinatesOffsetY;
    }
}
