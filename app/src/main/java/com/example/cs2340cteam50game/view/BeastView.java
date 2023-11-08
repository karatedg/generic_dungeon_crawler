package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.cs2340cteam50game.R;
import com.example.cs2340cteam50game.model.BeastCreator;
import com.example.cs2340cteam50game.model.BeastEnemy;


public class BeastView extends View {
    private BeastEnemy enemy;
    private Bitmap beastSprite;

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

    private BeastCreator beastCreator = new BeastCreator();

    public BeastView(Context context) {
        super(context);
        enemy = (BeastEnemy) beastCreator.createEnemy();
        int spriteID = R.drawable.beast;
        Drawable sprite = getResources().getDrawable(spriteID);
        enemy.setSprite(sprite);
        Bitmap temp = ((BitmapDrawable) enemy.getSprite()).getBitmap();
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        beastSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = beastSprite.getWidth();
        height = beastSprite.getHeight();
        enemy.setSpriteWidth(width);
        enemy.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(beastSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) enemy.getxPos();
        this.y = (float) enemy.getyPos();
        invalidate();
    }


}