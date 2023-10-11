package com.example.cs2340cteam50game.map;

public class Floor extends Tile {
    private final Sprite sprite;

    public TopLeftCorner(Spritesheet spritesheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spritesheet.getFloor();
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
