package ru.job4j.tracker.models;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * test class Item.
 *
 * @author Alexander Ivanov
 * @since 27.01.2016
 * @version 1.0
 */
 public class ItemTest {

	 /**
	 * test method for checking getName().
	 */
	@Test
	public void whenItemNameIsItem() {
		Item item = new Item("Item", "Description");
		String resultName = item.getName();
		String checkName = "Item";
		assertThat(resultName, is(checkName));
	}

	/**
	 * test method for checking getDescription().
	 */
	@Test
	public void whenItemDescriptionIsDescription() {
		Item item = new Item("Item", "Description");
		String resultDescription = item.getDescription();
		String checkDescription = "Description";
		assertThat(resultDescription, is(checkDescription));
	}

	/**
	 * test method for checking getCreate().
	 */
	@Test
	public void whenItemCraeteIsCurrentTime() {
		Item item = new Item("Item", "Description");
		long resultTime = item.getCreate();
		long checkTime = System.currentTimeMillis();
		final long error = 100L;
		assertThat((double) resultTime, is(closeTo(checkTime, error)));
	}

	/**
	 * test method for checking setId() and getID().
	 */
	@Test
	public void whenItemIdIsN123() {
		Item item = new Item("Item", "Description");
		item.setId("N123");
		String resultId = item.getId();
		String checkId = "N123";
		assertThat(resultId, is(checkId));
	}

	/**
	 * test method for checking getComments().
	 */
	@Test
	public void whenGetComments() {
		Item item = new Item("Item", "Description");
		ArrayList<String> refComments = item.getComments();
		refComments.add("test");
		refComments.add("Comment");
		ArrayList<String> resultComments = item.getComments();
		ArrayList<String> checkComments = new ArrayList<>();
		checkComments.add("test");
		checkComments.add("Comment");
		assertThat(resultComments, is(checkComments));
	}
 }