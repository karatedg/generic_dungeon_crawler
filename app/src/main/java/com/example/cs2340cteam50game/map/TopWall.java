package com.example.cs2340cteam50game.map;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.example.cs2340cteam50game.graphics.Sprite;
import com.example.cs2340cteam50game.graphics.SpriteSheet;

public class TopWall extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(SpriteSheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getTopWall();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
