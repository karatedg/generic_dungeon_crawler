package com.example.cs2340cteam50game.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.cs2340cteam50game.graphics.Sprite;
import com.example.cs2340cteam50game.graphics.SpriteSheet;

public class BottomLeftCorner extends Tile {
    private final Sprite sprite;

    public BottomLeftCorner(SpriteSheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getBottomLeftCorner();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
