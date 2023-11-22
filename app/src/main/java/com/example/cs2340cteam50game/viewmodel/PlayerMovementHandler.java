package com.example.cs2340cteam50game.viewmodel;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerMovementHandler implements Runnable {

    private Timer movementClock;
    private TimerTask movementStyle;
    private Handler handler;
    private final GameScreenModel model;
    private int direction;

    public PlayerMovementHandler(int direction, GameScreenModel model, Handler handler) {
        this.model = model;
        this.handler = handler;
        this.direction = direction;
        setMovement();
    }

    private void setMovement() {
        switch (direction) {
        case 0:
            movementStyle = moveLeft();
            break;
        case 1:
            movementStyle = moveUp();
            break;
        case 2:
            movementStyle = moveDown();
            break;
        case 3:
            movementStyle = moveRight();
            break;
        default:
        }
    }


    @Override
    public void run() {
        movementClock = new Timer();
        movementClock.scheduleAtFixedRate(movementStyle, 0, 50);
    }

    public void stopMovement() {
        movementClock.cancel();
        setMovement();

    }

    private TimerTask moveLeft() {
        return new TimerTask() {
            @Override
            public void run() {
                handler.post(model::moveLeft);
            }
        };
    }

    private TimerTask moveUp() {
        return new TimerTask() {
            @Override
            public void run() {
                handler.post(model::moveUp);
            }
        };
    }

    private TimerTask moveDown() {
        return new TimerTask() {
            @Override
            public void run() {
                handler.post(model::moveDown);
            }
        };
    }

    private TimerTask moveRight() {
        return new TimerTask() {
            @Override
            public void run() {
                handler.post(model::moveRight);
            }
        };
    }
}
