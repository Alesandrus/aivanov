package ru.job4j;

/**
 * Class for rotate square matrix.
 * @author Alexander Ivanov
 * @since 08.01.2016
 * @version 1.0
 */

public class RotateMatrix {
	/**
	 * Rotate Matrix.
	 * @param array - square matrix.
	 * @return array - rotate on 90 degrees.
	 */
	public int[][] rotate(int[][] array) {
		int arrLength = array.length;
		//Check - matrix is square?
		for (int i = 0; i < arrLength; i++) {
			if (array[i].length != arrLength) {
				return array;
			}
		}

		for (int i = 0; i < arrLength / 2; i++) {
			for (int j = i; j < arrLength - i - 1; j++) {
				int temp = array[i][j];
				array[i][j] = array[arrLength - j - 1][i];
				array[arrLength - j - 1][i] = array[arrLength - i - 1][arrLength - j - 1];
				array[arrLength - i - 1][arrLength - j - 1] = array[j][arrLength - i - 1];
				array[j][arrLength - i - 1] = temp;
			}
		}
		return array;
	}
}