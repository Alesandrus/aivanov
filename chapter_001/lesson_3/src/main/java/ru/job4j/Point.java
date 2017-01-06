package ru.job4j;

/**
 * Class for determination Point.
 * @author Alexander Ivanov
 * @since 05.01.2016
 * @version 1.0
 */

public class Point {
	/**
	 * Set coordinates of the x-axis.
	 */
	private double x;

	/**
	 * Set coordinates of the y-axis.
	 */
	private double y;

	/**
	 * Constructor Point(x, y).
	 * @param x - coordinates of the x-axis.
	 * @param y - coordinates of the y-axis.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Calculating distance from point to point.
	 * @param point - second point.
	 * @return distance from point to point.
	 */
	public double distanceTo(Point point) {
		return Math.sqrt((point.x - this.x) * (point.x - this.x)
		+ (point.y - this.y) * (point.y - this.y));
	}
}