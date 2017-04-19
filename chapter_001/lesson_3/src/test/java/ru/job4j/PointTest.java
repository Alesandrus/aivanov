package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * test of Point class.
 *
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */
public class PointTest {
	/**
	 * test distanceTo.
	 */
	@Test
	public void whenCalculateDistanceFromOnePointToOther() {
		final double firstX = 10.0;
		final double firstY = 5.0;
		final double secondX = 10.0;
		final double secondY = 1.0;
		Point firstPoint = new Point(firstX, firstY);
		Point secondPoint = new Point(secondX, secondY);
		final double distanceFromFirstToSecond = 4.0;
		final double error = 0.01;
		final double result = firstPoint.distanceTo(secondPoint);
		assertThat(result, is(closeTo(distanceFromFirstToSecond, error)));
	}
}