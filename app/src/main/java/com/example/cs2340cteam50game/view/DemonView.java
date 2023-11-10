package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.DemonCreator;
import com.example.cs2340cteam50game.model.DemonEnemy;


public class DemonView extends View {
    private DemonEnemy enemy;
    private Bitmap demonSprite;

    private float width;
    private float height;

    public float getSpriteWidth() {
        return width;
    }
    public float getSpriteHeight() {
        return height;
    }
    private float x;
    private float y;

    private DemonCreator demonCreator = new DemonCreator();

    public DemonView(Context context, DemonEnemy enemy) {
        super(context);
        this.enemy = enemy;
        int spriteID = R.drawable.demon;
        Drawable sprite = getResources().getDrawable(spriteID);
        enemy.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) enemy.getSprite()).getBitmap();
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        demonSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = demonSprite.getWidth();
        height = demonSprite.getHeight();
        enemy.setSpriteWidth(width);
        enemy.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(demonSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        invalidate();
    }


}