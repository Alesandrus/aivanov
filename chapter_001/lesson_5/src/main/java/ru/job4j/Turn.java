package ru.job4j;

/**
 * Class for reverse array.
 * @author Alexander Ivanov
 * @since 08.01.2016
 * @version 1.0
 */

public class Turn {
	/**
	 * Reverse array.
	 * @param array - array for reverse.
	 * @return array - reverse array.
	 */
	public int[] back(int[] array) {
		int temp;
		if (array.length > 0) {
			for (int i = 0; i < array.length / 2; i++) {
				temp = array[i];
				array[i] = array[array.length - 1 - i];
				array[array.length - 1 - i] = temp;
			}
		}
		return array;
	}
}