package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.GhostCreator;
import com.example.cs2340cteam50game.model.GhostEnemy;


public class GhostView extends View {
    private GhostEnemy enemy;
    private Bitmap ghostSprite;

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

    private GhostCreator ghostCreator = new GhostCreator();

    public GhostView(Context context) {
        super(context);
        enemy = (GhostEnemy) ghostCreator.createEnemy();
        int spriteID = R.drawable.ghost;
        Drawable sprite = getResources().getDrawable(spriteID);
        enemy.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) enemy.getSprite()).getBitmap();
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        ghostSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = ghostSprite.getWidth();
        height = ghostSprite.getHeight();
        enemy.setSpriteWidth(width);
        enemy.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(ghostSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        invalidate();
    }


}