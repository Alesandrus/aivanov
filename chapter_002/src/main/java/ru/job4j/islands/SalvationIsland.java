package ru.job4j.islands;

/**
 * Class SalvationIsland.
 * @author Alexander Ivanov
 * @since 21.02.2017
 * @version 1.0
 */
public class SalvationIsland {
    /**
     * Method for return island's square.
     * @param i - number of string.
     * @param j - number of columns.
     * @param arr - map for analyze island.
     * @return square of island.
     */
    private int squareCalc(int i, int j, int[][] arr) {
        int count = 0;
        if (arr[i - 1][j] == 0 && arr[i + 1][j] == 0 && arr[i][j + 1] == 0 && arr[i][j - 1] == 0) {
            return 0;
        }
        if (arr[i - 1][j] == 1) {
            arr[i - 1][j] = 0;
            count = count + 1 + squareCalc(i - 1, j, arr);
        }
        if (arr[i + 1][j] == 1) {
            arr[i + 1][j] = 0;
            count = count + 1 + squareCalc(i + 1, j, arr);
        }
        if (arr[i][j + 1] == 1) {
            arr[i][j + 1] = 0;
            count = count + 1 + squareCalc(i, j + 1, arr);
        }
        if (arr[i][j - 1] == 1) {
            arr[i][j - 1] = 0;
            count = count + 1 + squareCalc(i, j - 1, arr);
        }
        return count;
    }

    /**
     * Method for return max island's square.
     * @param map - map with islands.
     * @return square of the biggest island.
     */
    public int searchTheBestIsland(int[][] map) {
        //add to board of map some water
        int[][] mapWithWaterBoard = new int[map.length + 2][map[0].length + 2];
        for (int i = 0; i < mapWithWaterBoard.length; i++) {
            mapWithWaterBoard[i][0] = 0;
            mapWithWaterBoard[i][mapWithWaterBoard.length - 1] = 0;
        }
        for (int j = 0; j < mapWithWaterBoard[0].length; j++) {
            mapWithWaterBoard[0][j] = 0;
            mapWithWaterBoard[0][mapWithWaterBoard[0].length - 1] = 0;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                mapWithWaterBoard[i + 1][j + 1] = map[i][j];
            }
        }

        //calculate max island's square
        int maxSquare = 0;
        for (int i = 0; i < mapWithWaterBoard.length; i++) {
            for (int j = 0; j < mapWithWaterBoard[0].length; j++) {
                if (mapWithWaterBoard[i][j] == 1) {
                    int islandSquare = squareCalc(i, j, mapWithWaterBoard);
                    if (islandSquare > maxSquare) {
                        maxSquare = islandSquare;
                    }
                }
            }
        }
        return maxSquare;
    }
}
