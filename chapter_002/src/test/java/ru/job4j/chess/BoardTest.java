package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test Board.
 *
 * @author Alexander Ivanov
 * @since 16.02.2017
 * @version 1.0
 */
public class BoardTest {

    /**
     * output for test.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * method for setting stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * method for cleaning stream.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    /**
     * Test for check array of figures on the board.
     */
    @Test
    public void whenSetOneArrayOfFiguresThenGetIt() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 0));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        Figure[] resultFigures = board.getFigures();
        Figure[] checkFigures = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        assertThat(resultFigures, is(checkFigures));
    }

    /**
     * Test for check method move if source cell hasn't figure.
     */
    @Test
    public void whenUseMoveButInCellNoFigure() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 0));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(1, 7), new Cell(2, 5));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        String checkText = "Catch FigureNotFoundException";
        assertThat(output.toString(), is(checkText));
    }

    /**
     * Test for check method move if way is occupied other figures.
     */
    @Test
    public void whenUseMoveButWayIsOccupied() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(5, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(7, 2), new Cell(3, 6));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        String checkText = "Catch OccupiedWayException";
        assertThat(output.toString(), is(checkText));
    }

    /**
     * Test for check method move if use illegal figure's way.
     */
    @Test
    public void whenUseMoveButWayOfFigureIsIllegal() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(5, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(7, 2), new Cell(6, 2));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        String checkText = "Catch ImpossibleMoveException";
        assertThat(output.toString(), is(checkText));
    }

    /**
     * Test for check method move if use legal figure's way.
     */
    @Test
    public void whenUseMoveForLegalWayThenOk() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        boolean resultWay = false;
        try {
            resultWay = board.move(new Cell(7, 2), new Cell(4, 5));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        boolean checkWay = true;
        assertThat(resultWay, is(checkWay));
    }

    /**
     * Test for check cloning in method move.
     */
    @Test
    public void whenUseMoveForLegalWayThebGetClone() {
        Board board = new Board();
        Figure firstBishop = new Bishop(new Cell(7, 2));
        Figure firstRook = new Rook(new Cell(7, 4));
        Figure secondBishop = new Bishop(new Cell(7, 5));
        Figure secondRook = new Rook(new Cell(7, 7));
        Figure[] figuresForBoard = new Figure[] {firstRook, firstBishop, secondBishop, secondRook};
        board.setFigures(figuresForBoard);
        try {
            board.move(new Cell(7, 2), new Cell(4, 5));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        //checking for class
        String resultClass = figuresForBoard[1].getClass().getName();
        assertThat(resultClass, is("ru.job4j.chess.Bishop"));

        //I haven't Getter of Cell for Figures.
        //Use checking move from source cell with bishop1 to move any cell
        //If no any exception isn't caught, bishop is cloned
        boolean resultClone = false;
        try {
            resultClone = board.move(new Cell(4, 5), new Cell(1, 2));
        } catch (ImpossibleMoveException e) {
            System.out.print("Catch ImpossibleMoveException");
        } catch (FigureNotFoundException e) {
            System.out.print("Catch FigureNotFoundException");
        } catch (OccupiedWayException e) {
            System.out.print("Catch OccupiedWayException");
        }
        boolean checkClone = true;
        assertThat(resultClone, is(checkClone));
    }
}
