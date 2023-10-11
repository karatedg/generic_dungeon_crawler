package com.example.cs2340cteam50game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.cs2340cteam50game.graphics.SpriteSheet;
import com.example.cs2340cteam50game.map.Tilemap;

class Game extends SurfaceView{

    private final Tilemap tilemap;
    private GameDisplay gameDisplay;
    private GameLoop loop;

    private String currentMapFilename;

    public Game(Context context) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();

        loop = new GameLoop(this, surfaceHolder);


        // Initialize game objects
        SpriteSheet spriteSheet = new SpriteSheet(context);
        //TODO: Player setup here

        // Initialize display and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels,
                displayMetrics.heightPixels, player);

        // Initialize Tilemap
        tilemap = new Tilemap(currentMapFilename, spriteSheet);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (loop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            loop = new GameLoop(this, surfaceHolder);
        }
        loop.startLoop();
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
        loop.stopLoop();
    }
}
