package com.example.cs2340cteam50game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.model.Leaderboard;
import com.example.cs2340cteam50game.viewmodel.GameScreenModel;

public class GameScreen extends AppCompatActivity {

    private int health;
    private String name;
    private int difficulty;
    private String difficultyLabel;
    private int spriteNum;



    private int currentScreen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //getting ids for different elements that will be needed
        Button skipToEnd = (Button) findViewById(R.id.skipToEnd);
        TextView healthValue = (TextView) findViewById(R.id.healthValue);
        TextView playerName = (TextView) findViewById(R.id.playerName);
        TextView difficultySetting = (TextView) findViewById(R.id.difficultySetting);
        ImageView playerSprite = (ImageView) findViewById(R.id.playerSprite);

        TextView scoreText = (TextView) findViewById(R.id.scoreText);
        ImageView map = (ImageView) findViewById(R.id.gameMap);
        Button previousMap = (Button) findViewById(R.id.previousMap);
        Button nextMap = (Button) findViewById(R.id.nextMap);

        PlayerClass player = PlayerClass.getPlayer();
        name = player.getUsername();
        difficulty = player.getDifficultyNum();
        spriteNum = player.getSpriteNum();
        health = player.getHealthPoints();

        difficultyLabel = GameScreenModel.difficultySwitch(difficulty);

        playerName.setText(name);
        healthValue.setText(Integer.toString(health));
        difficultySetting.setText(difficultyLabel);

        GameScreenModel.spriteSet(spriteNum, playerSprite);

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

        scoreText.setText("Score: 50");

        CountDownTimer timer = GameScreenModel.startTimer(scoreText);


        //skip to end screen when button pressed
        skipToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score score = new Score(name, GameScreenModel.getScoreVal());
                Leaderboard leaderBoard = Leaderboard.getLeaderboard();
                leaderBoard.addScore(score);
                timer.cancel();
                Intent intent = new Intent(GameScreen.this, EndScreen.class);
                intent.putExtra("score", score.getScore());
                startActivity(intent);
            }
        });

        nextMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentScreen++;
                currentScreen = GameScreenModel.setScreen(currentScreen, map);
            }
        });

        previousMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentScreen--;
                currentScreen = GameScreenModel.setScreen(currentScreen, map);
            }
        });

    }
}