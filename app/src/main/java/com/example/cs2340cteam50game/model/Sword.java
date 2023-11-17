package com.example.cs2340cteam50game.model;

import android.graphics.drawable.Drawable;

import com.example.cs2340cteam50game.view.FireSkullView;
import com.example.cs2340cteam50game.view.SwordView;

public class Sword implements Weapon {
    private Drawable sprite;
    private volatile double xPos;
    private volatile double yPos;
    private float spriteWidth = 64.0F;
    private float spriteHeight = 64.0F;
    private volatile Rectangle hitBox;
    private static volatile Sword swordInstance;

    public Sword() {
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.sprite = null;
    }

    public static Sword getSword() {
        if (swordInstance == null) {
            synchronized (Sword.class) {
                if (swordInstance == null) {
                    swordInstance = new Sword();
                }
            }
        }
        return swordInstance;
    }
    @Override
    public Drawable getSprite() {
        return sprite;
    }

    public void setSprite(Drawable x) {
        this.sprite = x;
    }

    public void setSpriteData(SwordView swordView) {
        this.spriteWidth = swordView.getSpriteWidth();
        this.spriteHeight = swordView.getSpriteHeight();

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

    public void setxPos(double x) {
        xPos = x;
    }

    public void setyPos(double y) {
        yPos = y;
    }

}
