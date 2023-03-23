package com.example.slimesurvival;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//Main Activity is entry point to our game
//It handles the main menu which can modify the next game activity on how it will run
public class MainActivity extends Activity {
    private int Difficulty;
    private TextView DifficultyText;
    private String TEXT;

    /**
     * This activity starts as soon as the app is initialised
     * Within it, it is determining the difficulty based on button inputs setting the display text to correspond with the difficulty
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DifficultyText = findViewById(R.id.DifficultyText);
        switch(Difficulty){
            case 3:
                TEXT = "Difficulty: Hard";
                DifficultyText.setText(TEXT);
                break;
            case 2:
                TEXT = "Difficulty: Normal";
                DifficultyText.setText(TEXT);
                break;
            case 1:
                TEXT = "Difficulty: Easy";
                DifficultyText.setText(TEXT);
                break;
            default:
                TEXT = "Difficulty: Easy";
                DifficultyText.setText(TEXT);
                break;
        }


        Button difficultyBtn = findViewById(R.id.difficultyBtn);

        /**
         * Below is the button function, each button as their own function with a click function within to determine how it should respond
         * When the button is pressed. In this case it modifies the difficulty to the one indicated to the button
         */
        difficultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Difficulty = 3;
                TEXT = "Difficulty: Hard";
                DifficultyText.setText(TEXT);
            }
        });

        /**
         * Below is the button function, each button as their own function with a click function within to determine how it should respond
         * When the button is pressed. In this case it modifies the difficulty to the one indicated to the button
         */
        Button DifficultyBtn1 = findViewById(R.id.DifficultyBtn1);
        DifficultyBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Difficulty = 2;
                TEXT = "Difficulty: Normal";
                DifficultyText.setText(TEXT);
            }
        });

        Button difficultyBtn2 = findViewById(R.id.difficultyBtn2);

        /**
         * Below is the button function, each button as their own function with a click function within to determine how it should respond
         * When the button is pressed. In this case it modifies the difficulty to the one indicated to the button
         */
        difficultyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Difficulty = 1;
                TEXT = "Difficulty: Easy";
                DifficultyText.setText(TEXT);
            }
        });

        Button StartButton = findViewById(R.id.StartButton);

        /**
         * Below is the button function, each button as their own function with a click function within to determine how it should respond
         * When the button is pressed. This button begins the next activity with intent and passes the difficulty selected.
         */
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class); // Intent is the SecondActivity.java class code
                // how to pass information to another activity
                startIntent.putExtra("Difficulty", Difficulty); // This is like a key : value pair input
                startActivity(startIntent);
            }
        });
    }


    /**
     * This function is one part of the activity life cycle and handles how the activity responds when being taken out of pause.#
     * In this case it takes the shared perferences to save the preference on what difficulty was selected.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("Difficulty_SharedPref", MODE_PRIVATE);

        String difficultyText = sh.getString("DifficultyText", "");
        int difficulty = sh.getInt("Difficulty", 0);

        // Setting the fetched data
        // in the EditTexts
        switch(difficulty){
            case 3:
                TEXT = "Difficulty: Hard";
                DifficultyText.setText(TEXT);
                break;
            case 2:
                TEXT = "Difficulty: Normal";
                DifficultyText.setText(TEXT);
                break;
            case 1:
                TEXT = "Difficulty: Easy";
                DifficultyText.setText(TEXT);
                break;
        }

    }

    /**
     * This function is one part of the activity life cycle and handles how the activity responds when being paused.
     * In this case it stores the difficulty variable to Shared pref.
     */
    @Override
    protected void onPause() {
        super.onPause();

        //Shared preference created
        SharedPreferences sharedPreferences = getSharedPreferences("Difficulty_SharedPref", MODE_PRIVATE);
        SharedPreferences.Editor shEdit = sharedPreferences.edit();

        shEdit.putString("DifficultyText",TEXT);//Storing preference data
        shEdit.putInt("Difficulty", Difficulty);
        shEdit.apply();
    }

}