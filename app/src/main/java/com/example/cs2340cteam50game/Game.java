package com.example.cs2340cteam50game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.cs2340cteam50game.graphics.SpriteSheet;
import com.example.cs2340cteam50game.map.Tilemap;

class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Tilemap tilemap;
    private GameDisplay gameDisplay;
    private GameLoop loop;
    private PlayerClass player;

    private String currentMapFilename;

    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        loop = new GameLoop(this, surfaceHolder);


        // Initialize game objects
        SpriteSheet spriteSheet = new SpriteSheet(context);
        player = PlayerClass.getPlayer();

//        // Initialize display and center it around the player
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        gameDisplay = new GameDisplay(displayMetrics.widthPixels,
//                displayMetrics.heightPixels, player);

        // Initialize Tilemap
        tilemap = new Tilemap(spriteSheet); //currentMapFilename

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (loop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            loop = new GameLoop(this, surfaceHolder);
        }
        loop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int four, int height, int width) {
        return;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        return;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //Draw the Tilemap
        tilemap.draw(canvas, gameDisplay);

        //TODO: Draw the player
        //player.draw(canvas, gameDisplay);

        // TODO: We can put game over here
    }

    public void update() {
        //TODO: we can probably stop updating this if the player dies
        //if (player is dead) {
            //return; //to break method
        //}

        //TODO: update player location
        //player.update();


        //TODO: We can check for collision now, since we know the player's location

        //TODO: Update the gameDisplay so that it is based on the player's coordinates
        gameDisplay.update();
    }

    //pause/stop loop if neccesary
    public void pause() {
        System.out.println("pause called");
        loop.stopLoop();
    }
}
