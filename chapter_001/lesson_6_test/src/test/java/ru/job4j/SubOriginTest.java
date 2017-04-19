package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * test class SubOrigin.
 *
 * @author Alexander Ivanov
 * @since 10.01.2016
 * @version 1.0
 */
public class SubOriginTest {

	/**
	 * test method checksub for return true.
	 */
	@Test
	public void whenSubIsPartOfOrigin() {
		SubOrigin suborigin = new SubOrigin();
		String orgin = "test string";
		String sub = "ring";
		boolean checkSub = true;
		boolean resultSub = suborigin.checksub(orgin, sub);
		assertThat(resultSub, is(checkSub));
	}

	/**
	 * test method checksub for return false.
	 */
	@Test
	public void whenSubIsNotPartOfOrigin() {
		SubOrigin suborigin = new SubOrigin();
		String orgin = "test string";
		String sub = "ping";
		boolean checkSub = false;
		boolean resultSub = suborigin.checksub(orgin, sub);
		assertThat(resultSub, is(checkSub));
	}
}