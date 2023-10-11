package com.example.cs2340cteam50game.map;

public class BottomWall extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getBottomWall();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
