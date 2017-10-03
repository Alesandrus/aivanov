package ru.job4j.tracker.start;

import java.util.ArrayList;

/**
 * Interface Input.
 * @author Alexander Ivanov
 * @since 29.01.2016
 * @version 1.0
 */

public interface Input {

	/**
	 * Abstract method for asking question.
	 * @param question asking user for input.
	 * @return String.
	 */
	String ask(String question);

	/**
	 * Abstract method for asking number of action's.
	 * @param question asking user for input.
	 * @param range of numbers.
	 * @return String.
	 */
	int ask(String question, ArrayList<Integer> range);
}