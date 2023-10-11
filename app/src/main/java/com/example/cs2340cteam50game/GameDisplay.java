package com.example.cs2340cteam50game;

import android.graphics.Rect;

import com.example.cs2340cteam50game.GameObjects.GameScreenObject;

public class GameDisplay {
    public final Rect THE_SCREEN_RECT;
    private final int pixelWidth;
    private final int pixelHeight;
    private final GameScreenObject centerOfScreenObject;
    private final double centerOfScreenX;
    private final double centerOfScreenY;
    private double screenOffsetX;
    private double screenOffsetY;
    private double gameCenteredAtX;
    private double gameCenteredAtY;

    public GameDisplay(int widthPixels, int heightPixels, GameScreenObject centerObject) {

        this.pixelWidth = widthPixels;
        this.pixelHeight = heightPixels;
        THE_SCREEN_RECT = new Rect(0, 0, widthPixels, heightPixels);
        this.centerOfScreenObject = centerObject;
        centerOfScreenX = widthPixels / 2.0;
        centerOfScreenY = heightPixels / 2.0;

        update();
    }

    //update screen position
    public void update() {
        gameCenteredAtX = centerOfScreenObject.getPosX();
        gameCenteredAtY = centerOfScreenObject.getPosY();
        screenOffsetX = centerOfScreenX - gameCenteredAtX;
        screenOffsetY = centerOfScreenY - gameCenteredAtY;
    }

    //
    public double gameScreenCoordsX(double x) {
        return x + screenOffsetX;
    }

    public double gameScreenCoordsY(double y) {
        return y + screenOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenteredAtX - pixelWidth / 2),
                (int) (gameCenteredAtY - pixelHeight / 2),
                (int) (gameCenteredAtX + pixelWidth / 2),
                (int) (gameCenteredAtY + pixelHeight / 2)
        );
    }
}
