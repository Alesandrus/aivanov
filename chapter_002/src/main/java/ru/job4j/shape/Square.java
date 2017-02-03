package ru.job4j.shape;

/**
 * Class Square.
 * @author Alexander Ivanov
 * @since 03.02.2017
 * @version 1.0
 */
public class Square implements Shape {
	/**
    * return square in pseudographics.
    * @return square.
    */
	public String pic() {
		String nLine = System.getProperty("line.separator");
		String square = "*****" + nLine + "*****" + nLine + "*****" + nLine + "*****" + nLine + "*****";
		return square;
	}
}