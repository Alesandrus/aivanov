package ru.job4j;

/**
 * Class for return maximum.
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */

public class Max {
	/**
	 * Calculating maximum from two numbers.
	 * @param first - first number.
	 * @param second - second number.
	 * @return maximum from two numbers.
	 */
	public int max(int first, int second) {
		return first >= second ? first : second;
	}
}