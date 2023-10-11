package com.example.cs2340cteam50game.GameObjects;


import android.graphics.Canvas;

import com.example.cs2340cteam50game.GameDisplay;

/**
 * This is mostly used to just track the display and update it right now. It could be used later
 * in the project for other uses though
 */
public abstract class GameScreenObject {
    protected double posX = 0;
    protected double posY = 0;

    public GameScreenObject() { }

    public GameScreenObject(double positionX, double positionY) {
        this.posX = positionX;
        this.posY = positionY;
    }

    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }

    //because everything needs draw
    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);
    public abstract void update(); //force an update class into things that use this so we have it
}
