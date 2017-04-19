package ru.job4j.shape;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test Square class.
 *
 * @author Alexander Ivanov
 * @since 03.02.2017
 * @version 1.0
 */
 public class SquareTest {

	 /**
	 * test for checking pic().
	 */
	@Test
	public void whenUsedPicThenReturnSquareString() {
		Square square = new Square();
		String nLine = System.getProperty("line.separator");
		String checkSquare = "*****" + nLine + "*****" + nLine + "*****" + nLine + "*****" + nLine + "*****";
		String resultSquare = square.pic();
		assertThat(resultSquare, is(checkSquare));
	}
 }