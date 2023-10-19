package com.example.cs2340cteam50game.viewmodel;

import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.DefaultSpeed;
import com.example.cs2340cteam50game.model.MovementStrategy;
import com.example.cs2340cteam50game.model.PlayerClass;

public class GameScreenModel extends AppCompatActivity {

    private static TextView scoreText;

    public static void setScoreText(TextView score) {
        scoreText = score;
    }
    private static int scoreVal = 50;

    public static int getScoreVal() {
        return scoreVal;
    }

    public static void setScoreVal(int score) {
        if (score < 0) {
            scoreVal = 0;
        } else {
            scoreVal = score;
        }
    }

    public static void updateScoreDisplay() {
        scoreText.setText("Score: " + scoreVal);
    }

    public static CountDownTimer startTimer() {
        CountDownTimer timer = new CountDownTimer((50) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                setScoreVal((int) (millisUntilFinished / 1000));
                updateScoreDisplay();
            }
            public void onFinish() {
                setScoreVal(0);
                updateScoreDisplay();
            }
        }.start();

        return timer;
    }


    public static String difficultySwitch(int difficulty) {
        switch (difficulty) {
        case 1:
            return "Easy";
        case 2:
            return "Medium";
        case 3:
            return "Hard";
        default:
            return "Easy";
        }
    }

    public static int setScreen(int currentScreen, ImageView map) {
        switch (currentScreen) {
        case 0:
            map.setImageResource(R.drawable.map1);
            return 0;
        case 1:
            map.setImageResource(R.drawable.map2);
            return 1;
        case 2:
            map.setImageResource(R.drawable.map3);
            return 2;
        default:
            map.setImageResource(R.drawable.map1);
            return 0;
        }
    }

    private static MovementStrategy movementStrategy = new DefaultSpeed();
    public static void setMovementStrategy(MovementStrategy movementStrategy) {
        GameScreenModel.movementStrategy = movementStrategy;
    }

    //Callable movement methods
    public static void moveLeft() {
        movementStrategy.moveLeft();
    }
    public static void moveRight() {
        movementStrategy.moveRight();
    }
    public static void moveDown() {
        movementStrategy.moveDown();
    }
    public static void moveUp() {
        movementStrategy.moveUp();
    }

}
