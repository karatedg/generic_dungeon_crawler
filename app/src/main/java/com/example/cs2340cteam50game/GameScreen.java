package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    private int health;
    private String name;
    private int difficulty;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //getting ids for different elements that will be needed
        Button skipToEnd = (Button) findViewById(R.id.skipToEnd);
        TextView healthValue = (TextView) findViewById(R.id.healthValue);



        skipToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameScreen.this, EndScreen.class);
                startActivity(intent);
            }
        });

    }

}