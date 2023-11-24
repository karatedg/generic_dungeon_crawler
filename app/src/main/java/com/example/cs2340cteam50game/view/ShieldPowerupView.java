package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.ShieldPowerup;


public class ShieldPowerupView extends View{
    private ShieldPowerup shield;
    private Drawable playerShield;
    private Bitmap shieldSprite;
    private float width;
    private float height;

    public float getSpriteWidth() {
        return width;
    }
    public float getSpriteHeight() {
        return height;
    }
    private volatile float x;
    private volatile float y;

    public ShieldPowerupView(Context context, ShieldPowerup shield) {
        super(context);

        this.shield = shield;
        int spriteID = R.drawable.shield_powerup;
        Drawable sprite = getResources().getDrawable(spriteID);
        shield.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) shield.getSprite()).getBitmap();
        this.x = (float) shield.getxPos();
        this.y = (float) shield.getyPos();
        shieldSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = shieldSprite.getWidth();
        height = shieldSprite.getHeight();
        shield.setSpriteWidth(width);
        shield.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(shieldSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) shield.getxPos();
        this.y = (float) shield.getyPos();
        invalidate();
    }

}