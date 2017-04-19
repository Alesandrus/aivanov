package ru.job4j.shape;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test Triangle class.
 *
 * @author Alexander Ivanov
 * @since 03.02.2017
 * @version 1.0
 */
 public class TriangleTest {

	 /**
	 * test for checking pic().
	 */
	@Test
	public void whenUsedPicThenReturnTriangleString() {
		Triangle triangle = new Triangle();
		String nLine = System.getProperty("line.separator");
		String checkTriangle = "    *    " + nLine + "   ***   " + nLine + "  *****  " + nLine + " ******* " + nLine + "*********";
		String resultTriangle = triangle.pic();
		assertThat(resultTriangle, is(checkTriangle));
	}
 }