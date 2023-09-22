package com.example.cs2340cteam50game;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SpriteSelectionScreen extends AppCompatActivity {
    private int spriteNum = 1;
    private int difficultyNum = 1;

    SharedPreferences p1;
    SharedPreferences.Editor e1;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_sprite_selection);

        //Next && Previous
        Button backButton = (Button) findViewById(R.id.backButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        //Username Input
        EditText username = (EditText) findViewById(R.id.usernameText);

        //Difficulty Preview
        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultyDisplay);

        //Difficulty Buttons
        Button easyButton = (Button) findViewById(R.id.easyButton);
        Button medButton  = (Button) findViewById(R.id.mediumButton);
        Button hardButton = (Button) findViewById(R.id.hardButton);

        //Sprite Buttons
        ImageButton redSpriteButton = (ImageButton) findViewById(R.id.redSpriteButton);
        ImageButton blueSpriteButton = (ImageButton) findViewById(R.id.blueSpriteButton);
        ImageButton greenSpriteButton = (ImageButton) findViewById(R.id.greenSpriteButton);
        ImageView spriteSelection = (ImageView) findViewById(R.id.spriteDisplay);

        //Check for and enforce previous choices
        p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
        username.setText(p1.getString("username", ""));
        switch (p1.getInt("difficulty", 0)) {
        case 1:
            difficultyDisplay.setText(R.string.difficulty_easy);
            difficultyNum = 1;
            break;
        case 2:
            difficultyDisplay.setText(R.string.difficulty_medium);
            difficultyNum = 2;
            break;
        case 3:
            difficultyDisplay.setText(R.string.difficulty_hard);
            difficultyNum = 3;
            break;
        default:
            break;
        }
        switch (p1.getInt("sprite", 0)) {
        case 1:
            spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red_idle, null));
            spriteNum = 1;
            break;
        case 2:
            spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.blue_idle, null));
            spriteNum = 2;
            break;
        case 3:
            spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.green_idle, null));
            spriteNum = 3;
            break;
        default:
            break;
        }


        //Return to previous screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpriteSelectionScreen.this, OpeningScreen.class);
                startActivity(intent);
            }
        });


        //Choose difficulty
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficultyDisplay.setText(R.string.difficulty_easy);
                difficultyNum = 1;
            }
        });

        medButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficultyDisplay.setText(R.string.difficulty_medium);
                difficultyNum =2;
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                difficultyDisplay.setText(R.string.difficulty_hard);
                difficultyNum = 3;
            }
        });

        //Choose sprite
        redSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red_idle, null));
                spriteNum = 1;
            }
        });

        blueSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.blue_idle, null));
                spriteNum = 2;
            }
        });

        greenSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.green_idle, null));
                spriteNum = 3;
            }
        });


        //Continue and save choices
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().equals("")) {
                    return;
                } else {
                    p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
                    e1 = p1.edit();
                    e1.putString("username", username.getText().toString());
                    e1.putInt("difficulty", difficultyNum);
                    e1.putInt("sprite", spriteNum);
                    e1.apply();
                    Intent intent = new Intent(SpriteSelectionScreen.this, ContinueScreen.class);
                    startActivity(intent);
                }
            }
        });

    }

}
