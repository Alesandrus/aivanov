package ru.job4j.testTask_2;

/**
 * Class Client.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 26.04.2017
 */
public class Client {
    /**
     * Incoming client time.
     */
    private long income;

    /**
     * Outcoming client time.
     */
    private long outcome;

    /**
     * Constructor for Client.
     * @param income time.
     */
    public Client(long income) {
        this.income = income;
    }

    /**
     * Setter for outcome.
     * @param outcome time for setting.
     */
    public void setOutcome(long outcome) {
        this.outcome = outcome;
    }

    /**
     * Getter for income.
     * @return income.
     */
    public long getIncome() {
        return income;
    }

    /**
     * Getter for outcome.
     * @return outcome.
     */
    public long getOutcome() {
        return outcome;
    }
}
