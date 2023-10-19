package com.example.cs2340cteam50game.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.model.Leaderboard;
import com.example.cs2340cteam50game.viewmodel.GameScreenModel;

public class GameScreen extends AppCompatActivity {

    private PlayerView playerView;
    private RelativeLayout gameLayout;
    private double screenWidth;
    private double screenHeight;

    private int currentScreen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //Button ids
        Button skipToEnd = (Button) findViewById(R.id.skipToEnd);
        Button previousMap = (Button) findViewById(R.id.previousMap);
        Button nextMap = (Button) findViewById(R.id.nextMap);

        //Display item ids
        TextView healthValueDisplay = (TextView) findViewById(R.id.healthValue);
        TextView playerNameDisplay = (TextView) findViewById(R.id.playerName);
        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultySetting);
        TextView scoreDisplay = (TextView) findViewById(R.id.scoreText);

        //Map setup
        ImageView map = (ImageView) findViewById(R.id.gameMap);
        GameScreenModel.setScreen(currentScreen, map);

        //Pass access to the scoreDisplay to the GameScreenModel
        GameScreenModel.setScoreText(scoreDisplay);

        //Set up playerView
        PlayerClass player = PlayerClass.getPlayer();

        gameLayout = findViewById(R.id.gameLayout);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        player.setScreenWidth(screenWidth);
        player.setScreenHeight(screenHeight);


        player.setX(screenWidth / 2);
        player.setY(screenHeight - player.getSprite().getIntrinsicHeight());

        playerView = new PlayerView(this);
        player.setPlayerView(playerView);
        gameLayout.addView(playerView);

        //Get player attributes
        String name = player.getUsername();
        int difficulty = player.getDifficultyNum();
        int health = player.getHealthPoints();
        String difficultyLabel = GameScreenModel.difficultySwitch(difficulty);

        //Update Screen with Player Attributes
        playerNameDisplay.setText(name);
        healthValueDisplay.setText(Integer.toString(health));
        difficultyDisplay.setText(difficultyLabel);

        scoreDisplay.setText("Score: 50");
        CountDownTimer timer = GameScreenModel.startTimer();


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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_A:
            GameScreenModel.moveLeft();
            break;
        case KeyEvent.KEYCODE_D:
            GameScreenModel.moveRight();
            break;
        case KeyEvent.KEYCODE_W:
            GameScreenModel.moveUp();
            break;
        case KeyEvent.KEYCODE_S:
            GameScreenModel.moveDown();
            break;
        default:
            break;
        }
        playerView.updatePosition();
        return true;
    }
}