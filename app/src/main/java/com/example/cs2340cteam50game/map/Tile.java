package com.example.cs2340cteam50game.map;

import android.graphics.Rect;

import com.example.cs2340cteam50game.graphics.SpriteSheet;

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
    public static Tile getTile(int index, SpriteSheet SpriteSheet, Rect mapLocationRect) {
        switch(TileType.values()[index]) {
            case TopLeft_Corner:
                return new TopLeftCorner(SpriteSheet, mapLocationRect);
            case Top_Wall:
                return new TopWall(SpriteSheet, mapLocationRect));
            case TopRight_Corner:
                return new TopRightCorner(SpriteSheet, mapLocationRect));
            case Left_Wall:
                return new LeftWall(SpriteSheet, mapLocationRect));
            case Floor:
                return new Floor(SpriteSheet, mapLocationRect));
            case Right_Wall:
                return new RightWall(SpriteSheet, mapLocationRect));
            case Bottom_Wall:
                return new BottomWall(SpriteSheet, mapLocationRect));
            case BottomRight_Corner:
                return new BottomRightCorner(SpriteSheet, mapLocationRect));
            case BottomLeft_Corner:
                return new BottomLeftCorner(SpriteSheet, mapLocationRect));
            default:
                return null;
        }
    }
    public abstract void draw(Canvas canvas);
}
