package com.example.slimesurvival.object;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.GameDisplay;
import com.example.slimesurvival.R;


//Health bar displays the health of the player to the screen
public class HealthBar {
    private Player player;
    private int width,height, margin;//margin used to seperate healthbar from background border

    private Paint borderPaint;
    private Paint healthPaint;

    /**
     * Healthbar constructor
     * @param context
     * @param player
     */
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

    /**
     * Healthbar is spit into two parts, the inner and outline, the inside being the health, and the outline being something to gage against
     * The two rectangles are created with health being removed based on the percentage of health that palyer has
     * They are positioned to be above the players head at all times.
     * @param canvas
     * @param gameDisplay
     */
    public void draw(Canvas canvas, GameDisplay gameDisplay){
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
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(borderLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(borderRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(borderBottom), borderPaint);

        //Draw Health
        float healthWidth, healthHeight, healthLeft, healthTop, healthRight,healthBottom;
        healthWidth =  width - 2*margin;//Centered with /2 margin to seperate elements
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = borderLeft + healthWidth*healthPointsPercentage;//Width will vary depending on health
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        canvas.drawRect(
                (float) gameDisplay.gameToDisplayCoordinatesX(healthLeft),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthTop),
                (float) gameDisplay.gameToDisplayCoordinatesX(healthRight),
                (float) gameDisplay.gameToDisplayCoordinatesY(healthBottom), healthPaint);
    }
}
