package com.example.cs2340cteam50game.map;

public class BottomRightCorner extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getBottomRightCorner();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
