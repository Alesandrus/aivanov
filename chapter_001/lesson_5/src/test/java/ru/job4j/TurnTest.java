package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test class Turn.
 *
 * @author Alexander Ivanov
 * @since 08.01.2016
 * @version 1.0
 */
public class TurnTest {

	/**
	 * test array with even length.
	 */
	@Test
	public void whenArrayHasEvenLength() {
		Turn turn = new Turn();
		final int a = 1;
		final int b = 2;
		final int c = 3;
		final int d = 4;
		final int e = 5;
		final int f = 6;
		int[] array = {a, b, c, d, e, f};
		int[] checkArray = {f, e, d, c, b, a};
		int[] resultArray = turn.back(array);
		assertThat(resultArray, is(checkArray));
	}

	/**
	 * test array with odd length.
	 */
	@Test
	public void whenArrayHasOddLength() {
		Turn turn = new Turn();
		final int a = 7;
		final int b = 8;
		final int c = 9;
		final int d = 10;
		final int e = 11;
		int[] array = {a, b, c, d, e};
		int[] checkArray = {e, d, c, b, a};
		int[] resultArray = turn.back(array);
		assertThat(resultArray, is(checkArray));
	}
}