package ru.job4j.tracker.models;

/**
 * Class Task.
 * @author Alexander Ivanov
 * @since 23.01.2016
 * @version 1.0
 */

public class Task extends Item {
	/**
	 * Constructor Task with name and description.
	 * @param name - name.
	 * @param desc - description.
	 */
	public Task(String name, String desc) {
		super(name, desc);
	}

	/**
	 * Default Constructor Task.
	 */
	public Task() {
		super();
	}

	/**
	 * calculate price.
	 * @return 100%.
	 */
	public String calculatePrice() {
		return "100%";
	}
}