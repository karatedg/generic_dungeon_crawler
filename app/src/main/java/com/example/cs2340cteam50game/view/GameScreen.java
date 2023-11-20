package com.example.cs2340cteam50game.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.content.Intent;

import android.view.MotionEvent;
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
    private GameScreenModel gameScreenModel;
    private int currentScreen = 0;
    private PlayerClass player;
    private String name;
    private RelativeLayout gameLayout = null;
    private TextView healthValueDisplay = null;

    private ImageView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity);

        //Create associated model and give it access to this GameScreen; pass necessary drawables
        gameScreenModel = new GameScreenModel();
        gameScreenModel.setGameScreen(this);

        gameScreenModel.setFsSprite(getDrawable(R.drawable.fireskull));
        gameScreenModel.setbSprite(getDrawable(R.drawable.beast));
        gameScreenModel.setgSprite(getDrawable(R.drawable.ghost));
        gameScreenModel.setdSprite(getDrawable(R.drawable.demon));

        //Get ids for display elements
        healthValueDisplay = findViewById(R.id.healthValue);
        TextView playerNameDisplay = findViewById(R.id.playerName);
        TextView difficultyDisplay = findViewById(R.id.difficultySetting);
        TextView scoreDisplay = findViewById(R.id.scoreText);

        //Initialize map and give access to the GameScreenModel
        map = findViewById(R.id.gameMap);
        gameScreenModel.setMap(map);
        gameScreenModel.setScreen(currentScreen);

        //Get Screen Dimensions
        double screenWidth = getResources().getDisplayMetrics().widthPixels;
        double screenHeight = getResources().getDisplayMetrics().heightPixels;

        //Initialize Player & PlayerView
        initializePlayer(screenWidth, screenHeight);
        initializePlayerView();

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

        ImageView leftButton = findViewById(R.id.LeftButton);
        ImageView upButton = findViewById(R.id.UpButton);
        ImageView downButton = findViewById(R.id.DownButton);
        ImageView rightButton = findViewById(R.id.RightButton);

        initializeButtons(leftButton, upButton, downButton, rightButton);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeButtons(ImageView left, ImageView up, ImageView down, ImageView right) {

        left.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameScreenModel.startMovement(0);
                //start movement
                break;
            case MotionEvent.ACTION_UP:
                gameScreenModel.stopMovement(0);
                //cancel movement
                break;
            default:
            }
            return true;
        });

        up.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameScreenModel.startMovement(1);
                //start movement
                break;
            case MotionEvent.ACTION_UP:
                gameScreenModel.stopMovement(1);
                //cancel movement
                break;
            default:
            }
            return true;
        });

        down.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameScreenModel.startMovement(2);
                //start movement
                break;
            case MotionEvent.ACTION_UP:
                gameScreenModel.stopMovement(2);
                //cancel movement
                break;
            default:
            }
            return true;
        });

        right.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                gameScreenModel.startMovement(3);
                //start movement
                break;
            case MotionEvent.ACTION_UP:
                gameScreenModel.stopMovement(3);
                //cancel movement
                break;
            default:
            }
            return true;
        });

    }



    private void initializePlayer(double screenWidth, double screenHeight) {
        player = PlayerClass.getPlayer();
        player.setGameScreenModel(gameScreenModel);
        player.setScreenWidth(screenWidth);
        player.setScreenHeight(screenHeight);
        player.setxPos(screenWidth / 2);
        player.setyPos(screenHeight - player.getSprite().getIntrinsicHeight());
    }

    private void initializePlayerView() {
        gameLayout = findViewById(R.id.gameLayout);
        playerView = new PlayerView(this);
        gameScreenModel.setPlayerView(playerView);
        player.setSpriteData(playerView);
        gameLayout.addView(playerView);
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
        return false;
    }

    public void endGame() {
        Score score = new Score(name, gameScreenModel.getScoreVal());
        Leaderboard leaderBoard = Leaderboard.getLeaderboard();
        leaderBoard.addScore(score);
        Intent intent = new Intent(GameScreen.this, EndScreen.class);
        intent.putExtra("score", score.getScore());
        startActivity(intent);
        GameScreen.this.finishAffinity();
    }


    // NEW CODE
    public void updateHealth(int healthPoints) {
        if (healthPoints == 0 && !player.isDead()) {
            player.setDead(true);
            healthValueDisplay.setText("KO");
            player.setyPos(0);
            player.setxPos(0);
            playerView.setVisibility(View.INVISIBLE);
            gameScreenModel.endGameDeath();
        } else {
            healthValueDisplay.setText(Integer.toString(healthPoints));
        }
    }

    public void updatePlayer() {
        playerView.updatePosition();
    }
}