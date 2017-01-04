package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование класса Calculator.
 *
 * @author Alexander Ivanov
 * @since 05.01.2016
 * @version 1.0
 */
public class CalculatorTest {
	/**
	 * Introduce first number to test.
	 */
	 private final double first = 100.0;

	 /**
	 * Introduce second number to test.
	 */
	 private final double second = 10.0;

	/**
	 * Test addition.
	 */
	@Test
	public void whenAddOneToThenTwo() {
		Calculator calc = new Calculator();
		final double resultAdd = 110.0;
		calc.add(first, second);
		assertThat(calc.getResult(), is(resultAdd));
	}

	/**
	 * Test subtraction.
	 */
	@Test
	public void whenSubtractOneFromThenTwo() {
		Calculator calc = new Calculator();
		final double resultSubtract = 90.0;
		calc.subtract(first, second);
		assertThat(calc.getResult(), is(resultSubtract));
	}

	/**
	 * Test division.
	 */
	@Test
	public void whenDivideOneByThenTwo() {
		Calculator calc = new Calculator();
		final double resultDiv = 10.0;
		calc.div(first, second);
		assertThat(calc.getResult(), is(resultDiv));
	}

	/**
	 * Test multiplication.
	 */
	@Test
	public void whenMultiplyOneByThenTwo() {
		Calculator calc = new Calculator();
		final double resultMultiply = 1000.0;
		calc.multiply(first, second);
		assertThat(calc.getResult(), is(resultMultiply));
	}

	/**
	 * Test return result.
	 */
	@Test
	public void whenReturnResult() {
		Calculator calc = new Calculator();
		final double resultTest = 0.0;
		assertThat(calc.getResult(), is(resultTest));
	}
}