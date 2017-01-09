package ru.job4j;

import java.util.Arrays;

/**
 * Class for delete copy of strings in the array.
 * @author Alexander Ivanov
 * @since 09.01.2016
 * @version 1.0
 */

public class DeleteCopy {
	/**
	 * Delete Copy of strings.
	 * @param array - array of strings.
	 * @return array - array without copy of strings.
	 */
	public String[] deleteCopy(String[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] != null && array[i].equals(array[j])) {
				array[j] = null;
				}
			}
		}
		//shift null-String to the right part of array
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null && i != array.length - 1) {
				for (int j = i + 1; j < array.length; j++) {
					if (array[j] != null) {
						array[i] = array[j];
						array[j] = null;
						break;
					}
				}
			}
		}
		//count new array length
		int lengthCopyArray = 0;
		for (String s : array) {
			if (s != null) {
				lengthCopyArray++;
			}
		}
		//create copyArray for copy array without dublicate of strings
		String[] copyArray = Arrays.copyOf(array, lengthCopyArray);
		return copyArray;
	}
}