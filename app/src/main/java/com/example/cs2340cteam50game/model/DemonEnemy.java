package com.example.cs2340cteam50game.model;



import android.graphics.drawable.Drawable;
import com.example.cs2340cteam50game.view.DemonView;

public class DemonEnemy implements Enemy {
    private Drawable sprite;
    private volatile double xPos;
    private volatile double yPos;
    private float spriteWidth = 64.0F;
    private float spriteHeight = 64.0F;
    private double movementSpeed;
    private double healthPoints;
    private int damage = 25;
    private volatile Rectangle hitBox;

    public DemonEnemy() {
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.movementSpeed = 10;
        this.healthPoints = 10;
        this.sprite = null;
    }

    @Override
    public Drawable getSprite() {
        return sprite;
    }

    public void setSprite(Drawable x) {
        sprite = x;
    }

    public void setSpriteData(DemonView demonView) {
        this.spriteWidth = demonView.getSpriteWidth();
        this.spriteHeight = demonView.getSpriteHeight();

        // NEW CODE
        this.hitBox = new Rectangle((float) xPos, (float) yPos,
                (float) xPos + spriteWidth - 60, (float) yPos + spriteHeight);
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

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
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
