package com.example.cs2340cteam50game.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.cs2340cteam50game.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 96;
    private static final int SPRITE_HEIGHT_PIXELS = 96;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false; //tbd on what this does
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet_map,
                bitmapOptions);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getTopLeftCorner() {
        return getSpriteByIndex(0, 0); //row, col
    }

    private Sprite getSpriteByIndex(int row, int col) {
        return new Sprite(this, new Rect(
                col * SPRITE_WIDTH_PIXELS,row * SPRITE_HEIGHT_PIXELS,
                (col + 1) * SPRITE_WIDTH_PIXELS, (row + 1) * SPRITE_HEIGHT_PIXELS
        ));
    }
    public Sprite getTopWall() {
        return getSpriteByIndex(0, 1); //row, col
    }
    public Sprite getTopRightCorner() {
        return getSpriteByIndex(0, 2); //row, col
    }

    public Sprite getLeftWall() {
        return getSpriteByIndex(0, 3); //row, col
    }

    public Sprite getFloor() {
        return getSpriteByIndex(0, 4); //row, col
    }

    public Sprite getRightWall() {
        return getSpriteByIndex(0, 5); //row, col
    }

    public Sprite getBottomWall() {
        return getSpriteByIndex(0, 6); //row, col
    }

    public Sprite getBottomRightCorner() {
        return getSpriteByIndex(0, 7); //row, col
    }
    public Sprite getBottomLeftCorner() {
        return getSpriteByIndex(0, 8); //row, col
    }

}
