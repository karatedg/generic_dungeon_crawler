package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.BeastEnemy;


public class BeastView extends View implements EnemyView {
    private BeastEnemy beastEnemy;
    private Bitmap beastSprite;

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

    public BeastView(Context context, BeastEnemy beastEnemy) {
        super(context);
        this.beastEnemy = beastEnemy;
        int spriteID = R.drawable.beast;
        Drawable sprite = getResources().getDrawable(spriteID);
        beastEnemy.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) beastEnemy.getSprite()).getBitmap();
        this.x = (float) beastEnemy.getxPos();
        this.y = (float) beastEnemy.getyPos();
        beastSprite = Bitmap.createScaledBitmap(temp, (int) (2.5 * temp.getWidth()),
                (int) (2.5 * temp.getHeight()), true);
        width = beastSprite.getWidth();
        height = beastSprite.getHeight();
        beastEnemy.setSpriteWidth(width);
        beastEnemy.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(beastSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) beastEnemy.getxPos();
        this.y = (float) beastEnemy.getyPos();
        invalidate();
    }


}