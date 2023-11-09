package com.example.cs2340cteam50game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cs2340cteam50game.model.FireSkullCreator;
import com.example.cs2340cteam50game.model.FireSkullEnemy;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.Score;
import com.example.cs2340cteam50game.model.Leaderboard;
import com.example.cs2340cteam50game.viewmodel.GameScreenModel;

import java.util.ArrayList;


public class GameScreen extends AppCompatActivity {
    private PlayerView playerView;
    private GameScreenModel gameScreenModel;
    private int currentScreen = 0;
    private PlayerClass player;
    private String name;
    RelativeLayout gameLayout = null;
    TextView healthValueDisplay = null;

    ArrayList<View> currentEnemies = new ArrayList<>();

    private FireSkullCreator fsCreator = new FireSkullCreator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //Create associated model and give it access to this GameScreen
        gameScreenModel = new GameScreenModel();
        gameScreenModel.setGameScreen(this);

        //Get ids for display elements
        healthValueDisplay = (TextView) findViewById(R.id.healthValue);
        TextView playerNameDisplay = (TextView) findViewById(R.id.playerName);
        TextView difficultyDisplay = (TextView) findViewById(R.id.difficultySetting);
        TextView scoreDisplay = (TextView) findViewById(R.id.scoreText);

        //Initialize map and give access to the GameScreenModel
        ImageView map = (ImageView) findViewById(R.id.gameMap);
        gameScreenModel.setMap(map);
        gameScreenModel.setScreen(currentScreen);

        //Get Screen Dimensions
        double screenWidth = getResources().getDisplayMetrics().widthPixels;
        double screenHeight = getResources().getDisplayMetrics().heightPixels;

        //Initialize Player
        player = PlayerClass.getPlayer();
        player.setGameScreenModel(gameScreenModel);
        player.setScreenWidth(screenWidth);
        player.setScreenHeight(screenHeight);
        player.setxPos(screenWidth / 2);
        player.setyPos(screenHeight - player.getSprite().getIntrinsicHeight());

        //Initialize PlayerView
        gameLayout = findViewById(R.id.gameLayout);
        playerView = new PlayerView(this);
        gameScreenModel.setPlayerView(playerView);
        player.setSpriteData(playerView);
        gameLayout.addView(playerView);

        //Initialize a FireSkull & FireSkullView
        FireSkullEnemy fsEnemy1 = (FireSkullEnemy) fsCreator.createEnemy();
        FireSkullEnemy fsEnemy2 = (FireSkullEnemy) fsCreator.createEnemy();
        int spriteID = R.drawable.fireskull;
        fsEnemy1.setSprite(getDrawable(spriteID));
        fsEnemy2.setSprite(getDrawable(spriteID));
        fsEnemy1.setxPos(screenWidth * (7.0 / 10.0));
        fsEnemy1.setyPos(screenHeight / 3);
        fsEnemy2.setxPos(screenWidth * (4.0 / 10.0));
        fsEnemy2.setyPos(screenHeight / 3);

        //TODO: CONSTRUCTOR FOR FIRESKULLVIEW TAKES IN A FIRESKULL
        FireSkullView fsView1 = new FireSkullView(this, fsEnemy1);
        FireSkullView fsView2 = new FireSkullView(this, fsEnemy2);
        fsEnemy1.setSpriteData(fsView1);
        fsEnemy2.setSpriteData(fsView2);

        currentEnemies.add(fsView1);
        currentEnemies.add(fsView2);

        gameScreenModel.addEnemy(fsEnemy1);
        gameScreenModel.addEnemy(fsEnemy2);

        gameLayout.addView(fsView1);
        gameLayout.addView(fsView2);

        //Retrieve Player attributes
        name = player.getUsername();
        int difficulty = player.getDifficultyNum();
        int health = player.getHealthPoints();

        //Update Screen with Player Attributes
        playerNameDisplay.setText(name);
        healthValueDisplay.setText(Integer.toString(health));
        difficultyDisplay.setText(gameScreenModel.difficultySwitch(difficulty));
        scoreDisplay.setText("Score: " + gameScreenModel.getScoreVal());
        gameScreenModel.startScoreTimer(scoreDisplay);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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

    public void endGame() {
        Score score = new Score(name, gameScreenModel.getScoreVal());
        Leaderboard leaderBoard = Leaderboard.getLeaderboard();
        leaderBoard.addScore(score);
        Log.d("SCOREADD", "Scored added");
        Intent intent = new Intent(GameScreen.this, EndScreen.class);
        intent.putExtra("score", score.getScore());
        startActivity(intent);
    }

    //NEW CODE
    public void clearEnemies() {
        for (View enemy : currentEnemies) {
            gameLayout.removeView(enemy);
        }
    }

    // NEW CODE
    public void updateHealth(int healthPoints) {
        if (healthPoints == 0) {
            healthValueDisplay.setText("KO");
        } else {
            healthValueDisplay.setText(Integer.toString(healthPoints));
        }
    }
}