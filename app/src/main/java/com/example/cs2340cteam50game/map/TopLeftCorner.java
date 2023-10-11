package com.example.cs2340cteam50game.map;

public class TopLeftCorner extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getTopLeftCorner();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
