package ru.job4j;

import java.math.BigInteger;

/**
 * Class for calculate factorial.
 * @author Alexander Ivanov
 * @since 07.01.2016
 * @version 1.0
 */

public class Factorial {
	/**
	 * Calculating factorial.
	 * @param number - number for calculating factorial.
	 * @return fact - factorial.
	 */
	public BigInteger calcFactorial(int number) {
		BigInteger fact = new BigInteger("1");
		if (number == 0) {
			return fact;
		} else {
			for (int i = 1; i <= number; i++) {
				fact = fact.multiply(new BigInteger(String.valueOf(i)));
			}
		}
		return fact;
	}
}