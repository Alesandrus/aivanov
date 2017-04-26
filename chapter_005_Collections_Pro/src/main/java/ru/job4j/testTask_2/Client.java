package ru.job4j.testTask_2;

/**
 * Created by Ivanov_ab on 26.04.2017.
 */
public class Client {
    private long income;
    private long outcome;
    public Client(long income) {
        this.income = income;
    }

    public void setOutcome(long outcome) {
        this.outcome = outcome;
    }

    public long getIncome() {
        return income;
    }

    public long getOutcome() {
        return outcome;
    }
}
