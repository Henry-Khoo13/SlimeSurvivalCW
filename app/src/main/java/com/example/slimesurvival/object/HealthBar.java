package com.example.slimesurvival.object;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.R;


//Health bar displays the health of the player to the screen
public class HealthBar {
    private Player player;
    private int width,height, margin;//margin used to seperate healthbar from background border

    private Paint borderPaint;
    private Paint healthPaint;
    public HealthBar(Context context, Player player) {
        this.player = player;

        this.width = 100;
        this.height = 20;
        this.margin = 2;
        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthbarbackground);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthbar);
        healthPaint.setColor(healthColor);

    }

    public void draw(Canvas canvas){
        float x = (float) player.getPositionX();
        float y = (float) player.getPositionY();
        float distanceToPlayer = 30;
        float healthPointsPercentage = (float)player.getHealthPoints()/player.MAX_HEALTH_POINTS;

        //Draw Border (Background of healthbar)
        float borderLeft, borderTop, borderRight,borderBottom;
        borderLeft = x - width/2;//Centered with /2
        borderRight = x + width/2;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;
        canvas.drawRect(borderLeft, borderTop, borderRight,borderBottom, borderPaint);

        //Draw Health
        float healthWidth, healthHeight, healthLeft, healthTop, healthRight,healthBottom;
        healthWidth =  width - 2*margin;//Centered with /2 margin to seperate elements
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = borderLeft + healthWidth*healthPointsPercentage;//Width will vary depending on health
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        canvas.drawRect(healthLeft, healthTop, healthRight,healthBottom, healthPaint);
    }
}
