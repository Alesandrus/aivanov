package ru.job4j;

/**
 * Class for calculate the sum of even numbers.
 * @author Alexander Ivanov
 * @since 07.01.2016
 * @version 1.0
 */

public class Counter {
	/**
	 * Calculating the sum of even numbers.
	 * @param start - first number.
	 * @param finish - last number.
	 * @return sum - the sum of even numbers.
	 */
	public int add(int start, int finish) {
		int sum = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}