package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test of Counter class.
 *
 * @author Alexander Ivanov
 * @since 07.01.2016
 * @version 1.0
 */
public class CounterTest {
	/**
	 * Test calculate sum if start number is 5 and last is 16.
	 */
	@Test
	public void whenStartIsFiveFinishIsSixteen() {
		final int start = 5;
		final int finish = 16;
		Counter counter = new Counter();
		final int checkSum = 66;
		final int resultSum = counter.add(start, finish);
		assertThat(resultSum, is(checkSum));
	}
}