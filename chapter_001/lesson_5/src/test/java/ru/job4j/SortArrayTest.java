package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class SortArray.
 *
 * @author Alexander Ivanov
 * @since 08.01.2016
 * @version 1.0
 */
public class SortArrayTest {

	/**
	 * Test sort array.
	 */
	@Test
	public void whenArrayIsUnsort() {
		SortArray arraySort = new SortArray();
		final int a = 15;
		final int b = -2;
		final int c = 20;
		final int d = 5;
		final int e = 1000;
		final int f = 10;
		int[] array = {a, b, c, d, e, f};
		int[] checkArray = {b, d, f, a, c, e};
		int[] resultArray = arraySort.sort(array);
		assertThat(resultArray, is(checkArray));
	}
}