package com.example.cs2340cteam50game.viewmodel;

import com.example.cs2340cteam50game.model.Rectangle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.NoSpeed;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.view.GameScreen;
import com.example.cs2340cteam50game.view.PlayerView;

public class GameScreenModel {

    private final PlayerClass player = PlayerClass.getPlayer();
    private PlayerView playerView;
    private ImageView map;
    private GameScreen game;

    private CountDownTimer timer;

    private int currentRoom = 0;

    private final Rectangle[] map1Walls = {
        new Rectangle(423, 256, 450, 695),
        new Rectangle(526, 870, 953, 950),
        new Rectangle(738, 79, 768, 435),
        new Rectangle(1055, 168, 1088, 425),
        new Rectangle(1055, 518, 1088, 693),
        new Rectangle(2000, 342, 2220, 410),
        new Rectangle(2000, 522, 2220, 592),
        new Rectangle(2156, 420, 2220, 510)
    };
    private final Rectangle[] map2Walls = {
        new Rectangle(106, 232, 536, 302),
        new Rectangle(106, 632, 426, 702),
        new Rectangle(606, 872, 636, 942),
        new Rectangle(816, 872, 846, 942),
        new Rectangle(736, 472, 1166, 542),
        new Rectangle(1376, 712, 1796, 782),
        new Rectangle(1586, 312, 2016, 382),
        new Rectangle(1556, 0, 1586, 80),
        new Rectangle(1766, 0, 1796, 80),
        new Rectangle(1590, 0, 1765, 20)
    };
    private final Rectangle[] map3Walls = {
        new Rectangle(0, 230, 210, 300),
        new Rectangle(0, 390, 210, 460),
        new Rectangle(420, 80, 850, 140),
        new Rectangle(420, 710, 850, 780),
        new Rectangle(820, 310, 850, 560),
        new Rectangle(1160, 550, 1590, 620),
        new Rectangle(1480, 240, 1900, 300),
        new Rectangle(1580, 790, 2010, 850),
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
    public int checkCollisions(double newX, double newY) {

        Rectangle playerHitBox = new Rectangle((float) newX, (float) newY,
                (float) newX + player.getSpriteWidth(),
                (float) newY + player.getSpriteHeight());

        if (checkMoveRooms(playerHitBox)) {
            return 2;
        }
        for (int i = 0; i < currentWallSet.length - 1; i++) {

            float eLeft = currentWallSet[i].left;
            float eTop = currentWallSet[i].top;
            float eRight = currentWallSet[i].right;
            float eBottom = currentWallSet[i].bottom;

            float pLeft = playerHitBox.left;
            float pTop = playerHitBox.top;
            float pRight = playerHitBox.right;
            float pBottom = playerHitBox.bottom;

            boolean horizontalCollision = ((eLeft <=  pLeft && pLeft <= eRight)
                    || (eLeft <=  pRight && pRight <= eRight));

            boolean verticalCollision = ((eTop <= pTop && pTop <= eBottom)
                    || (eTop <=  pBottom && pBottom <= eBottom));

            if ((horizontalCollision && verticalCollision)) {
                return 1;
            }
        }
        return 0;
    }

    //Specialized collision handler for changing rooms
    public boolean checkMoveRooms(Rectangle playerHitBox) {
        float eLeft = exitBox.left;
        float eTop = exitBox.top;
        float eRight = exitBox.right;
        float eBottom = exitBox.bottom;

        float pLeft = playerHitBox.left;
        float pTop = playerHitBox.top;
        float pRight = playerHitBox.right;
        float pBottom = playerHitBox.bottom;

        boolean horizontalCollision = ((eLeft <=  pLeft && pLeft <= eRight)
                || (eLeft <=  pRight && pRight <= eRight));

        boolean verticalCollision = ((eTop <= pTop && pTop <= eBottom)
                || (eTop <=  pBottom && pBottom <= eBottom));

        return (horizontalCollision && verticalCollision);
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


