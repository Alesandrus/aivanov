package ru.job4j.tracker.start;

/**
 * Class StartUI.
 * @author Alexander Ivanov
 * @since 26.01.2016
 * @version 1.0
 */

public class StartUI {
    /**
     * range of actions.
     */
    private int[] range;

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
        range = menu.getRangeActions();
        int choice;
        System.out.println("Welcome to tracker!");
        if (input instanceof ConsoleInput) {
            do {
                menu.show();
                choice = input.ask("Enter number of action: \n", range);
                menu.select(choice);
            } while (choice != 8);
        } else if (input instanceof StubInput) {
            do {
                menu.show();
                choice = Integer.parseInt(input.ask("Enter number of action: \n"));
                menu.select(choice);
            } while (choice != 8);
        }
    }

    /**
     * PSVM.
     * @param args - args.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
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