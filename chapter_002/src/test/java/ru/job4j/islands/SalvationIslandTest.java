package ru.job4j.islands;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test SalvationIsland.
 *
 * @author Alexander Ivanov
 * @since 22.02.2017
 * @version 1.0
 */
public class SalvationIslandTest {
    /**
     * test for check the biggest island.
     */
    @Test
    public void whenTheBiggestIslandHasSquareTen() {
        int[][] map = new int[][] {
                {0, 0, 0, 1, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        SalvationIsland island = new SalvationIsland();
        final int resultSquare = island.searchTheBestIsland(map);
        final int checkSquare = 10;
        assertThat(resultSquare, is(checkSquare));
    }
}
