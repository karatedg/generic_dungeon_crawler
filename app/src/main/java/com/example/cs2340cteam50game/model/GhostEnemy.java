package com.example.cs2340cteam50game.model;



import android.graphics.drawable.Drawable;
import com.example.cs2340cteam50game.view.GhostView;

public class GhostEnemy implements Enemy{
    private static Drawable sprite;
    private double xPos;
    private double yPos;
    private float spriteWidth = 64.0F;
    private float spriteHeight = 64.0F;
    private double movementSpeed;
    private double healthPoints;
    private int damage = 25;

    public GhostEnemy() {
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.movementSpeed = 10;
        this.healthPoints = 10;
        this.sprite = null;
    }
    @Override
    public void movementCycle() {
        // Some movement pattern for Skull
    }

    @Override
    public void die() {
        // when player attacks and enemy health == 0
    }

    @Override
    public void onHit() {
        //For skull when player collides, swap players left and right, and up and down
    }

    @Override
    public Drawable getSprite() {return sprite;}

    public static void setSprite(Drawable x) {sprite = x;}

    public void setSpriteData(GhostView ghostView) {
        this.spriteWidth = ghostView.getSpriteWidth();
        this.spriteHeight = ghostView.getSpriteHeight();
    }

    @Override
    public void setSpriteWidth(float width) {
        spriteWidth = width;
    }

    public float getSpriteWidth() {return spriteWidth;}

    @Override
    public void setSpriteHeight(float height) {
        spriteHeight = height;
    }

    public float getSpriteHeight() {return spriteHeight;}

    @Override
    public double getxPos() {
        return xPos;
    }

    @Override
    public double getyPos() {
        return yPos;
    }

    public void setxPos(double x) {xPos = x;}

    public void setyPos(double y) {yPos = y;}
}
