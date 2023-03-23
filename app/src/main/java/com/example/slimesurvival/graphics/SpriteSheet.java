package com.example.slimesurvival.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.slimesurvival.R;

/**
 * Spritesheet is used to take an image and convert it into a bitmap to be used within the program
 */
public class SpriteSheet {
    private Bitmap bitmap;//Bitmap used for computers to understand graphics

    /**
     * Spritesheet constructor , handles fetching the sprite sheet from the images
     * @param context
     */
    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spritesheet,bitmapOptions);
        //Create a new bitmap with the imported sprite sheet
    }

    /**
     * This function takes from the spritesheet the player sprite to which can be sent out to be displayed
     * @return
     */
    public Sprite getPlayerSprite(){
        return new Sprite(this, new Rect(0,0,128,128));
    }//Get the player sprite from the sprite sheet selecting the coordinates

    /**
     * This function takes from the spritesheet the enemy sprite to which can be sent out to be displayed
     * @return
     */
    public Sprite getEnemySprite(){
        return new Sprite(this, new Rect(256,0,384,128));
    }//Get the player sprite from the sprite sheet selecting the coordinates

    /**
     * Getters and setters
     */

    public Bitmap getBitmap() {
        return bitmap;
    }//return the bitmap
}
