package com.example.slimesurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class GameActivity extends Activity {
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set content view to game
        //Objects in the game render to the screen
        game = new Game(this);
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