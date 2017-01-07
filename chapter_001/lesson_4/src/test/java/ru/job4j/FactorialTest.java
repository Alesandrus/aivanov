package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.math.BigInteger;

/**
 * Test of Factorial class.
 *
 * @author Alexander Ivanov
 * @since 07.01.2016
 * @version 1.0
 */
public class FactorialTest {
	/**
	 * Test calculate factorial of 20.
	 */
	@Test
	public void whenNumberIsTwenty() {
		final int number = 20;
		Factorial factor = new Factorial();
		final BigInteger checkFact = new BigInteger("2432902008176640000");
		final BigInteger resultFact = factor.calcFactorial(number);
		assertThat(resultFact, is(checkFact));
	}

	/**
	 * Test calculate factorial of 0.
	 */
	@Test
	public void whenNumberIsZero() {
		final int number = 0;
		Factorial factor = new Factorial();
		final BigInteger checkFact = new BigInteger("1");
		final BigInteger resultFact = factor.calcFactorial(number);
		assertThat(resultFact, is(checkFact));
	}
}