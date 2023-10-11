package com.example.cs2340cteam50game.map;

import static com.example.cs2340cteam50game.map.MapLayout.NUM_TILES_ROW;
import static com.example.cs2340cteam50game.map.MapLayout.NUM_TILES_COL;
import static com.example.cs2340cteam50game.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.cs2340cteam50game.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.cs2340cteam50game.GameDisplay;
import com.example.cs2340cteam50game.graphics.SpriteSheet;


public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap = new Tile[60][60];
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet) { //String filename
        mapLayout = new MapLayout(); //filename
        this.spriteSheet = spriteSheet;
        initializeTilemap();
    }

    private void initializeTilemap() {
        int[][] layout = mapLayout.getLayout();
        for (int row = 0; row < NUM_TILES_ROW; row++) {
            for (int col = 0; col < NUM_TILES_COL; col++) {
                tilemap[row][col] = Tile.getTile(layout[row][col], spriteSheet, getRectByIndex(row,
                        col));
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(NUM_TILES_COL * TILE_WIDTH_PIXELS,
                NUM_TILES_ROW * TILE_HEIGHT_PIXELS, config);
        Canvas mapCanvas = new Canvas(mapBitmap);
        for (int row = 0; row < NUM_TILES_ROW; row++) {
            for (int col = 0; col < NUM_TILES_COL; col++) {
                tilemap[row][col].draw(mapCanvas);
            }
        }
    }

    private Rect getRectByIndex(int row, int col) {
        return new Rect(col * TILE_WIDTH_PIXELS, row * TILE_HEIGHT_PIXELS,
                (col + 1) * TILE_WIDTH_PIXELS, (row + 1) * TILE_HEIGHT_PIXELS);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(mapBitmap, gameDisplay.getGameRect(),
                gameDisplay.THE_SCREEN_RECT, null);
    }
}
