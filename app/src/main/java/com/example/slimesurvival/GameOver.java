package com.example.slimesurvival;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

//Game over text is displayed when the game is over and the player has died
public class GameOver {
    private Context context;
    public GameOver(Context context){
        this.context = context;
    }
    public void draw(Canvas canvas) {
        String text = "Game Over";
        float x = 800;
        float y = 200;
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize= 150;
        paint.setTextSize(textSize);
        canvas.drawText(text,x,y,paint);
    }
}
