package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.HealthPowerup;


public class HealthPowerupView extends View{
    private HealthPowerup medkit;
    private Bitmap medkitSprite;
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

    public HealthPowerupView(Context context, HealthPowerup medkit) {
        super(context);

        this.medkit = medkit;
        int spriteID = R.drawable.medkit;
        Drawable sprite = getResources().getDrawable(spriteID);
        medkit.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) medkit.getSprite()).getBitmap();
        this.x = (float) medkit.getxPos();
        this.y = (float) medkit.getyPos();
        medkitSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = medkitSprite.getWidth();
        height = medkitSprite.getHeight();
        medkit.setSpriteWidth(width);
        medkit.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(medkitSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) medkit.getxPos();
        this.y = (float) medkit.getyPos();
        invalidate();
    }

}