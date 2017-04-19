package ru.job4j.tracker.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import ru.job4j.tracker.models.Item;

import java.util.ArrayList;

/**
 * test class Item.
 *
 * @author Alexander Ivanov
 * @since 28.01.2016
 * @version 1.0
 */
 public class TrackerTest {

	 /**
	 * test method for checking add() and getAll().
	 */
	@Test
	public void whenAddItemsToTracker() {
		Tracker tracker = new Tracker();
		final int checkItemsLength = 10;
        ArrayList<Item> checkItems = new ArrayList<>();
        checkItems.add(tracker.add(new Item("Name_1", "Desc_1")));
        checkItems.add(tracker.add(new Item("Name_2", "Desc_2")));
        checkItems.add(tracker.add(new Item("Name_3", "Desc_3")));
        checkItems.add(tracker.add(new Item("Name_4", "Desc_4")));
        checkItems.add(tracker.add(new Item("Name_5", "Desc_5")));
        checkItems.add(tracker.add(new Item("Name_6", "Desc_6")));
        checkItems.add(tracker.add(new Item("Name_7", "Desc_7")));
        checkItems.add(tracker.add(new Item("Name_8", "Desc_8")));
        checkItems.add(tracker.add(new Item("Name_9", "Desc_9")));
        checkItems.add(tracker.add(new Item("Name_10", "Desc_10")));
		ArrayList<Item> resultItems = tracker.getAll();
		assertThat(resultItems, is(checkItems));
	}

    /**
     * test method for findById().
     */
    @Test
    public void whenFindId() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        String id = "N23";
        item.setId(id);
        Item resultItem = tracker.findById(id);
        assertThat(resultItem, is(item));
    }

    /**
     * test method for update().
     */
    @Test
    public void whenUpdateTracker() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        ArrayList<Item> items = tracker.getAll();
        String id = items.get(0).getId();
        Item checkItem = new Item("Item", "Description");
        checkItem.setId(id);
        tracker.update(checkItem);
        ArrayList<Item> resultItems = tracker.getAll();
        assertThat(resultItems.get(0), is(checkItem));
    }

    /**
     * test method for delete().
     */
    @Test
    public void whenDeleteItemFromTracker() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        ArrayList<Item> items = tracker.getAll();
        String id = items.get(0).getId();
        Item deleteItem = new Item("Item", "Description");
        deleteItem.setId(id);
        tracker.delete(deleteItem);
        ArrayList<Item> resultItems = tracker.getAll();
        Item checkItem = null;
        assertThat(resultItems.get(0), is(checkItem));
    }

    /**
     * test method for findByName().
     */
    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Name_1", "Desc_1");
        tracker.add(item);
        String name = "Name_1";
        Item resultItem = tracker.findByName(name);
        assertThat(resultItem, is(item));
    }

    /**
     * test method for addComment().
     */
    @Test
    public void whenAddCommentToItem() {
        Tracker tracker = new Tracker();
        Item itemForComment = new Item("Name_1", "Desc_1");
        tracker.add(itemForComment);
        ArrayList<Item> items = tracker.getAll();
        String id = items.get(0).getId();
        Item checkItem = new Item("Name_1", "Desc_1");
        checkItem.setId(id);
        ArrayList<String> checkComments = checkItem.getComments();
        checkComments.add("test");
        checkComments.add("Comments");
        tracker.addComment(checkItem, "test");
        tracker.addComment(checkItem, "Comments");
        ArrayList<Item> resultItems = tracker.getAll();
        ArrayList<String> resultComments = resultItems.get(0).getComments();
        assertThat(resultComments, is(checkComments));
    }
 }