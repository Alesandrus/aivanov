package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test Figures classes.
 *
 * @author Alexander Ivanov
 * @since 15.02.2017
 * @version 1.0
 */
 public class FigureActionTest {

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
	 * Test for check Rook's way to left.
	 */
	@Test
	public void whenRookGoToLeft() {
		Cell src = new Cell();
		src.setNumberX(3);
		src.setNumberY(4);
		Rook rook = new Rook(src);
		FigureAction action = new FigureAction(rook);
		Cell dist = new Cell();
		dist.setNumberX(3);
		dist.setNumberY(2);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(3, 3), new Cell(3, 2)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check Rook's way to right.
	 */
	@Test
	public void whenRookGoToRight() {
		Cell src = new Cell();
		src.setNumberX(5);
		src.setNumberY(1);
		Rook rook = new Rook(src);
		FigureAction action = new FigureAction(rook);
		Cell dist = new Cell();
		dist.setNumberX(5);
		dist.setNumberY(5);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(5, 2), new Cell(5, 3),
				new Cell(5, 4), new Cell(5, 5)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check Rook's way to up.
	 */
	@Test
	public void whenRookGoToUp() {
		Cell src = new Cell();
		src.setNumberX(4);
		src.setNumberY(3);
		Rook rook = new Rook(src);
		FigureAction action = new FigureAction(rook);
		Cell dist = new Cell();
		dist.setNumberX(1);
		dist.setNumberY(3);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(3, 3), new Cell(2, 3),
				new Cell(1, 3)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check Rook's way to down.
	 */
	@Test
	public void whenRookGoToDown() {
		Cell src = new Cell();
		src.setNumberX(0);
		src.setNumberY(7);
		Rook rook = new Rook(src);
		FigureAction action = new FigureAction(rook);
		Cell dist = new Cell();
		dist.setNumberX(4);
		dist.setNumberY(7);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(1, 7), new Cell(2, 7),
				new Cell(3, 7), new Cell(4, 7)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check wrong Rook's way.
	 */
	@Test
	public void whenRookGoToWrongWay() {
		Cell src = new Cell();
		src.setNumberX(5);
		src.setNumberY(4);
		Rook rook = new Rook(src);
		FigureAction action = new FigureAction(rook);
		Cell dist = new Cell();
		dist.setNumberX(4);
		dist.setNumberY(6);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.print("Move right");
		}
		assertThat(output.toString(), is("Move right"));
	}

	/**
	 * Test for check Bishop's way to UpLeft.
	 */
	@Test
	public void whenBishopGoToUpLeft() {
		Cell src = new Cell();
		src.setNumberX(3);
		src.setNumberY(4);
		Bishop bishop = new Bishop(src);
		FigureAction action = new FigureAction(bishop);
		Cell dist = new Cell();
		dist.setNumberX(0);
		dist.setNumberY(1);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(2, 3), new Cell(1, 2),
				new Cell(0, 1)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check Bishop's way to UpLeft.
	 */
	@Test
	public void whenBishopGoToUpRight() {
		Cell src = new Cell();
		src.setNumberX(4);
		src.setNumberY(1);
		Bishop bishop = new Bishop(src);
		FigureAction action = new FigureAction(bishop);
		Cell dist = new Cell();
		dist.setNumberX(1);
		dist.setNumberY(4);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(3, 2), new Cell(2, 3),
				new Cell(1, 4)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check Bishop's way to UpLeft.
	 */
	@Test
	public void whenBishopGoToDownLeft() {
		Cell src = new Cell();
		src.setNumberX(3);
		src.setNumberY(4);
		Bishop bishop = new Bishop(src);
		FigureAction action = new FigureAction(bishop);
		Cell dist = new Cell();
		dist.setNumberX(6);
		dist.setNumberY(1);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(4, 3), new Cell(5, 2),
				new Cell(6, 1)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check Bishop's way to UpLeft.
	 */
	@Test
	public void whenBishopGoToDownRight() {
		Cell src = new Cell();
		src.setNumberX(4);
		src.setNumberY(2);
		Bishop bishop = new Bishop(src);
		FigureAction action = new FigureAction(bishop);
		Cell dist = new Cell();
		dist.setNumberX(7);
		dist.setNumberY(5);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.println("Move right");
		}
		Cell[] resultWay = new Cell[] {new Cell(5, 3), new Cell(6, 4),
				new Cell(7, 5)};
		assertThat(resultWay, is(checkWay));
	}

	/**
	 * Test for check wrong Bishop's way.
	 */
	@Test
	public void whenBishopGoToWrongWay() {
		Cell src = new Cell();
		src.setNumberX(3);
		src.setNumberY(2);
		Bishop bishop = new Bishop(src);
		FigureAction action = new FigureAction(bishop);
		Cell dist = new Cell();
		dist.setNumberX(7);
		dist.setNumberY(4);
		Cell[] checkWay = null;
		try {
			checkWay = action.moveFig(dist);
		} catch (Exception e) {
			System.out.print("Move right");
		}
		assertThat(output.toString(), is("Move right"));
	}
 }