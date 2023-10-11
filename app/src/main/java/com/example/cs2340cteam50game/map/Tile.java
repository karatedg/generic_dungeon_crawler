package com.example.cs2340cteam50game.map;

import android.graphics.Rect;

abstract class Tile {

    public enum TileType {
        TopLeft_Corner,
        Top_Wall,
        TopRight_Corner,
        Left_Wall,
        Floor,
        Right_Wall,
        Bottom_Wall,
        BottomRight_Corner,
        BottomLeft_Corner

    };
    public static Tile getTile(int index, Spritesheet spriteSheet, Rect mapLocationRect) {
        switch(TileType.values()[index]) {
            case TopLeft_Corner:
                return new TopLeftCorner(spriteSheet, mapLocationRect);
            case Top_Wall:
                return new TopWall(spriteSheet, mapLocationRect));
            case TopRight_Corner:
                return new TopRightCorner(spriteSheet, mapLocationRect));
            case Left_Wall:
                return new LeftWall(spriteSheet, mapLocationRect));
            case Floor:
                return new Floor(spriteSheet, mapLocationRect));
            case Right_Wall:
                return new RightWall(spriteSheet, mapLocationRect));
            case Bottom_Wall:
                return new BottomWall(spriteSheet, mapLocationRect));
            case BottomRight_Corner:
                return new BottomRightCorner(spriteSheet, mapLocationRect));
            case BottomLeft_Corner:
                return new BottomLeftCorner(spriteSheet, mapLocationRect));
            default:
                return null;
        }
    }
    public abstract void draw(Canvas canvas);
}
