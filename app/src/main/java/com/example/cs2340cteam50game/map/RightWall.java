package com.example.cs2340cteam50game.map;

public class RightWall extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getRightWall();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
