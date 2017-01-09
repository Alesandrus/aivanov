package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class DeleteCopy.
 *
 * @author Alexander Ivanov
 * @since 09.01.2016
 * @version 1.0
 */
public class DeleteCopyTest {

	/**
	 * Test method for create array without dublicate of strings.
	 */
	@Test
	public void whenArrayHasDublicateOfStrings() {
		DeleteCopy delCopy = new DeleteCopy();
		String[] array = {"Hello", "Hello", "World", "World", "!", "Hello", "Hello"};
		String[] checkArray = {"Hello", "World", "!"};
		String[] resultArray = delCopy.deleteCopy(array);
		assertThat(resultArray, is(checkArray));
	}
}