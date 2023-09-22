package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ContinueScreen extends AppCompatActivity {
    private String playerName;
    private int difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_screen);
        ImageButton continueButton = (ImageButton) findViewById(R.id.continueButton);
        ImageButton backButton = (ImageButton) findViewById(R.id.back);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueScreen.this, GameScreen.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueScreen.this, SpriteSelectionScreen.class);
                startActivity(intent);
            }
        });
    }
}