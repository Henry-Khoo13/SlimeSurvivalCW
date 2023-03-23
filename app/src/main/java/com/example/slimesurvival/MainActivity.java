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

//Main Activity is entry points to our application
public class MainActivity extends Activity {
    private int Difficulty;
    private TextView DifficultyText;
    private String TEXT;
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
        difficultyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Difficulty = 3;
                TEXT = "Difficulty: Hard";
                DifficultyText.setText(TEXT);
            }
        });

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
        difficultyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Difficulty = 1;
                TEXT = "Difficulty: Easy";
                DifficultyText.setText(TEXT);
            }
        });

        Button StartButton = findViewById(R.id.StartButton);
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
        Difficulty = difficulty;
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
        }

    }


    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("SCORE_SharedPref", MODE_PRIVATE);
        SharedPreferences.Editor shEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        shEdit.putString("DifficultyText",TEXT);
        shEdit.putInt("Difficulty", Difficulty);
        shEdit.apply();
    }

}