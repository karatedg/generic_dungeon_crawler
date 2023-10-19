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
    private double width;
    private double height;
    private float x;
    private float y;

    public PlayerView(Context context) {
        super(context);
        player = PlayerClass.getPlayer();
        playerSprite = ((BitmapDrawable) player.getSprite()).getBitmap();
        this.x = (float) player.getX();
        this.y = (float) player.getY();
        this.width = playerSprite.getWidth();
        this.height = playerSprite.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(playerSprite, x, y, null);
    }

    public void updatePosition() {
        this.x = (float) player.getX();
        this.y = (float) player.getY();
        invalidate();
    }

    public int getPlayerWidth() {
        return playerSprite.getWidth();
    }

    public int getPlayerHeight() {
        return playerSprite.getHeight();
    }

}
