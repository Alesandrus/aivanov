package ru.job4j;

/**
 * Class for painting pyramid.
 * @author Alexander Ivanov
 * @since 06.01.2016
 * @version 1.0
 */

public class Paint {
	/**
	 * Painting pyramid.
	 * @param h - pyramid's height.
	 * @return pyramid-string.
	 */
	public String piramid(int h) {
		//length is the Number of characters in the string
		int length = (h - 1) * 2 + 1;
		StringBuilder pyramid = new StringBuilder();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < length; j++) {
				if (j == h - i - 1 || j == h + i - 1) {
					pyramid.append('^');
				} else {
					pyramid.append(' ');
				}
			}
			pyramid.append("\r\n");
		}
		return pyramid.toString();
	}
}