package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.models.Task;

/**
 * Class StartUI.
 * @author Alexander Ivanov
 * @since 26.01.2016
 * @version 1.0
 */

public class StartUI {

	/**
	 * PSVM.
	 * @param args - args.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		tracker.add(new Item("first item", "second desc"));
		tracker.add(new Task("second task", "third desc"));
		for (Item i : tracker.getAll()) {
			System.out.println(i.getName());
			System.out.println("id: " + i.getId() + "\n");
		}
	}
}