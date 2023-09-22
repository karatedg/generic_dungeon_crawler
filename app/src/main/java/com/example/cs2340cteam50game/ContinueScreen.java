package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ContinueScreen extends AppCompatActivity {
    SharedPreferences p1;
    SharedPreferences.Editor e1;

    private int spriteNum;
    private int difficultyNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_screen);
        Button continueButton = (Button) findViewById(R.id.continueButton);
        Button backButton = (Button) findViewById(R.id.backButton);

        TextView difficultyString = (TextView) findViewById(R.id.difficultyString);
        TextView usernameString = (TextView) findViewById(R.id.usernameString);
        ImageView spriteChoice = (ImageView) findViewById(R.id.spriteChoice);

        p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
        usernameString.setText(p1.getString("username",""));

        switch (p1.getInt("sprite", 0)) {
            case 1:
                spriteChoice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red_idle, null));
                spriteNum = 1;
                break;
            case 2:
                spriteChoice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.blue_idle, null));
                spriteNum = 2;
                break;
            case 3:
                spriteChoice.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.green_idle, null));
                spriteNum = 3;
                break;
            default:
                break;
        }
        switch (p1.getInt("difficulty", 0)) {
            case 1:
                difficultyString.setText(R.string.difficulty_easy);
                difficultyNum = 1;
                break;
            case 2:
                difficultyString.setText(R.string.difficulty_medium);
                difficultyNum = 2;
                break;
            case 3:
                difficultyString.setText(R.string.difficulty_hard);
                difficultyNum = 3;
                break;
            default:
                break;
        }

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