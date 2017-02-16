package ru.job4j.chess;

/**
 * Class FigureAction.
 * @author Alexander Ivanov
 * @since 14.02.2017
 * @version 1.0
 */
public class FigureAction {

    /**
     * Figure for action.
     */
    private Figure figure;

    /**
     * Constructor for FigureAction.
     * @param figure for action.
     */
    public FigureAction(Figure figure) {
        this.figure = figure;
    }

    /**
     * Method for return possible way for figure's move.
     * @param dist - destination cell.
     * @return array of cells for move.
     * @throws ImpossibleMoveException when move is impossible.
     */
    public Cell[] moveFig(Cell dist) throws ImpossibleMoveException {
        return figure.way(dist);
    }
}
