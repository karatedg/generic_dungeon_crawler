package com.example.cs2340cteam50game.model;

import android.graphics.drawable.Drawable;

public interface Weapon {
    abstract Drawable getSprite();
    abstract void setSpriteWidth(float width);
    abstract void setSpriteHeight(float height);
    abstract double getxPos();
    abstract double getyPos();
    abstract Rectangle getHitBox();
}
