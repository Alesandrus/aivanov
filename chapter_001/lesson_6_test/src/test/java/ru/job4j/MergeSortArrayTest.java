package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test class MergeSortArrays.
 *
 * @author Alexander Ivanov
 * @since 16.01.2016
 * @version 1.0
 */
public class MergeSortArrayTest {

	/**
	 * test method addAndSort when length of arrayA bigger arrayB.
	 */
	@Test
	public void whenArrayABiggerArrayB() {
		MergeSortArrays merger = new MergeSortArrays();
		final int[] arrayA = {1, 3, 5, 6, 10, 15, 20};
		final int[] arrayB = {1, 2, 8, 9, 16};
		final int[] checkArray = {1, 1, 2, 3, 5, 6, 8, 9, 10, 15, 16, 20};
		final int[] resultArray = merger.addAndSort(arrayA, arrayB);
		assertThat(resultArray, is(checkArray));
	}

	/**
	 * test method addAndSort when length of arrayB bigger arrayA.
	 */
	@Test
	public void whenArrayBBiggerArrayA() {
		MergeSortArrays merger = new MergeSortArrays();
		final int[] arrayB = {1, 3, 5, 6, 10, 15, 20};
		final int[] arrayA = {1, 2, 8, 9, 16};
		final int[] checkArray = {1, 1, 2, 3, 5, 6, 8, 9, 10, 15, 16, 20};
		final int[] resultArray = merger.addAndSort(arrayA, arrayB);
		assertThat(resultArray, is(checkArray));
	}

	/**
	 * test method addAndSort when arrayA equal arrayB.
	 */
	@Test
	public void whenArrayAEqualArrayB() {
		MergeSortArrays merger = new MergeSortArrays();
		final int[] arrayA = {1, 3, 5, 7, 15};
		final int[] arrayB = {1, 3, 5, 7, 15};
		final int[] checkArray = {1, 1, 3, 3, 5, 5, 7, 7, 15, 15};
		final int[] resultArray = merger.addAndSort(arrayA, arrayB);
		assertThat(resultArray, is(checkArray));
	}
}