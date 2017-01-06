package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Test of Triangle class.
 *
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */
public class TriangleTest {
	/**
	 * Test calculate area of triangle, when triangle is existing.
	 */
	@Test
	public void whenCalculateAreaOfTriangle() {
		final Point firstPoint = new Point(1, 1);
		final Point secondPoint = new Point(10, 5);
		final Point thirdPoint = new Point(16, 3);
		Triangle triangle = new Triangle(firstPoint, secondPoint, thirdPoint);
		final double checkArea = 21.0;
		final double error = 0.01;
		final double triangleArea = triangle.area();
		assertThat(triangleArea, is(closeTo(checkArea, error)));
	}

	/**
	 * Test calculate area of triangle, when triangle isn`t existing.
	 */
	@Test
	public void whenOneSideIsSumOtherTwo() {
		final Point firstPoint = new Point(0, 0);
		final Point secondPoint = new Point(4, 0);
		final Point thirdPoint = new Point(9, 0);
		Triangle triangle = new Triangle(firstPoint, secondPoint, thirdPoint);
		final double checkArea = 0.0;
		final double error = 0.01;
		final double triangleArea = triangle.area();
		assertThat(triangleArea, is(closeTo(checkArea, error)));
	}

	/**
	 * Test calculate area of triangle, when triangle isn`t existing and two points is eqals.
	 */
	@Test
	public void whenOneSideIsZero() {
		final Point firstPoint = new Point(0, 0);
		final Point secondPoint = new Point(0, 0);
		final Point thirdPoint = new Point(9, 0);
		Triangle triangle = new Triangle(firstPoint, secondPoint, thirdPoint);
		final double checkArea = 0.0;
		final double error = 0.01;
		final double triangleArea = triangle.area();
		assertThat(triangleArea, is(closeTo(checkArea, error)));
	}
}