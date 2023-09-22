package com.example.cs2340cteam50game;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpriteSelectionScreen extends AppCompatActivity {
    private int spriteNum = 1;
    private int difficultyNum = 1;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_sprite_selection);

        Button backButton = (Button) findViewById(R.id.backButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        EditText username = (EditText) findViewById(R.id.usernameText);
        SharedPreferences p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
        Log.d("USERNAME", p1.getString("username", "no name"));


        if (!(p1.getString("username", "").equals(""))) {
            String prevName = p1.getString("username", "");
            username.setText(prevName);
        }

        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultyDisplay);

        Button easyButton = (Button) findViewById(R.id.easyButton);
        Button medButton  = (Button) findViewById(R.id.mediumButton);
        Button hardButton = (Button) findViewById(R.id.hardButton);

        ImageButton redSpriteButton = (ImageButton) findViewById(R.id.redSpriteButton);
        ImageButton blueSpriteButton = (ImageButton) findViewById(R.id.blueSpriteButton);
        ImageButton greenSpriteButton = (ImageButton) findViewById(R.id.greenSpriteButton);
        ImageView spriteSelection = (ImageView) findViewById(R.id.spriteDisplay);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpriteSelectionScreen.this, OpeningScreen.class);
                startActivity(intent);
            }
        });

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

        redSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red_idle, null));
            }
        });

        blueSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.blue_idle, null));
            }
        });

        greenSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.green_idle, null));
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString() == null || username.getText().toString().equals("")) {
                    return;
                } else {
                    Intent intent = new Intent(SpriteSelectionScreen.this, ContinueScreen.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        EditText username = (EditText) findViewById(R.id.usernameText);
        SharedPreferences p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
        SharedPreferences.Editor e1 = p1.edit();
        e1.putString("username", username.getText().toString());
        e1.putInt("difficulty", difficultyNum);
        e1.putInt("sprite", spriteNum);
        e1.apply();
    }

}
