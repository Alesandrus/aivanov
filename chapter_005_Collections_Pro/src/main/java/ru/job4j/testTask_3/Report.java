package ru.job4j.testTask_3;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;

/**
 * Class Report.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 28.04.2017
 */
public class Report {
    /**
     * Create Trend report.
     * @param list of tasks.
     * @param begin date.
     * @param end date.
     * @param duration - periods for report.
     */
    public void genReport(List<Task> list, Calendar begin, Calendar end, Duration duration) {
        System.out.println("As of the time:\t\t   New \t\tProcessed \tResolved   \tVerified\t Closed");
        System.out.println("----------------------------------------------------------------------------");
        long start = begin.getTimeInMillis();
        long finish = end.getTimeInMillis();
        long period = duration.toMillis();

        for (long i = start; i < finish; i += period) {
            NumberOfStates states = reportByPeriod(list, i, i + period);
            String str = String.format("%td.%tm.%ty %tH:%tM \t\t%5d \t\t%5d \t\t%5d \t\t%5d \t\t%5d",
                    i + period, i + period, i + period, i + period, i + period,
                    states.getNewNumber(), states.getProcNumber(), states.getResNumber(),
                    states.getVerNumber(), states.getCloseNumber());
            System.out.println(str);
        }

    }

    /**
     * Getting number of states by period.
     * @param list of tasks.
     * @param from start period.
     * @param to end period.
     * @return numbers of states by period.
     */
    private NumberOfStates reportByPeriod(List<Task> list, long from, long to) {
        int newNumber = 0;
        int procNumber = 0;
        int resNumber = 0;
        int verNumber = 0;
        int closeNumber = 0;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            List<Operation> operList = task.getOperationList();
            for (int j = 0; j < operList.size(); j++) {
                Operation operation = operList.get(j);
                State state = operation.getEnd();
                long executeTime = operation.getDateOfExecute().getTimeInMillis();
                if (executeTime >= from && executeTime < to) {
                    switch (state) {
                        case New:
                            newNumber++;
                            break;
                        case Processed:
                            procNumber++;
                            break;
                        case Resolved:
                            resNumber++;
                            break;
                        case Verified:
                            verNumber++;
                            break;
                        case Closed:
                            closeNumber++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return new NumberOfStates(newNumber, procNumber, resNumber, verNumber, closeNumber);
    }

    /**
     * Inner class for storing numbers of states.
     */
    private class NumberOfStates {
        /**
         * Number of State.New.
         */
        private int newNumber;

        /**
         * Number of State.Processed.
         */
        private int procNumber;

        /**
         * Number of State.Resolved.
         */
        private int resNumber;

        /**
         * Number of State.Verified.
         */
        private int verNumber;

        /**
         * Number of State.Closed.
         */
        private int closeNumber;

        /**
         * Constructor for NumberOfStates.
         * @param newNumber is Number of State.New.
         * @param procNumber is Number of State.Processed.
         * @param resNumber is Number of State.Resolved.
         * @param verNumber is Number of State.Resolved.
         * @param closeNumber is Number of State.Closed.
         */
        NumberOfStates(int newNumber, int procNumber, int resNumber, int verNumber, int closeNumber) {
            this.newNumber = newNumber;
            this.procNumber = procNumber;
            this.resNumber = resNumber;
            this.verNumber = verNumber;
            this.closeNumber = closeNumber;
        }

        /**
         * Getter for newNumber.
         * @return newNumber.
         */
        public int getNewNumber() {
            return newNumber;
        }

        /**
         * Getter fot procNumber.
         * @return procNumber.
         */
        public int getProcNumber() {
            return procNumber;
        }

        /**
         * Getter for resNumber.
         * @return resNumber.
         */
        public int getResNumber() {
            return resNumber;
        }

        /**
         * Getter for verNumber.
         * @return verNumber.
         */
        public int getVerNumber() {
            return verNumber;
        }

        /**
         * Getter for closeNumber.
         * @return closeNumber.
         */
        public int getCloseNumber() {
            return closeNumber;
        }
    }
}
