package ru.job4j.chess;

/**
 * Class ImpossibleMoveException.
 * @author Alexander Ivanov
 * @since 12.02.2017
 * @version 1.0
 */
public class ImpossibleMoveException extends Exception {
    /**
     * constructor for ImpossibleMoveException.
     * @param msg information about Exception.
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
