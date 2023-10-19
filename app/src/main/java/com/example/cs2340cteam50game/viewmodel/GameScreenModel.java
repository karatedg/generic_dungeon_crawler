package com.example.cs2340cteam50game.viewmodel;

import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.DefaultSpeed;
import com.example.cs2340cteam50game.model.MovementStrategy;

public class GameScreenModel {

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

    public void updateScoreDisplay(TextView scoreText) {
        scoreText.setText("Score: " + scoreVal);
    }

    public CountDownTimer startTimer(TextView scoreDisplay) {
        CountDownTimer timer = new CountDownTimer((50) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                setScoreVal((int) (millisUntilFinished / 1000));
                updateScoreDisplay(scoreDisplay);
            }
            public void onFinish() {
                setScoreVal(0);
                updateScoreDisplay(scoreDisplay);
            }
        }.start();

        return timer;
    }


    public String difficultySwitch(int difficulty) {
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

    public int setScreen(int currentScreen, ImageView map) {
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

    private MovementStrategy movementStrategy = new DefaultSpeed();
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    //Callable movement methods
    public void moveLeft() {
        movementStrategy.moveLeft();
    }
    public void moveRight() {
        movementStrategy.moveRight();
    }
    public void moveDown() {
        movementStrategy.moveDown();
    }
    public void moveUp() {
        movementStrategy.moveUp();
    }

}
