package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test of Paint class.
 *
 * @author Alexander Ivanov
 * @since 07.01.2016
 * @version 1.0
 */
public class PaintTest {
	/**
	 * test return pyramid with height 4 strings.
	 */
	@Test
	public void whenHieghtIsFour() {
		final int number = 4;
		Paint pyr = new Paint();
		final String checkPyramid = "   ^   \r\n  ^ ^  \r\n ^   ^ \r\n^     ^\r\n";
		final String resultPyramid = pyr.piramid(4);
		assertThat(resultPyramid, is(checkPyramid));
	}
}