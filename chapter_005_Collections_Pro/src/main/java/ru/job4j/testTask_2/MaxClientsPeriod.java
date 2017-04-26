package ru.job4j.testTask_2;

/**
 * Created by Ivanov_ab on 26.04.2017.
 */
public class MaxClientsPeriod {
    private long start;
    private long end;
    private int maximumOfClients;

    public MaxClientsPeriod(long start, long end, int maximumOfClients) {
        this.start = start;
        this.end = end;
        this.maximumOfClients = maximumOfClients;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getMaximumOfClients() {
        return maximumOfClients;
    }

    public void setMaximumOfClients(int maximumOfClients) {
        this.maximumOfClients = maximumOfClients;
    }
}
