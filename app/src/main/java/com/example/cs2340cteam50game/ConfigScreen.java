package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class ConfigScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_screen);


        Button button1 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfigScreen.this, OpeningScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences prefs1 = getSharedPreferences("playerChoices", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = getSharedPreferences("playerChoices", MODE_PRIVATE).edit();
        //Save user inputs into the sharedPreferences
        prefEditor.apply();

        Log.d("Prefs Saved", "Player preferences successfully saved");
    }
}