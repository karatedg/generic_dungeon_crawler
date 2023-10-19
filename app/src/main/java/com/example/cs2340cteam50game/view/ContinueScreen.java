package com.example.cs2340cteam50game.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.R;

public class ContinueScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_screen);

        //Button ids
        Button continueButton = (Button) findViewById(R.id.continueButton);
        Button backButton = (Button) findViewById(R.id.backButton);

        //View ids
        TextView difficultyString = (TextView) findViewById(R.id.difficultyString);
        TextView usernameString = (TextView) findViewById(R.id.usernameString);
        ImageView spriteChoice = (ImageView) findViewById(R.id.spriteChoice);

        //Retrieve player attributes
        PlayerClass player = PlayerClass.getPlayer();
        usernameString.setText(player.getUsername());
        spriteChoice.setImageDrawable(player.getSprite());

        //Set difficulty text
        switch (player.getDifficultyNum()) {
        case 1:
            difficultyString.setText(R.string.difficulty_easy);
            break;
        case 2:
            difficultyString.setText(R.string.difficulty_medium);
            break;
        case 3:
            difficultyString.setText(R.string.difficulty_hard);
            break;
        default:
            break;
        }

        //Continue functionality
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueScreen.this, GameScreen.class);
                startActivity(intent);
            }
        });

        //Back functionality
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContinueScreen.this, SpriteSelectionScreen.class);
                startActivity(intent);
            }
        });
    }
}