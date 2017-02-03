package ru.job4j.shape;

/**
 * Class Paint.
 * @author Alexander Ivanov
 * @since 03.02.2017
 * @version 1.0
 */
public class Paint {
	/**
    * create variable of Shape.
    */
	private Shape shape;

	/**
    * method for print shape in pseudographics.
    * @param shape for drawing.
    */
	public void draw(Shape shape) {
		this.shape = shape;
		System.out.println(shape.pic());
	}
}