package com.example.slimesurvival.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.GameLoop;
import com.example.slimesurvival.R;

public class Spell extends Circle {
    private final Player spellcaster;
    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;//How fast the player can move
    public Spell(Context context, Player spellcaster) {
        super(context,
                ContextCompat.getColor(context, R.color.spell),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),
                25);
        this.spellcaster = spellcaster;
        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;
    }


    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
