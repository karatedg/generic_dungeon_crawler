package com.example.cs2340cteam50game.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.PlayerClass;

public class SpriteSelectionScreen extends AppCompatActivity {
    private int spriteNum = 1;
    private Drawable sprite = null;
    private int difficultyNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprite_selection);

        //Next && Previous
        Button backButton = (Button) findViewById(R.id.backButton);
        Button nextButton = (Button) findViewById(R.id.nextButton);

        //Username Input
        EditText username = (EditText) findViewById(R.id.usernameText);

        //Difficulty Preview
        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultyChoice);

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
        if (PlayerClass.playerExists()) {
            PlayerClass player = PlayerClass.getPlayer();
            username.setText(player.getUsername());
            difficultyNum = player.getDifficultyNum();
            spriteNum = player.getSpriteNum();
            sprite = player.getSprite();
            switch (difficultyNum) {
            case 1:
                difficultyDisplay.setText(R.string.difficulty_easy);
                break;
            case 2:
                difficultyDisplay.setText(R.string.difficulty_medium);
                break;
            case 3:
                difficultyDisplay.setText(R.string.difficulty_hard);
                break;
            default:
                break;
            }
            spriteSelection.setImageDrawable(sprite);
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
                difficultyNum = 2;
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
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                        R.drawable.red_idle, null));
                spriteNum = 1;
                sprite = ResourcesCompat.getDrawable(getResources(), R.drawable.red_idle, null);
            }
        });

        blueSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                        R.drawable.blue_idle, null));
                spriteNum = 2;
                sprite = ResourcesCompat.getDrawable(getResources(), R.drawable.blue_idle, null);
            }
        });

        greenSpriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spriteSelection.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                        R.drawable.green_idle, null));
                spriteNum = 3;
                sprite = ResourcesCompat.getDrawable(getResources(), R.drawable.green_idle, null);
            }
        });

        //Continue and save choices
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PlayerClass player = PlayerClass.getPlayer();
                    player.setUsername(username.getText().toString().trim());
                    player.setDifficultyNum(difficultyNum);
                    player.setSpriteNum(spriteNum);
                    player.setSprite(sprite);
                    Intent intent = new Intent(SpriteSelectionScreen.this, ContinueScreen.class);
                    startActivity(intent);
                } catch (IllegalArgumentException e) {
                    return;
                }
            }
        });

    }

}
