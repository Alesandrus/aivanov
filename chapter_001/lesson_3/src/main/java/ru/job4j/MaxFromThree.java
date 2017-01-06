package ru.job4j;

/**
 * Class for return maximum from three numbers.
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */

public class MaxFromThree {
	/**
	 * Calculating maximum from two numbers.
	 * @param first - first number.
	 * @param second - second number.
	 * @return maximum from two numbers.
	 */
	public int max(int first, int second) {
		return first >= second ? first : second;
	}

	/**
	 * Calculating maximum from three numbers.
	 * @param first - first number.
	 * @param second - second number.
	 * @param third - third number.
	 * @return maximum from two numbers.
	 */
	public int max(int first, int second, int third) {
		return this.max(this.max(first, second), third);
	}
}