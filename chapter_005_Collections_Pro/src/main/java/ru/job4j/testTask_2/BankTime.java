package ru.job4j.testTask_2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class BankTime.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 26.04.2017
 */
public class BankTime {
    /**
     * Time for opening bank.
     */
    private long opening;

    /**
     * Time for closing bank.
     */
    private long closing;

    /**
     * List of all clients by day.
     */
    private List<Client> clientList = new ArrayList<>();

    /**
     * List of periods that contains maximum of clients.
     */
    private List<MaxClientsPeriod> listOfMaxPeriods = new ArrayList<>();

    /**
     * Constructor for BankTime.
     * @param opening time.
     * @param closing time.
     */
    public BankTime(Calendar opening, Calendar closing) {
        this.opening = opening.getTimeInMillis();
        this.closing = closing.getTimeInMillis();
    }

    /**
     * Add client to clientList.
     * @param client with income and outcome time.
     */
    public void addClient(Client client) {
        clientList.add(client);
    }

    /**
     * Adding to list all periods with maximum number of clients.
     */
    private void addToListOfMaxPeriods() {
        long minTimePeriod = 100;
        int maxClients = 0;
        for (long i = opening; i <= closing; i += minTimePeriod) {
            int count = 0;
            long endOfPeriod = i + minTimePeriod;
            for (int j = 0; j < clientList.size(); j++) {
                if (i >= clientList.get(j).getIncome() && endOfPeriod <= clientList.get(j).getOutcome()) {
                    count++;
                }
            }
            if (count > maxClients) {
                maxClients = count;
                listOfMaxPeriods.clear();
                listOfMaxPeriods.add(new MaxClientsPeriod(i, endOfPeriod, maxClients));
            } else if (count == maxClients) {
                listOfMaxPeriods.add(new MaxClientsPeriod(i, endOfPeriod, maxClients));
            }
        }
    }

    /**
     * Getting list of combined periods.
     * @return list.
     */
    private List<MaxClientsPeriod> getListOfMaxPeriods() {
        addToListOfMaxPeriods();
        List<MaxClientsPeriod> listOfPeriods = new ArrayList<>();
        long start = -1;
        long finish = -1;
        for (int i = 0; i < listOfMaxPeriods.size(); i++) {
            long periodStart = listOfMaxPeriods.get(i).getStart();
            long periodFinish = listOfMaxPeriods.get(i).getEnd();
            if (start == -1) {
                start = periodStart;
                finish = periodStart;
            }

            if (periodStart == finish) {
                finish = periodFinish;
            } else {
                listOfPeriods.add(new MaxClientsPeriod(start, finish, listOfMaxPeriods.get(i).getMaximumOfClients()));
                start = periodStart;
                finish = periodFinish;
            }
            if (i == listOfMaxPeriods.size() - 1) {
                listOfPeriods.add(new MaxClientsPeriod(start, finish, listOfMaxPeriods.get(i).getMaximumOfClients()));
            }
        }
        return listOfPeriods;
    }

    /**
     * Show all periods with maximum number of clients.
     */
    public void showMaxPeriods() {
        List<MaxClientsPeriod> listOfPeriods = getListOfMaxPeriods();
        System.out.println("Максимальное количество людей было");
        for (int i = 0; i < listOfPeriods.size(); i++) {
            Date in = new Date(listOfPeriods.get(i).getStart());
            Date out = new Date(listOfPeriods.get(i).getEnd());
            System.out.println(String.format("С %tH:%tM:%tS по %tH:%tM:%tS - %d клиентов",
                    in, in, in, out, out, out, listOfPeriods.get(i).getMaximumOfClients()));
        }
    }
}
