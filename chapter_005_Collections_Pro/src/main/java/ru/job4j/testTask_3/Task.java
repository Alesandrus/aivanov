package ru.job4j.testTask_3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class Task.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2017
 */
public class Task {
    /**
     * Current state.
     */
    private State state = State.New;

    /**
     * Date of create.
     */
    private Calendar createDate;

    /**
     * Date of update.
     */
    private Calendar updateDate;

    /**
     * List of operations.
     */
    private List<Operation> operationList = new ArrayList<>();

    /**
     * Constructor for Task.
     * @param createDate for setting createDate.
     */
    public Task(Calendar createDate) {
        this.createDate = createDate;
        this.updateDate = createDate;
        operationList.add(new Operation(null, State.New, createDate));
    }

    /**
     * Getter for operationList.
     * @return operationList.
     */
    public List<Operation> getOperationList() {
        return operationList;
    }

    /**
     * Changing state of task and adding to list.
     * @param state for changing.
     * @param date of changing.
     */
    public void execute(State state, Calendar date) {
        State stateBefore = this.state;
        State stateAfter = state;
        operationList.add(new Operation(stateBefore, stateAfter, date));
        this.state = stateAfter;
        updateDate = date;
    }
}
