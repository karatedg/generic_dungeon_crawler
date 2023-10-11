package com.example.cs2340cteam50game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.PlaybackParams;
import android.media.tv.TimelineRequest;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends AppCompatActivity {

    private int health;
    private String name;
    private int difficulty;
    private String difficultyLabel;
    private int spriteNum;
    private SharedPreferences p1;
    int scoreVal;

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

        p1 = getSharedPreferences("PlayerChoices", MODE_PRIVATE);
        name = p1.getString("username", "player");
        difficulty = p1.getInt("difficulty", 1);
        spriteNum = p1.getInt("sprite", 1);

        switch (difficulty) {
        case 1:
            health = 150;
            difficultyLabel = "Easy";
            break;
        case 2:
            health = 100;
            difficultyLabel = "Normal";
            break;
        case 3:
            health = 75;
            difficultyLabel = "Hard";
            break;
        default:
            health = 100;
            difficultyLabel = "Normal";
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
            playerSprite.setImageResource(R.drawable.green_idle);
            break;
        }

        scoreText.setText("Score: 50");
        new CountDownTimer((50) * 3000, 1000) {
            public void onTick(long millisUntilFinished) {
                scoreVal = (int) (millisUntilFinished / 1000);
                scoreText.setText("Score: " + scoreVal);
            }
            public void onFinish() {
                scoreText.setText("Score: 0");
            }
        }.start();

        //skip to end screen when button pressed
        skipToEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score score = new Score(name, scoreVal);
                Leaderboard leaderBoard = new Leaderboard();
                leaderBoard.addScore(score);
                System.out.println(score.getScore());
                Intent intent = new Intent(GameScreen.this, EndScreen.class);
                intent.putExtra("score", score.getScore());
                startActivity(intent);
            }
        });

    }
}