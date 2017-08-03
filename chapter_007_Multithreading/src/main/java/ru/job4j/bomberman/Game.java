package ru.job4j.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Game.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 02.08.2017
 */
public class Game {
    /**
     * Game board.
     */
    private final ReentrantLock[][] board;

    /**
     * Constructor for Game.
     * @param sizeBoard size of board NxN.
     */
    public Game(int sizeBoard) {
        this.board = new ReentrantLock[sizeBoard][sizeBoard];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public Game(int sizeBoard, int difficulty, int monsters) {
        this(sizeBoard);

    }

    /**
     * Start game.
     * @param args from CMDLine.
     */
    public static void main(String[] args) {
        Game game = new Game(10);
        Thread monster1 = new Thread(new Monster(game.board, 5, 5), "Monster");
        Thread hero = new Thread(new Hero(game.board), "Hero");
        monster1.start();
        hero.start();
    }
}
