package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.SpeedPowerup;


public class SpeedPowerupView extends View{
    private SpeedPowerup redbull;
    private Bitmap redbullSprite;
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

    public SpeedPowerupView(Context context, SpeedPowerup redbull) {
        super(context);

        this.redbull = redbull;
        int spriteID = R.drawable.redbull;
        Drawable sprite = getResources().getDrawable(spriteID);
        redbull.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) redbull.getSprite()).getBitmap();
        this.x = (float) redbull.getxPos();
        this.y = (float) redbull.getyPos();
        redbullSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = redbullSprite.getWidth();
        height = redbullSprite.getHeight();
        redbull.setSpriteWidth(width);
        redbull.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(redbullSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) redbull.getxPos();
        this.y = (float) redbull.getyPos();
        invalidate();
    }

}