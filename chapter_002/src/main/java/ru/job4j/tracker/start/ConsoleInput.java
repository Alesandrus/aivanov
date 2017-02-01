package ru.job4j.tracker.start;

import java.util.Scanner;

/**
 * Class ConsoleInput.
 * @author Alexander Ivanov
 * @since 29.01.2016
 * @version 1.0
 */

public class ConsoleInput implements Input {

	/**
	 * scanner for input.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Method for asking question.
	 * @param question asking user for input.
	 * @return scanner.
	 */
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
}