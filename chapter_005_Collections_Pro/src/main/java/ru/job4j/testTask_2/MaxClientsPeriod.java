package ru.job4j.testTask_2;

/**
 * Class MaxClientsPeriod.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 26.04.2017
 */
public class MaxClientsPeriod {
    /**
     * Start time.
     */
    private long start;

    /**
     * End time.
     */
    private long end;

    /**
     * Maximum clients for this period.
     */
    private int maximumOfClients;

    /**
     * Constructor for MaxClientsPeriod.
     * @param start time.
     * @param end time.
     * @param maximumOfClients for the period.
     */
    public MaxClientsPeriod(long start, long end, int maximumOfClients) {
        this.start = start;
        this.end = end;
        this.maximumOfClients = maximumOfClients;
    }

    /**
     * Getter for start.
     * @return start.
     */
    public long getStart() {
        return start;
    }

    /**
     * Setter for start.
     * @param start for setting.
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * Getter for end.
     * @return end.
     */
    public long getEnd() {
        return end;
    }

    /**
     * Setter for end.
     * @param end for setting.
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * Getter for maximumOfClients.
     * @return maximumOfClients.
     */
    public int getMaximumOfClients() {
        return maximumOfClients;
    }

    /**
     * Setter for maximumOfClient.
     * @param maximumOfClients for setting.
     */
    public void setMaximumOfClients(int maximumOfClients) {
        this.maximumOfClients = maximumOfClients;
    }
}
