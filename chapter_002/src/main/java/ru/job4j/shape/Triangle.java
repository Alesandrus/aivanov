package ru.job4j.shape;

/**
 * Class Triangle.
 * @author Alexander Ivanov
 * @since 03.02.2017
 * @version 1.0
 */
public class Triangle implements Shape {
	/**
    * return triangle in pseudographics.
    * @return triangle.
    */
	public String pic() {
		String nLine = System.getProperty("line.separator");
		String triangle = "    *    " + nLine + "   ***   " + nLine + "  *****  " + nLine + " ******* " + nLine + "*********";
		return triangle;
	}
}