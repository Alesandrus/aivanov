package ru.job4j.testTask_3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Class Test.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2017
 */
public class Test {
    /**
     * Main method.
     * @param args for cmd.
     */
    public static void main(String[] args) {
        Calendar firstDate = new GregorianCalendar(2017, 2, 10, 12, 0);
        Task task1 = new Task(firstDate);
        firstDate = new GregorianCalendar(2017, 2, 11, 14, 30);
        task1.execute(State.Processed, firstDate);
        firstDate = new GregorianCalendar(2017, 2, 25, 17, 15);
        task1.execute(State.Resolved, firstDate);
        firstDate = new GregorianCalendar(2017, 3, 2, 11, 0);
        task1.execute(State.Verified, firstDate);
        firstDate = new GregorianCalendar(2017, 3, 3, 15, 20);
        task1.execute(State.Closed, firstDate);

        Calendar secondDate = new GregorianCalendar(2017, 2, 15, 9, 0);
        Task task2 = new Task(secondDate);
        secondDate = new GregorianCalendar(2017, 2, 19, 15, 30);
        task2.execute(State.Processed, secondDate);
        secondDate = new GregorianCalendar(2017, 2, 29, 14, 15);
        task2.execute(State.Resolved, secondDate);
        secondDate = new GregorianCalendar(2017, 3, 20, 11, 0);
        task2.execute(State.Verified, secondDate);
        secondDate = new GregorianCalendar(2017, 3, 23, 15, 20);
        task2.execute(State.Closed, secondDate);

        Calendar thirdDate = new GregorianCalendar(2017, 3, 5, 16, 0);
        Task task3 = new Task(thirdDate);
        thirdDate = new GregorianCalendar(2017, 3, 8, 11, 10);
        task3.execute(State.Processed, thirdDate);
        thirdDate = new GregorianCalendar(2017, 3, 16, 18, 40);
        task3.execute(State.Resolved, thirdDate);
        thirdDate = new GregorianCalendar(2017, 3, 19, 23, 0);
        task3.execute(State.Verified, thirdDate);
        thirdDate = new GregorianCalendar(2017, 4, 5, 12, 0);
        task3.execute(State.Closed, thirdDate);

        Calendar fourthDate = new GregorianCalendar(2017, 4, 20, 12, 0);
        Task task4 = new Task(fourthDate);
        fourthDate = new GregorianCalendar(2017, 4, 26, 15, 20);
        task4.execute(State.Processed, fourthDate);
        fourthDate = new GregorianCalendar(2017, 4, 29, 18, 0);
        task4.execute(State.Closed, fourthDate);

        List<Task> list = new ArrayList<>(Arrays.asList(task1, task2, task3, task4));

        Report report = new Report();
        Calendar begin = new GregorianCalendar(2017, 2, 1, 0, 0);
        Calendar end = new GregorianCalendar(2017, 4, 30, 0, 0);
        Duration duration = Duration.ofDays(10);
        report.genReport(list, begin, end, duration);
    }
}
