package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.FireSkullCreator;
import com.example.cs2340cteam50game.model.FireSkullEnemy;


public class FireSkullView extends View {
    private FireSkullEnemy enemy;
    private Bitmap fireSkullSprite;

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

    private FireSkullCreator fsCreator = new FireSkullCreator();

    public FireSkullView(Context context) {
        super(context);
        enemy = (FireSkullEnemy) fsCreator.createEnemy();
        int spriteID = R.drawable.fireskull;
        Drawable sprite = getResources().getDrawable(spriteID);
        enemy.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) enemy.getSprite()).getBitmap();
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        fireSkullSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = fireSkullSprite.getWidth();
        height = fireSkullSprite.getHeight();
        enemy.setSpriteWidth(width);
        enemy.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(fireSkullSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        invalidate();
    }


}