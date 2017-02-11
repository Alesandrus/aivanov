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
    /**
     * variable of input.
     */
    private Input input;

    /**
     * task tracker.
     */
    private Tracker tracker;

    /**
     * menu of actions.
     */
    private UserAction[] actions = new UserAction[9];

    /**
     * count for checking task to tracker.
     */
    private int countAddedTask = 0;

    /**
     * constructor for MenuTracker.
     * @param input for enter information.
     * @param tracker of tasks.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * fill menu of actions.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.EditItem();
        this.actions[2] = new DeleteItem();
        this.actions[3] = new ShowItems();
        this.actions[4] = new FindItemByName();
        this.actions[5] = new FindItemById();
        this.actions[6] = new AddComment();
        this.actions[7] = new ShowComments();
        this.actions[8] = new Exit();
    }

    /**
     * method for getting array of actions.
     * @return actions - int[].
     */
    public int[] getRangeActions() {
        int[] rangeActions = new int[actions.length];
        for (int i = 0; i < rangeActions.length; i++) {
            rangeActions[i] = actions[i].key();
        }
        return rangeActions;
    }

    /**
     * method for execute action.
     * @param key for select action.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * method for show user actions.
     */
    public void show() {
        System.out.println("Choose action from 0 to 8");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Inner class to add task.
     */
    private class AddItem implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 0;
        }

        /**
         * method for execute adding.
         * @param input for enter information.
         * @param tracker for tasks.
         */
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

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    /**
     * Inner static class to edit task.
     */
    private static class EditItem implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 1;
        }

        /**
         * method for execute editing.
         * @param input for enter information.
         * @param tracker for tasks.
         */
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

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Edit the item.");
        }
    }

    /**
     * Inner class to show all tasks.
     */
    private class ShowItems implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 3;
        }

        /**
         * method for execute showing.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("The List of tasks: ");
            Item[] items = tracker.getAll();
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null) {
                    System.out.println("Task - " + items[i].getName() + " with ID - " + items[i].getId());
                }
            }
        }

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }

    /**
     * Inner class to find tasks by name.
     */
    private class FindItemByName implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 4;
        }

        /**
         * method for execute finding by name.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter name of task to search - ");
            Item item = tracker.findByName(name);
            if (item != null) {
                System.out.println("Your task was found. Task ID is: " + item.getId());
            } else {
                System.out.println("!!!There is not task with this name!!!");
            }
        }
        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find task by name.");
        }
    }

    /**
     * Inner class to find tasks by ID.
     */
    private class FindItemById implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 5;
        }

        /**
         * method for execute finding by ID.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter ID of task to search - ");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println("Your task was found. Task name is: " + item.getName());
            } else {
                System.out.println("!!!There is not task with this ID!!!");
            }
        }

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find task by ID.");
        }
    }

    /**
     * Inner class to add comment to task.
     */
    private class AddComment implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 6;
        }

        /**
         * method for execute adding comment.
         * @param input for enter information.
         * @param tracker for tasks.
         */
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

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add comment to task.");
        }
    }

    /**
     * Inner class to show comments of task.
     */
    private class ShowComments implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 7;
        }

        /**
         * method for execute showing comments.
         * @param input for enter information.
         * @param tracker for tasks.
         */
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

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all comments of task.");
        }
    }

    /**
     * Inner class to exit.
     */
    private class Exit implements UserAction {
        /**
         * key for choose.
         * @return int key.
         */
        public int key() {
            return 8;
        }

        /**
         * method for execute showing comments.
         * @param input for enter information.
         * @param tracker for tasks.
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("Good bye");
        }

        /**
         * method for print information about action.
         * @return String information.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Exit.");
        }
    }
}

/**
 * Outer class to delete task.
 */
class DeleteItem implements UserAction {
    /**
     * key for choose.
     * @return int key.
     */
    public int key() {
        return 2;
    }

    /**
     * method for execute deleting task.
     * @param input for enter information.
     * @param tracker for tasks.
     */
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

    /**
     * method for print information about action.
     * @return String information.
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Delete the item.");
    }
}
