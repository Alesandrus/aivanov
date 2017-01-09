package ru.job4j;

/**
 * Class for sort array.
 * @author Alexander Ivanov
 * @since 08.01.2016
 * @version 1.0
 */

public class SortArray {
	/**
	 * Sort array.
	 * @param array - array for sort.
	 * @return array - sort array.
	 */
	public int[] sort(int[] array) {
		int maxTemp;
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if (array[j - 1] > array[j]) {
					maxTemp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = maxTemp;
				}
			}
		}
		return array;
	}
}