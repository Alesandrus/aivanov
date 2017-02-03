package ru.job4j.shape;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test Paint class.
 *
 * @author Alexander Ivanov
 * @since 03.02.2017
 * @version 1.0
 */
 public class PaintTest {
	/**
	 * output for test.
	 */
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();

	/**
	 * method for setting stream.
	 */
	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(output));
	}

	/**
	 * method for cleaning stream.
	 */
	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

 	/**
	 * Test draw() for getting square.
	 */
	@Test
	public void whenDrawParamIsSquareThenGraphicIsSquare() {
		Paint paint = new Paint();
		String nLine = System.getProperty("line.separator");
		String checkShapeIsSquare = "*****" + nLine + "*****" + nLine + "*****" + nLine + "*****" + nLine + "*****";
		paint.draw(new Square());
		assertThat(checkShapeIsSquare + nLine, is(output.toString()));
		//or
		//Assert.assertEquals(checkShapeIsSquare + nLine, output.toString());
	}

	/**
	 * Test draw() for getting square.
	 */
	@Test
	public void whenDrawParamIsTriangleThenGraphicIsTriangle() {
		Paint paint = new Paint();
		String nLine = System.getProperty("line.separator");
		String checkShapeIsTriangle = "    *    " + nLine + "   ***   " + nLine + "  *****  " + nLine + " ******* " + nLine + "*********";
		paint.draw(new Triangle());
		assertThat(checkShapeIsTriangle + nLine, is(output.toString()));
		//or
		//Assert.assertEquals(checkShapeIsTriangle + nLine, output.toString());
	}
 }