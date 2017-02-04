package ru.job4j.tracker.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.models.Item;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class StartUITest.
 *
 * @author Alexander Ivanov
 * @since 04.02.2017
 * @version 1.0
 */
public class StartUITest {

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
	 * Test for checking add task.
	 */
	@Test
	public void whenAddNewTask() {
		String[] actionTask = new String[] {"1", "Task#1", "Long description", "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI();
		start.init(enter);
		Tracker tracker = start.getTracker();
		Item[] items = tracker.getAll();
		String name = items[0].getName();
		String desc = items[0].getDescription();
		assertThat(name + desc, is("Task#1Long description"));
	}

	/**
	 * Test for editing task.
	 */
	@Test
	public void whenEditTask() {
		Tracker tracker = new Tracker();
		String taskName = "Task#1";
		String taskDescription = "Long description";
		Item taskForAdd = new Item(taskName, taskDescription);
		tracker.add(taskForAdd);
		String id = taskForAdd.getId();
		String[] actionTask = new String[] {"2", id, "Task#777", "Short description", "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		Item[] items = tracker.getAll();
		String nameForTest = items[0].getName();
		String descForTest = items[0].getDescription();
		assertThat(nameForTest + descForTest, is("Task#777Short description"));
	}

	/**
	 * Test for deleting task.
	 */
	@Test
	public void whenDeleteTask() {
		Tracker tracker = new Tracker();
		String taskName = "Task#1";
		String taskDescription = "Long description";
		Item taskForAdd = new Item(taskName, taskDescription);
		tracker.add(taskForAdd);
		String id = taskForAdd.getId();
		String[] actionTask = new String[] {"3", id, "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		Item[] items = tracker.getAll();
		Item checkItem = null;
		assertThat(items[0], is(checkItem));
	}

	/**
	 * Test for showing all tasks.
	 */
	@Test
	public void whenShowAllTasks() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("Task#1", "Desc#1"));
		tracker.add(new Item("Task#2", "Desc#2"));
		tracker.add(new Item("Task#3", "Desc#3"));
		String[] actionTask = new String[] {"4", "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		String nLine = System.getProperty("line.separator");
		String menu = "Choose action from 1 to 8" + nLine + "1. Add new task."
				+ nLine + "2. Edit task." + nLine + "3. Delete task." + nLine + "4. Show all task." + nLine
				+ "5. Find task by filter." + nLine + "6. Add comment to task." + nLine + "7. Show all comments of task."
				+ nLine + "8. Exit." + nLine;
		String listOfTasks = "Task#1" + nLine + "Task#2" + nLine + "Task#3" + nLine;
		assertThat(output.toString(), is( menu + "The List of tasks: " + nLine + listOfTasks + menu + "Good bye" + nLine));
	}

	/**
	 * Test for finding item by name.
	 */
	@Test
	public void whenFindItemByName() {
		Tracker tracker = new Tracker();
		Item firstItem = new Item("Task#1", "Desc#1");
		Item secondItem = new Item("Task#2", "Desc#2");
		Item thirdItem = new Item("Task#3", "Desc#3");
		tracker.add(firstItem);
		tracker.add(secondItem);
		tracker.add(thirdItem);
		String[] actionTask = new String[] {"5", "1", "Task#2", "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		String id = secondItem.getId();
		String nLine = System.getProperty("line.separator");
		String menu = "Choose action from 1 to 8" + nLine + "1. Add new task."
				+ nLine + "2. Edit task." + nLine + "3. Delete task." + nLine + "4. Show all task." + nLine
				+ "5. Find task by filter." + nLine + "6. Add comment to task." + nLine + "7. Show all comments of task."
				+ nLine + "8. Exit." + nLine;
		String chooseFilter = "Choose filter" + nLine + "1. Find by name." + nLine + "2. Find by ID."
				+ nLine + "3. Return to main menu." + nLine;
		String yourTaskId = "Your task was found. Task id is: " + id + nLine;
		assertThat(output.toString(), is( menu + chooseFilter + yourTaskId + menu + "Good bye" + nLine));
	}

	/**
	 * Test for finding item by ID.
	 */
	@Test
	public void whenFindItemByID() {
		Tracker tracker = new Tracker();
		Item firstItem = new Item("Task#1", "Desc#1");
		Item secondItem = new Item("Task#2", "Desc#2");
		Item thirdItem = new Item("Task#3", "Desc#3");
		tracker.add(firstItem);
		tracker.add(secondItem);
		tracker.add(thirdItem);
		String id = secondItem.getId();
		String[] actionTask = new String[] {"5", "2", id, "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		String nLine = System.getProperty("line.separator");
		String menu = "Choose action from 1 to 8" + nLine + "1. Add new task."
				+ nLine + "2. Edit task." + nLine + "3. Delete task." + nLine + "4. Show all task." + nLine
				+ "5. Find task by filter." + nLine + "6. Add comment to task." + nLine + "7. Show all comments of task."
				+ nLine + "8. Exit." + nLine;
		String chooseFilter = "Choose filter" + nLine + "1. Find by name." + nLine + "2. Find by ID."
				+ nLine + "3. Return to main menu." + nLine;
		String yourTaskId = "Your task was found." + nLine;
		assertThat(output.toString(), is( menu + chooseFilter + yourTaskId + menu + "Good bye" + nLine));
	}

	/**
	 * Test for adding comment to task.
	 */
	@Test
	public void whenAddCommentToTask() {
		Tracker tracker = new Tracker();
		String taskName = "Task#1";
		String taskDescription = "Long description";
		Item taskForAdd = new Item(taskName, taskDescription);
		tracker.add(taskForAdd);
		String id = taskForAdd.getId();
		String[] actionTask = new String[] {"6", id, "Add comment", "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		Item[] items = tracker.getAll();
		ArrayList<String> comments = items[0].getComments();
		String comment = comments.get(0);
		assertThat(comment, is("Add comment"));
	}

	/**
	 * Test for showing all comments of task.
	 */
	@Test
	public void whenShowAllCommentsOfTask() {
		Tracker tracker = new Tracker();
		String taskName = "Task#1";
		String taskDescription = "Long description";
		Item taskForAdd = new Item(taskName, taskDescription);
		ArrayList<String> comments = taskForAdd.getComments();
		comments.add("Long Text");
		comments.add("Short Text");
		tracker.add(taskForAdd);
		String id = taskForAdd.getId();
		String[] actionTask = new String[] {"7", id, "8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI(tracker);
		start.init(enter);
		String nLine = System.getProperty("line.separator");
		String menu = "Choose action from 1 to 8" + nLine + "1. Add new task."
				+ nLine + "2. Edit task." + nLine + "3. Delete task." + nLine + "4. Show all task." + nLine
				+ "5. Find task by filter." + nLine + "6. Add comment to task." + nLine + "7. Show all comments of task."
				+ nLine + "8. Exit." + nLine;
		String comment = "Comment #1 - " + "Long Text" + nLine + "Comment #2 - " + "Short Text" + nLine;
		assertThat(output.toString(), is( menu + comment + menu + "Good bye" + nLine));
	}

	/**
	 * Test for checking exit.
	 */
	@Test
	public void whenUseExitFromProgramm() {
		String[] actionTask = new String[] {"8"};
		Input input = new StubInput(actionTask);
		Enter enter = new Enter(input);
		StartUI start = new StartUI();
		start.init(enter);
		String nLine = System.getProperty("line.separator");
		String menu = "Choose action from 1 to 8" + nLine + "1. Add new task."
				+ nLine + "2. Edit task." + nLine + "3. Delete task." + nLine + "4. Show all task." + nLine
				+ "5. Find task by filter." + nLine + "6. Add comment to task." + nLine + "7. Show all comments of task."
				+ nLine + "8. Exit." + nLine;
		assertThat(output.toString(), is( menu + "Good bye" + nLine));
	}
}