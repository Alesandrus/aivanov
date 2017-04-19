package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test of MaxFromThree class.
 *
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */
public class MaxFromThreeTest {
	/**
	 * test max. First > second.
	 */
	@Test
	public void whenFirstMoreThanSecond() {
		final int first = 10;
		final int second = 5;
		MaxFromThree maximum = new MaxFromThree();
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
		MaxFromThree maximum = new MaxFromThree();
		final int checkMax = 12;
		final int result = maximum.max(first, second);
		assertThat(result, is(checkMax));
	}

	/**
	 * test max. First > (second && third).
	 */
	@Test
	public void whenFirstIsMax() {
		final int first = 15;
		final int second = 12;
		final int third = 10;
		MaxFromThree maximum = new MaxFromThree();
		final int checkMax = 15;
		final int result = maximum.max(first, second, third);
		assertThat(result, is(checkMax));
	}

	/**
	 * test max. Second > (first && third).
	 */
	@Test
	public void whenSecondIsMax() {
		final int first = 1;
		final int second = 12;
		final int third = 10;
		MaxFromThree maximum = new MaxFromThree();
		final int checkMax = 12;
		final int result = maximum.max(first, second, third);
		assertThat(result, is(checkMax));
	}

	/**
	 * test max. Third > (first && second).
	 */
	@Test
	public void whenThirdIsMax() {
		final int first = 10;
		final int second = 20;
		final int third = 30;
		MaxFromThree maximum = new MaxFromThree();
		final int checkMax = 30;
		final int result = maximum.max(first, second, third);
		assertThat(result, is(checkMax));
	}
}