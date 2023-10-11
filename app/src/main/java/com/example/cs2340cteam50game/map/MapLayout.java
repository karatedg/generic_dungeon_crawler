package com.example.cs2340cteam50game.map;

import java.util.Scanner;
import java.io.File;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 96;
    public static final int TILE_HEIGHT_PIXELS = 96;
    public static final int NUM_TILES_ROW = 60;
    public static final int NUM_TILES_COL = 60;

    private int[][] layout;

    public MapLayout(String filename) {
        initializeLayout(filename);
    }

    private void initializeLayout(String filename) {
        layout = readArrayFromFile(filename);
    }


    //Ok, Theoretically this should load in a predefined map from a CSV in the "maps" folder
    //into a 60x60 2D array.
    private static int[][] readArrayFromFile(String filename) {
        int[][] map = new int[60][60];
        int rowNum = 0;

        File temp1 = new File(".");
        File parent1 = temp1.getParentFile();
        File mapToLoad = new File(String.format((parent1.getAbsolutePath() + "/maps/%s.csv"),
                filename)); //I am choosing to ignore the error above bc it should never occur

        try (Scanner myFileReader = new Scanner(mapToLoad)) {
            while (myFileReader.hasNextLine()) {
                String line = myFileReader.nextLine();
                Scanner rowScanner = new Scanner(line);
                rowScanner.useDelimiter(",");
                int colNum = 0;
                while (rowScanner.hasNextInt()) {
                    map[rowNum][colNum++] = rowScanner.nextInt();
                }
                rowNum++;
            }
        } catch (java.io.FileNotFoundException e) { //not that this will help much, just for debug
            e.printStackTrace(); //maybe this will tell us what to do
        }
        return map;
    }

    public int[][] getLayout() {
        return layout;
    }


}