package com.example.slimesurvival;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Main Activity is entry points to our application
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivityBtn = findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), GameActivity.class); // Intent is the SecondActivity.java class code
                // how to pass information to another activity
                //startIntent.putExtra("myKey", "HELLO Second Activity"); // This is like a key : value pair input
                startActivity(startIntent);
            }
        });
    }


}