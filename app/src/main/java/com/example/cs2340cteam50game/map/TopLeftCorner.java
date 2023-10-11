package com.example.cs2340cteam50game.map;

import android.graphics.Rect;
import android.graphics.Canvas;
import com.example.cs2340cteam50game.graphics.Sprite;
import com.example.cs2340cteam50game.graphics.SpriteSheet;

public class TopLeftCorner extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(SpriteSheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getTopLeftCorner();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
