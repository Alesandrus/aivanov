package ru.job4j;

/**
 * Class for merger two sort arrays of integers.
 * @author Alexander Ivanov
 * @since 16.01.2016
 * @version 1.0
 */

public class MergeSortArrays {
	/**
	 * Merge two sort arrays.
	 * @param arrayA - first sort array.
	 * @param arrayB - second sort array.
	 * @return copyArrayAndSort - sort merger array .
	 */
	public static int[] addAndSort(int[] arrayA, int[] arrayB) {
		int i = 0;
		int j = 0;
		int[] copyArrayAndSort = new int[arrayA.length + arrayB.length];
		for (int c = 0; c < copyArrayAndSort.length; c++) {
			if (i < arrayA.length && j < arrayB.length) {
				if (arrayA[i] < arrayB[j]) {
					copyArrayAndSort[c] = arrayA[i++];
				} else {
					copyArrayAndSort[c] = arrayB[j++];
				}
			} else if (i < arrayA.length) {
				copyArrayAndSort[c] = arrayA[i++];
			} else {
				copyArrayAndSort[c] = arrayB[j++];
			}
		}
		return copyArrayAndSort;
	}
}