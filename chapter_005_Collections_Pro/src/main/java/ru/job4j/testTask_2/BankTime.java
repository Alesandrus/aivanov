package ru.job4j.testTask_2;

import java.util.*;

/**
 * Created by Ivanov_ab on 26.04.2017.
 */
public class BankTime {
    private long opening;
    private long closing;
    private List<Client> clientList = new ArrayList<>();
    private List<MaxClientsPeriod> listOfMaxPeriods = new ArrayList<>();

    public BankTime(Calendar opening, Calendar closing) {
        this.opening = opening.getTimeInMillis();
        this.closing = closing.getTimeInMillis();
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public long getMinTimeBeing() {
        long minTimePeiod = closing - opening;
        for (int i = 0; i < clientList.size(); i++) {
            long outFirst = clientList.get(i).getOutcome();
            for (int j = i + 1; j < clientList.size(); j++) {
                long inSecond = clientList.get(j).getIncome();
                long outSecond = clientList.get(j).getOutcome();
                if (inSecond <= outFirst) {
                    long out;
                    if (outSecond > outFirst) {
                        out = outFirst;
                    } else {
                        out = outSecond;
                    }
                    long period = out - inSecond;
                    minTimePeiod = minTimePeiod > period ? period : minTimePeiod;
                }
            }
        }
        return minTimePeiod;
    }

    private long getRoundedClosingTimeForIterate(long minTimePeriod) {
        long diferenceCloseAndOpen = closing - opening;
        long factor = (long) Math.ceil(diferenceCloseAndOpen / minTimePeriod);
        return opening + minTimePeriod * factor;
    }

    private void addToListOfMaxPeriods() {
        long minTimePeriod = getMinTimeBeing();
        long roundedClosing = getRoundedClosingTimeForIterate(minTimePeriod);
        int maxClients = 0;
        for (long i = opening; i <= roundedClosing - minTimePeriod; i += minTimePeriod) {
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

    public static void main(String[] args) {
        Calendar incomeTimeOfFirstClient = new GregorianCalendar(2017, 5, 26, 9,0);
        Calendar outcomeTimeOfFirstClient = new GregorianCalendar(2017, 5, 26, 9,50);
        Client firstClient = new Client(incomeTimeOfFirstClient.getTimeInMillis());
        firstClient.setOutcome(outcomeTimeOfFirstClient.getTimeInMillis());

        Calendar incomeTimeOfSecondClient = new GregorianCalendar(2017, 5, 26, 9,30);
        Calendar outcomeTimeOfSecondClient = new GregorianCalendar(2017, 5, 26, 15,50);
        Client secondClient = new Client(incomeTimeOfSecondClient.getTimeInMillis());
        secondClient.setOutcome(outcomeTimeOfSecondClient.getTimeInMillis());

        Calendar incomeTimeOfThirdClient = new GregorianCalendar(2017, 5, 26, 9,40);
        Calendar outcomeTimeOfThirdClient = new GregorianCalendar(2017, 5, 26, 13,30);
        Client thirdClient = new Client(incomeTimeOfThirdClient.getTimeInMillis());
        thirdClient.setOutcome(outcomeTimeOfThirdClient.getTimeInMillis());

        Calendar incomeTimeOfFourthClient = new GregorianCalendar(2017, 5, 26, 12,0);
        Calendar outcomeTimeOfFourthClient = new GregorianCalendar(2017, 5, 26, 13,50);
        Client fourthClient = new Client(incomeTimeOfFourthClient.getTimeInMillis());
        fourthClient.setOutcome(outcomeTimeOfFourthClient.getTimeInMillis());

        Calendar incomeTimeOfFifthClient = new GregorianCalendar(2017, 5, 26, 13,0);
        Calendar outcomeTimeOfFifthClient = new GregorianCalendar(2017, 5, 26, 13,30);
        Client fifthClient = new Client(incomeTimeOfFifthClient.getTimeInMillis());
        fifthClient.setOutcome(outcomeTimeOfFifthClient.getTimeInMillis());

        Calendar incomeTimeOfSixthClient = new GregorianCalendar(2017, 5, 26, 13,10);
        Calendar outcomeTimeOfSixthClient = new GregorianCalendar(2017, 5, 26, 16,00);
        Client sixthClient = new Client(incomeTimeOfSixthClient.getTimeInMillis());
        sixthClient.setOutcome(outcomeTimeOfSixthClient.getTimeInMillis());

        Calendar incomeTimeOfSeventhClient = new GregorianCalendar(2017, 5, 26, 14,0);
        Calendar outcomeTimeOfSeventhClient = new GregorianCalendar(2017, 5, 26, 18,50);
        Client seventhClient = new Client(incomeTimeOfSeventhClient.getTimeInMillis());
        seventhClient.setOutcome(outcomeTimeOfSeventhClient.getTimeInMillis());

        Calendar openingTime = new GregorianCalendar(2017, 5, 26, 8,0);
        Calendar closingTime = new GregorianCalendar(2017, 5, 26, 20,00);
        BankTime bankTime = new BankTime(openingTime, closingTime);
        bankTime.addClient(firstClient);
        bankTime.addClient(secondClient);
        bankTime.addClient(thirdClient);
        bankTime.addClient(fourthClient);
        bankTime.addClient(fifthClient);
        bankTime.addClient(sixthClient);
        bankTime.addClient(seventhClient);

        bankTime.showMaxPeriods();
    }
}
