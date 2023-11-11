package com.example.cs2340cteam50game.viewmodel;


import com.example.cs2340cteam50game.model.Enemy;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.model.Rectangle;
import com.example.cs2340cteam50game.view.EnemyView;
import com.example.cs2340cteam50game.view.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class EnemyMovementHandler {

    private final Enemy enemy;
    private final EnemyView enemyView;
    private final Timer movementClock;
    private TimerTask movementStyle;
    private final PlayerClass player;
    private final GameScreen gameScreen;

    public EnemyMovementHandler(Enemy enemy, EnemyView enemyView,
                                PlayerClass player, GameScreen gameScreen, int direction) {
        this.enemy = enemy;
        this.enemyView = enemyView;
        this.player = player;
        this.gameScreen = gameScreen;
        switch (direction) {
        case 0:
            movementStyle = moveHorizontal();
            break;
        case 1:
            movementStyle = moveVertical();
            break;
        default:

        }
        movementClock = new Timer();
        movementClock.scheduleAtFixedRate(movementStyle, 0, 150);
    }

    private TimerTask moveHorizontal() {

        TimerTask moveHorizontal = new TimerTask() {
            private int stepsLeft = 0;
            private int stepsRight = 60;

            @Override
            public void run() {
                if (stepsRight < 100) {
                    enemy.move(5, 0);
                    stepsRight++;
                    if (stepsRight == 100) {
                        stepsLeft = 0;
                    }
                } else if (stepsLeft < 100) {
                    enemy.move(-5, 0);
                    stepsLeft++;
                    if (stepsLeft == 100) {
                        stepsRight = 0;
                    }
                }
                checkPlayerCollision(0);
                enemyView.updatePosition();
            }
        };

        return moveHorizontal;
    }

    private TimerTask moveVertical() {

        TimerTask moveVertical = new TimerTask() {
            private int stepsUp = 0;
            private int stepsDown = 30;

            @Override
            public void run() {
                if (stepsDown < 50) {
                    enemy.move(0, 5);
                    stepsDown++;
                    if (stepsDown == 50) {
                        stepsUp = 0;
                    }
                } else if (stepsUp < 50) {
                    enemy.move(0, -5);
                    stepsUp++;
                    if (stepsUp == 50) {
                        stepsDown = 0;
                    }
                }
                checkPlayerCollision(1);
                enemyView.updatePosition();
            }
        };
        return moveVertical;
    }


    // Collision Handler for Enemy With Player
    public void checkPlayerCollision(int direction) {
        double xPos = player.getxPos();
        double yPos = player.getyPos();
        Rectangle playerHitBox = new Rectangle((float) xPos, (float) yPos,
                (float) xPos + player.getSpriteWidth(),
                (float) yPos + player.getSpriteHeight());

        if (playerHitBox.intersectsEnemy(enemy.getHitBox(), direction)) {
            player.takeDamage(enemy.getDamage());
            player.setxPos(playerHitBox.getLeft());
            player.setyPos(playerHitBox.getTop());
            gameScreen.updatePlayer();
            gameScreen.updateHealth(player.getHealthPoints());
        }
    }

    public void stopMovement() {
        movementClock.cancel();
    }

}
