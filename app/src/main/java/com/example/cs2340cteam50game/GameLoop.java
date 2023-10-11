package com.example.cs2340cteam50game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private Game game;
    private SurfaceHolder surfaceHolder;

    private boolean isRunning = false;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }
    @Override
    public void run() {
        super.run();

        // Game loop
        Canvas canvas = null;
        while (isRunning) {

            // Try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace(); //If the game can't update it does this
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void stopLoop() {
        isRunning = false;
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
