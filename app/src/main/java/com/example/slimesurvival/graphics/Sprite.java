package com.example.slimesurvival.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Sprite is used to position the sprite where it needs to be
 */
public class Sprite {

    private final SpriteSheet spriteSheet;
    private final Rect rect;

    /**
     * Sprite Constructor
     * @param spriteSheet
     * @param rect
     */
    public Sprite(SpriteSheet spriteSheet, Rect rect){
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    /**
     * Draw, places the sprite where it needs to be
     * @param canvas
     * @param x
     * @param y
     */
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
    /**
     * Getters and Setters
     */
    public int getWidth() {
        return rect.width();
    }//return width
    public int getHeight(){
        return rect.height();
    }//return height
}
