package com.example.cs2340cteam50game.map;

import static com.example.cs2340cteam50game.map.MapLayout.NUM_TILES_ROW;
import static com.example.cs2340cteam50game.map.MapLayout.NUM_TILES_COL;


public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;

    public Tilemap(String filename) {
        mapLayout = new MapLayout(filename);
        initializeTilemap();
    }

    private void initializeTilemap() {
        int[][] layout = mapLayout.getLayout();
        for (int row = 0; row < NUM_TILES_ROW; row++) {
            for (int col = 0; col < NUM_TILES_COL; col++) {
                tilemap[row][col] = Tile.getTile();
            }
        }
    }
}
