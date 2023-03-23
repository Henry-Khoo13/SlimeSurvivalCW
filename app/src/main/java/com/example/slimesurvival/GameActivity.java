package com.example.slimesurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Main Game activity to start the game itself.
 */
public class GameActivity extends Activity {
    private Game game;

    /**
     * This handles the starting of the game activity and thus the start of the main game running
     * It again modifies the difficulty passed  to it so that it can create an game oject accordingly.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set content view to game
        //Objects in the game render to the screen
        if (getIntent().hasExtra("Difficulty"))
        {
            switch(getIntent().getExtras().getInt("Difficulty")) {
                case 1:
                    game = new Game(this, 1);
                    break;
                case 2:
                    game = new Game(this, 2);
                    break;
                case 3:
                    game = new Game(this, 3);

                    break;
                default:
                    game = new Game(this,1);
            }

        }
        setContentView(game);
    }

    /**
     * This is one part of the activity lifecycle and this step has been modified to make sure that the
     * game reaches a stable pause calling functions before threads start producing errors.
     */
    @Override
    protected void onPause() {
        game.pause();
        super.onPause();

    }


}