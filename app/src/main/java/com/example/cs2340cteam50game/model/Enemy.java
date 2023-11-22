package com.example.cs2340cteam50game.model;

import android.graphics.drawable.Drawable;

public interface Enemy {
    abstract Drawable getSprite();
    abstract void setSpriteWidth(float width);
    abstract void setSpriteHeight(float height);
    abstract double getxPos();
    abstract double getyPos();
    abstract Rectangle getHitBox();
    abstract int getDamage();
    abstract void move(int stepX, int stepY);

    void setHitBox(Rectangle rectangle);
}
