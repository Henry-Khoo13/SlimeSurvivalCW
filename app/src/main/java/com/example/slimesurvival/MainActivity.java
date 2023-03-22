package com.example.slimesurvival;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.view.Window;//Setting to full screen
import android.view.WindowManager;

//Main Activity is entry points to our application
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set content view to game
        //Objects in the game render to the screen
        setContentView(new Game(this));
    }
}