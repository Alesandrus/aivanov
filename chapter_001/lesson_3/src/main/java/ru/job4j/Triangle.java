package ru.job4j;

/**
 * Class for determination Area Triangle.
 * @author Alexander Ivanov
 * @since 05.01.2016
 * @version 1.0
 */

public class Triangle {
	/**
	 * Set first point.
	 */
	private Point a;

	/**
	 * Set second point.
	 */
	private Point b;

	/**
	 * Set third point.
	 */
	private Point c;

	/**
	 * Constructor Triangle(first point, second point, third point).
	 * @param a - first point.
	 * @param b - second point.
	 * @param c - third point.
	 */
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Calculating Area of Triangle.
	 * @return distance from point to point.
	 */
	public double area() {
		double ab = a.distanceTo(b);
		double bc = b.distanceTo(c);
		double ac = a.distanceTo(c);
		double calcArea;
		if (ab > 0 && bc > 0 && ac > 0 && ab < (bc + ab)
			&& bc < (ab + ac) && ac < (ab + bc)) {
			//calculate half perimetr
			double p = (ab + bc + ac) / 2;
			calcArea = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
			} else {
				calcArea = 0;
			}
		return calcArea;
	}
}