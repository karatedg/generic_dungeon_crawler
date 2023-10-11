package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.cs2340cteam50game.GameObjects.PlayerClass;

public class GameScreen extends AppCompatActivity {

    private int health;
    private String name;
    private int difficulty;
    private String difficultyLabel;
    private int spriteNum;
    //private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //new stuff here to see if this works:
//        game = new Game(this);
//        setContentView(game);

        //getting ids for different elements that will be needed
        Button skipToEnd = (Button) findViewById(R.id.skipToEnd);
        TextView healthValue = (TextView) findViewById(R.id.healthValue);
        TextView playerName = (TextView) findViewById(R.id.playerName);
        TextView difficultySetting = (TextView) findViewById(R.id.difficultySetting);
        ImageView playerSprite = (ImageView) findViewById(R.id.playerSprite);

        PlayerClass player = PlayerClass.getPlayer();
        name = player.getUsername();
        difficulty = player.getDifficultyNum();
        spriteNum = player.getSpriteNum();
        health = player.getHealthPoints();

        switch (difficulty) {
        case 1:
            difficultyLabel = "Easy";
            break;
        case 2:
            difficultyLabel = "Medium";
            break;
        case 3:
            difficultyLabel = "Hard";
            break;
        default:
            break;
        }

        playerName.setText(name);
        healthValue.setText(Integer.toString(health));
        difficultySetting.setText(difficultyLabel);

        switch (spriteNum) {
        case 1:
            playerSprite.setImageResource(R.drawable.red_idle);
            break;
        case 2:
            playerSprite.setImageResource(R.drawable.blue_idle);
            break;
        case 3:
            playerSprite.setImageResource(R.drawable.green_idle);
            break;
        default:
            break;
        }

        //skip to end screen when button pressed
        skipToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameScreen.this, EndScreen.class);
                startActivity(intent);
            }
        });

    }
}