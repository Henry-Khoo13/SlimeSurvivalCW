package com.example.slimesurvival.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect){
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }//Create a sprite constructor
    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(
                        x,y,x+getWidth(), y+getHeight()
                ),
                null
        );
    }//Draw the sprite using the bitmap, positioning them with given x and y

    public int getWidth() {
        return rect.width();
    }//return width
    public int getHeight(){
        return rect.height();
    }//return height
}
