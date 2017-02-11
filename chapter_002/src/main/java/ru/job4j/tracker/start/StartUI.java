package ru.job4j.tracker.start;

/**
 * Class StartUIwithSwitch.
 * @author Alexander Ivanov
 * @since 26.01.2016
 * @version 1.0
 */

public class StartUI {
    /**
     * variable of input.
     */
    private Input input;

    /**
     * task tracker.
     */
    private Tracker tracker = new Tracker();
    /**
     * constructor for MenuTracker.
     * @param input for enter information.
     */
    public StartUI(Input input) {
        this.input = input;
    }
    /**
     * initialize input.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        int choice;
        System.out.println("Welcome to tracker!");
        do {
            menu.show();
            choice = Integer.parseInt(input.ask("Enter number of action: \n"));
            if (choice != 8) {
                menu.select(choice);
            }
        } while (choice != 8);
        System.out.println("Good bye");
    }

    /**
     * PSVM.
     * @param args - args.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUI(input).init();
    }

    /**
     * method for getting tracker.
     * @return tracker.
     */
    public Tracker getTracker() {
        return tracker;
    }

    /**
     * method for setting tracker.
     * @param  tracker for setting.
     */
    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }
}