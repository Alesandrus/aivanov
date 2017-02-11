package ru.job4j.tracker.start;

/**
 * Class StubInput.
 * @author Alexander Ivanov
 * @since 29.01.2017
 * @version 1.0
 */

public class StubInput implements Input {

	/**
	 * answers.
	 */
	private String[] answers;

	/**
	 * position of answer.
	 */
	private int position = 0;

	/**
	 * constructor for StubInput.
	 * @param answers - array of String's
	 */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	 * Method for asking question.
	 * @param question asking user for input.
	 * @return answers.
	 */
	public String ask(String question) {
		return answers[position++];
	}

	@Override
	public int ask(String question, int[] range) {
		//throw new UnsupportedOperationException("Unsupported operation");
		return -1;
	}
}