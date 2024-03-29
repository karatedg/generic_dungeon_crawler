package com.example.cs2340cteam50game.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.example.cs2340cteam50game.model.PlayerClass;

public class PlayerView extends View {
    private PlayerClass player;
    private Bitmap playerSprite;

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

    public PlayerView(Context context) {
        super(context);
        player = PlayerClass.getPlayer();
        Bitmap temp = ((BitmapDrawable) player.getSprite()).getBitmap();
        this.x = (float) player.getxPos();
        this.y = (float) player.getyPos();
        playerSprite = Bitmap.createScaledBitmap(temp, (int) (1.5 * temp.getWidth()),
                (int) (1.5 * temp.getHeight()), true);
        width = playerSprite.getWidth();
        height = playerSprite.getHeight();
        player.setSpriteWidth(width);
        player.setSpriteHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(playerSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) player.getxPos();
        this.y = (float) player.getyPos();
        invalidate();
    }


}
