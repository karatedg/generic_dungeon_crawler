package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class OpeningScreen extends AppCompatActivity {

    SharedPreferences p1;
    SharedPreferences.Editor e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_screen);

        p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
        e1 = p1.edit();
        e1.clear();
        e1.apply();


        Button exitButton = (Button) findViewById(R.id.exitButton);
        Button nextButton = (Button) findViewById(R.id.startButton);
        TextView textView1 = (TextView) findViewById(R.id.textView1);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OpeningScreen.this, SpriteSelectionScreen.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
            }
        });



    }
}