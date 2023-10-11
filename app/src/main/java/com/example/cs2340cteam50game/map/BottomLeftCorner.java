package com.example.cs2340cteam50game.map;

import android.graphics.Rect;

public class BottomLeftCorner extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getBottomLeftCorner();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
