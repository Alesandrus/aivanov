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
        Tracker tracker = new Tracker();
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillActions();
        int choice;
        do {
            menu.show();
            choice = Integer.parseInt(input.ask("Enter number of action: \n"));
            if (choice != 8) {
                menu.select(choice);
            }
        } while (choice != 8);

    }

    /**
     * PSVM.
     * @param args - args.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to tracker!");
        Input input = new ConsoleInput();
        new StartUI(input).init();
    }
}