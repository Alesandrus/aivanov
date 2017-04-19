package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test class RotateMatrix.
 *
 * @author Alexander Ivanov
 * @since 09.01.2016
 * @version 1.0
 */
public class RotateMatrixTest {

	/**
	 * test method for rotate square matrix.
	 */
	@Test
	public void whenMatrixIsSquare() {
		RotateMatrix matrix = new RotateMatrix();
		final int a = 0;
		final int b = 1;
		final int c = 2;
		final int d = 3;
		final int e = 4;
		final int f = 5;
		final int g = 6;
		final int h = 7;
		final int i = 8;
		final int j = 9;
		final int k = 10;
		final int l = 11;
		final int m = 12;
		final int n = 13;
		final int o = 14;
		final int p = 15;
		int[][] array = {
			{a, b, c, d},
			{e, f, g, h},
			{i, j, k, l},
			{m, n, o, p}
		};
		int[][] checkArray = {
			{m, i, e, a},
			{n, j, f, b},
			{o, k, g, c},
			{p, l, h, d}
		};
		int[][] resultArray = matrix.rotate(array);
		assertThat(resultArray, is(checkArray));
	}

	/**
	 * test method for rotate not square matrix.
	 */
	@Test
	public void whenMatrixIsNotSquare() {
		RotateMatrix matrix = new RotateMatrix();
		final int a = 0;
		final int b = 1;
		final int c = 2;
		final int d = 3;
		final int e = 4;
		final int f = 5;
		final int g = 6;
		final int h = 7;
		final int i = 8;
		final int j = 9;
		final int k = 10;
		final int l = 11;
		final int m = 12;
		final int n = 13;
		final int o = 14;
		final int p = 15;
		int[][] array = {
			{a, b, c},
			{e, f, g},
			{i, j, k},
			{m, n, o}
		};
		int[][] checkArray = {
			{a, b, c},
			{e, f, g},
			{i, j, k},
			{m, n, o}
		};
		int[][] resultArray = matrix.rotate(array);
		assertThat(resultArray, is(checkArray));
	}
}