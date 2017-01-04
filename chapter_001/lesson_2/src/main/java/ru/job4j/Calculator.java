package ru.job4j;

/**
 * Class Класс для вычисления арифметических операций + - * /.
 * @author Alexander Ivanov
 * @since 04.01.2016
 * @version 1.0
 */

public class Calculator {
	/**
	 * Поле, в которое заносится результат.
	 */
	private double result = 0;

	/**
	 * Сложение двух чисел типа double.
	 * @param first - first number.
	 * @param second - second number.
	 */
	public void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 * Вычитание двух чисел типа double.
	 * @param first - first number.
	 * @param second - second number.
	 */
	public void subtract(double first, double second) {
		this.result = first - second;
	}

	/**
	 * Деление двух чисел типа double.
	 * @param first - first number.
	 * @param second - second number.
	 */
	public void div(double first, double second) {
		this.result = first / second;
	}

	/**
	 * Умножение двух чисел типа double.
	 * @param first - first number.
	 * @param second - second number.
	 */
	public void multiply(double first, double second) {
		this.result = first * second;
	}

	/**
	 * Метод, возвращающий результат.
	 * @return result.
	 */
	public double getResult() {
		return this.result;
	}
}