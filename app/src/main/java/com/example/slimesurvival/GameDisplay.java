package com.example.slimesurvival;

import com.example.slimesurvival.object.GameObject;

public class GameDisplay {

    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;

    private GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels,GameObject centerObject){
        this.centerObject = centerObject;
        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }
    public void update(){//Utilising vectors to handle the offset
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();
        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }
    public double gameToDisplayCoordinatesX(double positionX) {
        return positionX + gameToDisplayCoordinatesOffsetX;
    }
    public double gameToDisplayCoordinatesY(double positionY) {
        return positionY + gameToDisplayCoordinatesOffsetY;
    }
}
