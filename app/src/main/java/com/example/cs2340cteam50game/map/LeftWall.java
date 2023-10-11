package com.example.cs2340cteam50game.map;

public class LeftWall extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getLeftWall();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
