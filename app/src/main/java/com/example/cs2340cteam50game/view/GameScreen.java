package com.example.cs2340cteam50game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.content.Intent;
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
    private GameScreenModel gameScreenModel;
    private int currentScreen = 0;
    private PlayerClass player;
    private String name;
    private CountDownTimer timer;
    private ImageView map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //Create associated model and give it access to this GameScreen
        gameScreenModel = new GameScreenModel();
        gameScreenModel.setGameScreen(this);

        //Get ids for display elements
        TextView healthValueDisplay = (TextView) findViewById(R.id.healthValue);
        TextView playerNameDisplay = (TextView) findViewById(R.id.playerName);
        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultySetting);
        TextView scoreDisplay = (TextView) findViewById(R.id.scoreText);

        //Initialize map and give access to the GameScreenModel
        map = (ImageView) findViewById(R.id.gameMap);
        gameScreenModel.setScreen(currentScreen, map);

        //
        double screenWidth = getResources().getDisplayMetrics().widthPixels;
        double screenHeight = getResources().getDisplayMetrics().heightPixels;

        player = PlayerClass.getPlayer();
        player.setScreenWidth(screenWidth);
        player.setScreenHeight(screenHeight);

        player.setxPos(screenWidth / 2);
        player.setyPos(screenHeight - player.getSprite().getIntrinsicHeight());

        //Initialize PlayerView
        RelativeLayout gameLayout = findViewById(R.id.gameLayout);
        playerView = new PlayerView(this);
        gameScreenModel.setPlayerView(playerView);
        player.setSpriteData(playerView);
        player.setGameScreenModel(gameScreenModel);
        gameLayout.addView(playerView);

        //Retrieve Player attributes
        name = player.getUsername();
        int difficulty = player.getDifficultyNum();
        int health = player.getHealthPoints();

        //Update Screen with Player Attributes
        playerNameDisplay.setText(name);
        healthValueDisplay.setText(Integer.toString(health));
        difficultyDisplay.setText(gameScreenModel.difficultySwitch(difficulty));
        scoreDisplay.setText("Score: 50");
        timer = gameScreenModel.startScoreTimer(scoreDisplay);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("KEY", "KeyDown: " + keyCode);
        switch (keyCode) {
        case KeyEvent.KEYCODE_A:
            gameScreenModel.moveLeft();
            break;
        case KeyEvent.KEYCODE_D:
            gameScreenModel.moveRight();
            break;
        case KeyEvent.KEYCODE_W:
            gameScreenModel.moveUp();
            break;
        case KeyEvent.KEYCODE_S:
            gameScreenModel.moveDown();
            break;
        default:
            break;
        }
        playerView.updatePosition();
        return false;
    }

    public void nextRoom() {
        if (currentScreen == 2) {
            Score score = new Score(name, gameScreenModel.getScoreVal());
            Leaderboard leaderBoard = Leaderboard.getLeaderboard();
            leaderBoard.addScore(score);

            timer.cancel();
            Intent intent = new Intent(GameScreen.this, EndScreen.class);
            intent.putExtra("score", score.getScore());
            startActivity(intent);
        } else {
            currentScreen++;
            gameScreenModel.setScreen(currentScreen, map);
            switch (currentScreen) {
            case 1:
                player.setxPos(700);
                player.setyPos(882);
                break;
            case 2:
                player.setxPos(10);
                player.setyPos(312);
                break;
            default:
            }
            playerView.updatePosition();
        }
    }

    public void setPlayerView(PlayerView view) {
        this.playerView = view;
    }

    public GameScreenModel getGameScreenModel() {
        return gameScreenModel;
    }



}