package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.FireSkullEnemy;



public class FireSkullView extends View implements EnemyView {
    private FireSkullEnemy fireSkull;
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

    public FireSkullView(Context context, FireSkullEnemy fireSkull) {
        super(context);

        this.fireSkull = fireSkull;
        int spriteID = R.drawable.fireskull;
        Drawable sprite = getResources().getDrawable(spriteID);
        fireSkull.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) fireSkull.getSprite()).getBitmap();
        this.x = (float) fireSkull.getxPos();
        this.y = (float) fireSkull.getyPos();
        fireSkullSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = fireSkullSprite.getWidth();
        height = fireSkullSprite.getHeight();
        fireSkull.setSpriteWidth(width);
        fireSkull.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(fireSkullSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) fireSkull.getxPos();
        this.y = (float) fireSkull.getyPos();
        invalidate();
    }

}