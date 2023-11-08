package com.example.cs2340cteam50game.viewmodel;

import com.example.cs2340cteam50game.model.Rectangle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.NoSpeed;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.view.FireSkullView;
import com.example.cs2340cteam50game.view.GameScreen;
import com.example.cs2340cteam50game.view.PlayerView;

public class GameScreenModel {

    private final PlayerClass player = PlayerClass.getPlayer();
    private PlayerView playerView;
    private FireSkullView fireSkullView;
    private ImageView map;
    private GameScreen game;

    private CountDownTimer timer;

    private int currentRoom = 0;

    private final Rectangle[] map1Walls = {
        new Rectangle(423, 256, 450, 695),
        new Rectangle(526, 865, 953, 950),
        new Rectangle(738, 79, 768, 435),
        new Rectangle(1055, 168, 1088, 425),
        new Rectangle(1055, 518, 1088, 693),
        new Rectangle(2000, 342, 2220, 390),
        new Rectangle(2000, 522, 2220, 565),
        new Rectangle(2170, 400, 2220, 510)
    };
    private final Rectangle[] map2Walls = {
        new Rectangle(106, 232, 536, 272),
        new Rectangle(106, 632, 426, 672),
        new Rectangle(606, 872, 636, 942),
        new Rectangle(816, 872, 846, 942),
        new Rectangle(736, 472, 1166, 512),
        new Rectangle(1376, 712, 1796, 752),
        new Rectangle(1586, 312, 2016, 352),
        new Rectangle(1556, 0, 1586, 80),
        new Rectangle(1766, 0, 1796, 80),
        new Rectangle(1590, 0, 1765, 20)
    };
    private final Rectangle[] map3Walls = {
        new Rectangle(0, 230, 210, 280),
        new Rectangle(0, 390, 210, 440),
        new Rectangle(420, 80, 850, 120),
        new Rectangle(420, 710, 850, 750),
        new Rectangle(820, 310, 850, 560),
        new Rectangle(1160, 550, 1590, 590),
        new Rectangle(1480, 240, 1900, 280),
        new Rectangle(1580, 790, 2010, 830),
        new Rectangle(2090, 390, 2120, 640),
        new Rectangle(1770, 0, 1800, 80),
        new Rectangle(1980, 0, 2010, 80),
        new Rectangle(1801, 0, 1979, 20),
    };
    private Rectangle[] currentWallSet = map1Walls;

    private Rectangle exitBox = currentWallSet[currentWallSet.length - 1];
    private int scoreVal = 100;

    public void startScoreTimer(TextView scoreDisplay) {
        timer = new CountDownTimer((scoreVal) * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                setScoreVal((int) (millisUntilFinished / 1000));
                scoreDisplay.setText("Score: " + scoreVal);
            }
            public void onFinish() {
                setScoreVal(0);
                scoreDisplay.setText("Score: " + scoreVal);
            }
        }.start();
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
    public int checkCollisions(double newX, double newY, int direction) {

        Rectangle playerHitBox = new Rectangle((float) newX, (float) newY,
                (float) newX + player.getSpriteWidth(),
                (float) newY + player.getSpriteHeight());

        if (playerHitBox.intersects(exitBox)) {
            return 2;
        }

        for (int i = 0; i < currentWallSet.length - 1; i++) {
            if (playerHitBox.intersectsWall(currentWallSet[i], direction)) {
                player.setxPos(playerHitBox.getLeft());
                player.setyPos(playerHitBox.getTop());
                return 1;
            }
        }
        return 0;
    }
    //Passes on the nextRoom call to the GameScreen which handles it
    public void nextRoom() {
        if (currentRoom == 2) {
            player.setMovementStrategy(new NoSpeed());
            timer.cancel();
            game.endGame();
        } else {
            currentRoom++;
            setScreen(currentRoom);
            switch (currentRoom) {
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

    //Updates the current game screen - handling the new image, screenNum, and currentWallSet
    public void setScreen(int currentScreen) {
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
        exitBox = currentWallSet[currentWallSet.length - 1];
    }

    //Sets the currently used GameScreen
    public void setGameScreen(GameScreen game) {
        this.game = game;
    }

    //Sets the currentMapView
    public void setMap(ImageView map) {
        this.map = map;
    }

    //Setter for score
    public void setScoreVal(int score) {
        scoreVal = Math.max(score, 0);
    }

    //Getter for score
    public int getScoreVal() {
        return scoreVal;
    }

    //Getter for currentScreen
    public int getCurrentRoom() {
        return currentRoom;
    }

    //Setter for playerView
    public void setPlayerView(PlayerView playerView) {
        this.playerView = playerView;
    }

    public void setFireSkullView(FireSkullView fsView) {
        this.fireSkullView = fsView;
    }

    public void setCurrentWallSet(int wallSet) {
        switch (wallSet) {
        case 0:
            currentWallSet = map1Walls;
            currentRoom = 0;
            break;
        case 1:
            currentWallSet = map2Walls;
            currentRoom = 1;
            break;
        case 2:
            currentWallSet = map3Walls;
            currentRoom = 2;
            break;
        default:
            System.out.println("DEFAULT SET WALL SET");
        }
        exitBox = currentWallSet[currentWallSet.length - 1];
    }


}


