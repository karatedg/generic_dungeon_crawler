package com.example.cs2340cteam50game.viewmodel;

import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.view.GameScreen;

public class GameScreenModel {

    static TextView scoreText;

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

    public static void spriteSet(int spriteNum, ImageView spriteImage) {
        switch (spriteNum) {
        case 1:
            spriteImage.setImageResource(R.drawable.red_idle);
            break;
        case 2:
            spriteImage.setImageResource(R.drawable.blue_idle);
            break;
        case 3:
            spriteImage.setImageResource(R.drawable.green_idle);
            break;
        default:
            break;
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

}
