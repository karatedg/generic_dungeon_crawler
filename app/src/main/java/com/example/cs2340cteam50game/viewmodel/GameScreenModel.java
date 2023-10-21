package com.example.cs2340cteam50game.viewmodel;

import android.graphics.RectF;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.view.PlayerView;

public class GameScreenModel {

    private final PlayerClass player = PlayerClass.getPlayer();
    private PlayerView playerView;
    private final RectF[] map1Walls = {
        new RectF(423, 256, 450, 695),
        new RectF(526, 860, 953, 920),
        new RectF(738, 79, 768, 435),
        new RectF(1055, 518, 1088, 693),
        new RectF(2000, 342, 2206, 410),
        new RectF(2000, 522, 2206, 592),
        new RectF(2156, 420, 2206, 510)
    };
    private final RectF[] map2Walls = {
        new RectF(106, 232, 536, 302),
        new RectF(106, 632, 426, 702),
        new RectF(606, 872, 636, 942),
        new RectF(816, 872, 846, 942),
        new RectF(736, 472, 1166, 542),
        new RectF(1376, 712, 1796, 782),
        new RectF(1586, 312, 2016, 382),
        new RectF(1556, 0, 1586, 80),
        new RectF(1766, 0, 1796, 80),
        new RectF(1590, 0, 1765, 20)
    };
    private final RectF[] map3Walls = {
        new RectF(0, 230, 210, 300),
        new RectF(0, 390, 210, 460),
        new RectF(420, 80, 850, 140),
        new RectF(420, 710, 850, 780),
        new RectF(820, 310, 850, 560),
        new RectF(1160, 550, 1590, 620),
        new RectF(1480, 240, 1900, 300),
        new RectF(1580, 790, 2010, 850),
        new RectF(2090, 390, 2120, 640),
        new RectF(1770, 0, 1800, 80),
        new RectF(1980, 0, 2010, 80),
        new RectF(1801, 0, 1979, 20),
    };

    private RectF[] currentWallSet;

    private int scoreVal = 50;
    public int getScoreVal() {
        return scoreVal;
    }
    public void setScoreVal(int score) {
        scoreVal = Math.max(score, 0);
    }

    public CountDownTimer startScoreTimer(TextView scoreDisplay) {
        CountDownTimer timer = new CountDownTimer((50) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                setScoreVal((int) (millisUntilFinished / 1000));
                scoreDisplay.setText("Score: " + scoreVal);
            }
            public void onFinish() {
                setScoreVal(0);
                scoreDisplay.setText("Score: " + scoreVal);
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
            return null;
        }
    }

    public void setScreen(int currentScreen, ImageView map) {
        switch (currentScreen) {
        case 0:
            map.setImageResource(R.drawable.newmap1);
            currentWallSet = map1Walls;
            break;
        case 1:
            map.setImageResource(R.drawable.newmap2);
            currentWallSet = map2Walls;
            break;
        case 2:
            map.setImageResource(R.drawable.newmap3);
            currentWallSet = map3Walls;
            break;
        default:
            map.setImageResource(R.drawable.newmap1);
            currentWallSet = map1Walls;
            break;
        }
    }

    //Callable movement methods
    public void moveLeft() {
        player.moveLeft();
    }
    public void moveUp() {
        player.moveUp();
    }
    public void moveRight() {
        player.moveRight();
    }
    public void moveDown() {
        player.moveDown();
    }

    //Collision Handler
    public boolean checkCollisions(double newX, double newY) {
        RectF playerHitBox = new RectF((float) newX, (float) newY,
                (float) newX + playerView.getSpriteWidth(),
                (float) newY + playerView.getSpriteHeight());
        for (int i = 0; i < currentWallSet.length - 2; i++) {
            if (playerHitBox.intersect(currentWallSet[i])) {
                return true;
            }
        }
        return false;
    }

    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }
}
