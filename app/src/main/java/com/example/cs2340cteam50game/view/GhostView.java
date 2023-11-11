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


public class GhostView extends View implements EnemyView {
    private GhostEnemy ghostEnemy;
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

    public GhostView(Context context, GhostEnemy ghostEnemy) {
        super(context);
        this.ghostEnemy = ghostEnemy;
        int spriteID = R.drawable.ghost;
        Drawable sprite = getResources().getDrawable(spriteID);
        ghostEnemy.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) ghostEnemy.getSprite()).getBitmap();
        this.x = (float) ghostEnemy.getxPos();
        this.y = (float) ghostEnemy.getyPos();
        ghostSprite = Bitmap.createScaledBitmap(temp, (int) (3 * temp.getWidth()),
                (int) (3 * temp.getHeight()), true);
        width = ghostSprite.getWidth();
        height = ghostSprite.getHeight();
        ghostEnemy.setSpriteWidth(width);
        ghostEnemy.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(ghostSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) ghostEnemy.getxPos();
        this.y = (float) ghostEnemy.getyPos();
        invalidate();
    }


}