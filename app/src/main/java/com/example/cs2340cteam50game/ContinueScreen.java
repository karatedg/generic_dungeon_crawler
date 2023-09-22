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
        Button continueButton = (Button) findViewById(R.id.continueButton);
        Button backButton = (Button) findViewById(R.id.backButton);

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