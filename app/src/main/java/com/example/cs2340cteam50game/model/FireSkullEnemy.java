package com.example.cs2340cteam50game.model;



import android.graphics.drawable.Drawable;
import com.example.cs2340cteam50game.view.FireSkullView;

public class FireSkullEnemy implements Enemy {
    private Drawable sprite;
    private double xPos;
    private double yPos;
    private float spriteWidth;
    private float spriteHeight;

    private Rectangle hitBox;
    private double movementSpeed;
    private double healthPoints;
    private int damage = 10;

    public FireSkullEnemy() {
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
    public Drawable getSprite() {
        return sprite;
    }

    public void setSprite(Drawable x) {
        this.sprite = x;
    }

    public void setSpriteData(FireSkullView fsView) {
        this.spriteWidth = fsView.getSpriteWidth();
        this.spriteHeight = fsView.getSpriteHeight();

        // NEW CODE
        this.hitBox = new Rectangle((float) xPos, (float) yPos,
                (float) xPos + spriteWidth, (float) yPos + spriteHeight);
    }

    @Override
    public void setSpriteWidth(float width) {
        spriteWidth = width;
    }

    public float getSpriteWidth() {
        return spriteWidth;
    }

    @Override
    public void setSpriteHeight(float height) {
        spriteHeight = height;
    }

    public float getSpriteHeight() {
        return spriteHeight;
    }

    @Override
    public double getxPos() {
        return xPos;
    }

    @Override
    public double getyPos() {
        return yPos;
    }


    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getDamage() {
        return damage;
    }

    public void setxPos(double x) {
        xPos = x;
    }

    public void setyPos(double y) {
        yPos = y;
    }

    public void move(int stepX, int stepY) {
        xPos += stepX;
        yPos += stepY;
        hitBox.updatePosition(stepX, stepY);
    }
}
