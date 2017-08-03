package ru.job4j.bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hero.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 03.08.2017
 */
public class Hero extends Creature {
    /**
     * Constructor for Hero.
     *
     * @param board Game board.
     */
    public Hero(ReentrantLock[][] board) {
        super(board, 4, 5);
    }

    /**
     * Hero move.
     * In start hero moving to right.
     */
    private int move = 1;

    @Override
    public void run() {
        getPositionCell().lock();

        JFrame frame = new JFrame("Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setSize(400, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new GridBagLayout());
        frame.setOpacity(0.0f);
        frame.setVisible(true);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        move = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        move = 1;
                        break;
                    case KeyEvent.VK_DOWN:
                        move = 2;
                        break;
                    case KeyEvent.VK_LEFT:
                        move = 3;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);

                boolean isMoved = false;
                while (!isMoved) {
                    isMoved = chooseMove(move % 4);
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                System.out.println("Координаты " + Thread.currentThread().getName()
                        + ": x = " + getX() + "  y = " + getY());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
