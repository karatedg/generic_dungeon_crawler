package com.example.cs2340cteam50game.viewmodel;


import android.os.Handler;

import com.example.cs2340cteam50game.model.Enemy;
import com.example.cs2340cteam50game.model.PlayerClass;
import com.example.cs2340cteam50game.model.Rectangle;
import com.example.cs2340cteam50game.view.EnemyView;
import com.example.cs2340cteam50game.view.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class EnemyMovementHandler implements Runnable {

    private final Enemy enemy;
    private final EnemyView enemyView;
    private Timer movementClock;
    private TimerTask movementStyle;
    private final PlayerClass player;
    private final GameScreen gameScreen;

    private Handler handler;

    public EnemyMovementHandler(Enemy enemy, EnemyView enemyView,
                                PlayerClass player, GameScreen gameScreen,
                                int direction, Handler handler) {
        this.enemy = enemy;
        this.enemyView = enemyView;
        this.player = player;
        this.gameScreen = gameScreen;
        this.handler = handler;
        switch (direction) {
        case 0:
            movementStyle = moveHorizontal();
            break;
        case 1:
            movementStyle = moveVertical();
            break;
        case 2:
            movementStyle = moveCircular();
            break;
        case 3:
            movementStyle = moveDiagonal();
            break;
        default:
        }
    }

    @Override
    public void run() {
        movementClock = new Timer();
        movementClock.scheduleAtFixedRate(movementStyle, 0, 200);
    }



    public TimerTask moveHorizontal() {

        TimerTask moveHorizontal = new TimerTask() {
            private int stepsLeft = 0;
            private int stepsRight = 30;

            @Override
            public void run() {
                if (stepsRight < 60) {
                    enemy.move(5, 0);
                    stepsRight++;
                    if (stepsRight == 60) {
                        stepsLeft = 0;
                    }
                } else if (stepsLeft < 60) {
                    enemy.move(-5, 0);
                    stepsLeft++;
                    if (stepsLeft == 60) {
                        stepsRight = 0;
                    }
                }
                checkPlayerCollision(0);
                handler.post(enemyView::updatePosition);
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
                handler.post(enemyView::updatePosition);
            }
        };
        return moveVertical;
    }



    private TimerTask moveCircular() {
        TimerTask moveCircular = new TimerTask() {
            private double angle = 30;
            private double radius = 10;

            @Override
            public void run() {
                double x = radius * Math.cos(Math.toRadians(angle));
                double y = radius * Math.sin(Math.toRadians(angle));

                enemy.move((int) x, (int) y);

                angle += 10;

                checkPlayerCollision(0);
                checkPlayerCollision(1);
                handler.post(enemyView::updatePosition);
            }
        };
        return moveCircular;
    }

    private TimerTask moveDiagonal() {
        TimerTask moveDiagonal = new TimerTask() {
            private int xDirection = 1;
            private int yDirection = 1;
            private int xStep = 5;
            private int yStep = 5;
            private int maxX = 100;
            private int maxY = 100;
            private int minX = 0;
            private int minY = 0;

            private int currentX = minX;
            private int currentY = minY;

            @Override
            public void run() {
                enemy.move(xStep * xDirection, yStep * yDirection);

                currentX += xStep * xDirection;
                currentY += yStep * yDirection;

                if (currentX >= maxX || currentX <= minX) {
                    xDirection *= -1; // Change x direction when reaching boundaries
                }

                if (currentY >= maxY || currentY <= minY) {
                    yDirection *= -1; // Change y direction when reaching boundaries
                }

                checkPlayerCollision(0);
                checkPlayerCollision(1);
                handler.post(enemyView::updatePosition);
            }
        };
        return moveDiagonal;
    }



    // Collision Handler for Enemy With Player
    public void checkPlayerCollision(int direction) {
        double xPos = player.getxPos();
        double yPos = player.getyPos();
        Rectangle playerHitBox = new Rectangle((float) xPos, (float) yPos,
                (float) xPos + player.getSpriteWidth(),
                (float) yPos + player.getSpriteHeight());

        if (playerHitBox.intersectsEnemy(enemy.getHitBox(), direction)) {
            player.setxPos(playerHitBox.getLeft());
            player.setyPos(playerHitBox.getTop());

            handler.post(player::takeDamage);
            handler.post(gameScreen::updateScoreDisplay);
            handler.post(gameScreen::updatePlayer);
            handler.post(() -> gameScreen.updateHealth(player.getHealthPoints()));
        }
    }

    public void stopMovement() {
        if (!(movementClock == null)) {
            movementClock.cancel();
            movementClock.purge();
            movementClock = null;
            movementStyle = null;
        }
    }


}
