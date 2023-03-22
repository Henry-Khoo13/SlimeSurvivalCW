package com.example.slimesurvival.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.slimesurvival.R;

public class SpriteSheet {
    private Bitmap bitmap;//Bitmap used for computers to understand graphics
    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.spritesheet,bitmapOptions);
        //Create a new bitmap with the imported sprite sheet
    }
    public Sprite getPlayerSprite(){
        return new Sprite(this, new Rect(0,0,128,128));
    }//Get the player sprite from the sprite sheet selecting the coordinates

    public Sprite getEnemySprite(){
        return new Sprite(this, new Rect(256,0,384,128));
    }//Get the player sprite from the sprite sheet selecting the coordinates
    public Bitmap getBitmap() {
        return bitmap;
    }//return the bitmap
}
