package ru.job4j.testTask_3;

import java.util.Calendar;

/**
 * Class Operation.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2017
 */
public class Operation {

    /**
     * State in begin of operation.
     */
    private State begin;

    /**
     * State in end of operation.
     */
    private State end;

    /**
     * Date when state is changed.
     */
    private Calendar dateOfExecute;

    /**
     * Constructor for Operation.
     * @param begin state of operation.
     * @param end state of operation.
     * @param dateOfExecute date of state cahnging.
     */
    public Operation(State begin, State end, Calendar dateOfExecute) {
        this.begin = begin;
        this.end = end;
        this.dateOfExecute = dateOfExecute;
    }

    /**
     * Getter for begin.
     * @return begin.
     */
    public State getBegin() {
        return begin;
    }

    /**
     * Getter for end.
     * @return end.
     */
    public State getEnd() {
        return end;
    }

    /**
     * Getter for dateOfExecute.
     * @return dateOfExecute.
     */
    public Calendar getDateOfExecute() {
        return dateOfExecute;
    }
}
