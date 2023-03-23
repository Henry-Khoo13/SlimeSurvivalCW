package com.example.slimesurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameActivity extends Activity {
    private Game game;
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


    @Override
    protected void onStart() {
        Log.d("MainActivity.java","onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java","onResume()");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d("MainActivity.java","onPause()");
        game.pause();
        super.onPause();

    }
    @Override
    protected void onStop() {
        Log.d("MainActivity.java","onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java","onDestroy()");
        super.onDestroy();
    }

}