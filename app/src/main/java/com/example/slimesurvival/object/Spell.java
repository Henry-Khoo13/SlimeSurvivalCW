package com.example.slimesurvival.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.slimesurvival.GameLoop;
import com.example.slimesurvival.R;

/**
 * This class handles the objects which are fired from the player
 * Most functions are handled by the parent class Circle, and it's parent class GameObject
 */
public class Spell extends Circle {
    private final Player spellcaster;
    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;//How fast the player can move

    /**
     * This handles the contructor to creating a new spell
     * @param context
     * @param spellcaster
     */
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


    /**
     * This updates the position of the spell.
     */
    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
