package ru.job4j.tracker.start;

import ru.job4j.tracker.models.Item;
import ru.job4j.tracker.models.Task;

import java.util.ArrayList;

/**
 * Class MenuTracker.
 * @author Alexander Ivanov
 * @since 06.02.2016
 * @version 1.0
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[8];
    private int countAddedTask = 0;
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.EditItem();
        this.actions[2] = new DeleteItem();
        this.actions[3] = new ShowItems();
        this.actions[4] = new FindItemByName();
        this.actions[5] = new FindItemById();
        this.actions[6] = new AddComment();
        this.actions[7] = new ShowComments();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        System.out.println("Choose action from 0 to 8");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("8. Exit.");
    }

    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please enter the task's name: ");
            String desc = input.ask("Please enter the task's desc: ");
            Item item = new Task(name, desc);
            tracker.add(item);
            countAddedTask++;
            if (countAddedTask <= tracker.getAll().length) {
                System.out.println("You have successfully added the task. Your task id is: " + item.getId());
            } else {
                System.out.println("Sorry you can't add task. List of task is full.");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    private static class EditItem implements UserAction {
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter ID of edit task - ");
            Item item = new Task();
            item.setId(id);
            if (tracker.hasId(item)) {
                String name = input.ask("Change name of task - ");
                String desc = input.ask("Change description of task - ");
                item = new Task(name, desc);
                item.setId(id);
                tracker.update(item);
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Edit the item.");
        }
    }

    private class ShowItems implements UserAction {
        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("The List of tasks: ");
            Item[] items = tracker.getAll();
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    System.out.println("Task - " + items[i].getName() + " with ID - " + items[i].getId());
                }
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    private class FindItemByName implements UserAction {
        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name of task to search - ");
            Item item = tracker.findByName(name);
            if (item != null) {
                System.out.println("Your task was found. Task ID is: " + item.getId());
            } else {
                System.out.println("!!!There is not task with this name!!!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find task by name.");
        }
    }

    private class FindItemById implements UserAction {
        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter ID of task to search - ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Your task was found. Task name is: " + item.getName());
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find task by ID.");
        }
    }

    private class AddComment implements UserAction {
        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of task to comment - ");
            Item item = new Task();
            item.setId(id);
            if (tracker.hasId(item)) {
                String comment = input.ask("Enter your comment - ");
                tracker.addComment(item, comment);
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add comment to task.");
        }
    }

    private class ShowComments implements UserAction {
        public int key() {
            return 7;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id of task to show comments - ");
            Item item = new Task();
            item.setId(id);
            if (tracker.hasId(item)) {
                ArrayList<String> comments = tracker.showComments(item);
                if (comments.size() > 0) {
                    for (int i = 1; i <= comments.size(); i++) {
                        System.out.println("Comment #" + i + " - " + comments.get(i - 1));
                    }
                } else {
                    System.out.println(("This task is not got comments yet"));
                }
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all comments of task.");
        }
    }
}

class DeleteItem implements UserAction {
    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter id task to delete - ");
        Item item = new Task();
        item.setId(id);
        if (tracker.hasId(item)) {
            tracker.delete(item);
            System.out.println("Item deleted.");
        } else {
            System.out.println("!!!There is not task with this ID!!!");
        }
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Delete the item.");
    }
}
