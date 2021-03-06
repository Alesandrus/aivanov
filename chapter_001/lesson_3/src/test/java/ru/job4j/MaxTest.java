package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test of Max class.
 *
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */
public class MaxTest {
	/**
	 * test max. First > second.
	 */
	@Test
	public void whenFirstMoreThanSecond() {
		final int first = 10;
		final int second = 5;
		Max maximum = new Max();
		final int checkMax = 10;
		final int result = maximum.max(first, second);
		assertThat(result, is(checkMax));
	}

	/**
	 * test max. First < second.
	 */
	@Test
	public void whenFirstLessThanSecond() {
		final int first = 7;
		final int second = 12;
		Max maximum = new Max();
		final int checkMax = 12;
		final int result = maximum.max(first, second);
		assertThat(result, is(checkMax));
	}
}