package com.example.cs2340cteam50game.model;

import android.graphics.drawable.Drawable;

import com.example.cs2340cteam50game.view.HealthPowerupView;

public class HealthPowerup implements Powerup {

    private Drawable sprite;
    private volatile Rectangle hitBox;
    private Float width;
    private Float height;
    private double x;
    private double y;

    public HealthPowerup() {
        this.sprite = null;
        this.x = 0.0;
        this.y = 0.0;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setSpriteData(HealthPowerupView healthPowerupView) {
        this.width = healthPowerupView.getSpriteWidth();
        this.height = healthPowerupView.getSpriteHeight();

        // NEW CODE
        this.hitBox = new Rectangle((float) x, (float) y,
                (float) x + width, (float) y + height);
    }

    public void setSprite(Drawable sprite) {
        this.sprite = sprite;
    }

    public Drawable getSprite() {
        return sprite;
    }

    public void setSpriteWidth(Float width) {
        this.width = width;
    }

    public Float getWidth() {
        return width;
    }

    public Float getHeight() {
        return height;
    }

    public void setSpriteHeight(Float height) {
        this.height = height;
    }

    public void setyPos(double y) {
        this.y = y;
    }

    public double getyPos() {
        return y;
    }

    public void setxPos(double x) {
        this.x = x;
    }

    public double getxPos() {
        return x;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

}