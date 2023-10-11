package com.example.cs2340cteam50game.map;

import android.graphics.Rect;



public class Tile {

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
    public static Tile getTile(int indexTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {

        switch(TileType.values()[indexTileType]) {
            case TopLeft_Corner:
                return new TopLeftCorner(spriteSheet, mapLocationRect);
        }

    }
}
