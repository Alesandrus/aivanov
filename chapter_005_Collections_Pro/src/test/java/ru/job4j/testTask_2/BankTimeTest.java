package ru.job4j.testTask_2;

import com.google.common.base.Joiner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test BankTime.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 26.04.2017
 */
public class BankTimeTest {
    /**
     * output for test.
     */
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    /**
     * method for setting stream.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * method for cleaning stream.
     */
    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    /**
     * Test when max period is 14:50-15:15 and max number of clients is 3.
     */
    @Test
    public void whenOnePeriodWithMaxNumber() {
        Calendar incomeTimeOfFirstClient = new GregorianCalendar(2017, 5, 26, 9, 0);
        Calendar outcomeTimeOfFirstClient = new GregorianCalendar(2017, 5, 26, 9, 20);
        Client firstClient = new Client(incomeTimeOfFirstClient.getTimeInMillis());
        firstClient.setOutcome(outcomeTimeOfFirstClient.getTimeInMillis());

        Calendar incomeTimeOfSecondClient = new GregorianCalendar(2017, 5, 26, 9, 30);
        Calendar outcomeTimeOfSecondClient = new GregorianCalendar(2017, 5, 26, 15, 50);
        Client secondClient = new Client(incomeTimeOfSecondClient.getTimeInMillis());
        secondClient.setOutcome(outcomeTimeOfSecondClient.getTimeInMillis());

        Calendar incomeTimeOfThirdClient = new GregorianCalendar(2017, 5, 26, 9, 35);
        Calendar outcomeTimeOfThirdClient = new GregorianCalendar(2017, 5, 26, 9, 40);
        Client thirdClient = new Client(incomeTimeOfThirdClient.getTimeInMillis());
        thirdClient.setOutcome(outcomeTimeOfThirdClient.getTimeInMillis());

        Calendar incomeTimeOfFourthClient = new GregorianCalendar(2017, 5, 26, 13, 0);
        Calendar outcomeTimeOfFourthClient = new GregorianCalendar(2017, 5, 26, 15, 15);
        Client fourthClient = new Client(incomeTimeOfFourthClient.getTimeInMillis());
        fourthClient.setOutcome(outcomeTimeOfFourthClient.getTimeInMillis());

        Calendar incomeTimeOfFifthClient = new GregorianCalendar(2017, 5, 26, 14, 50);
        Calendar outcomeTimeOfFifthClient = new GregorianCalendar(2017, 5, 26, 15, 40);
        Client fifthClient = new Client(incomeTimeOfFifthClient.getTimeInMillis());
        fifthClient.setOutcome(outcomeTimeOfFifthClient.getTimeInMillis());

        Calendar incomeTimeOfSixthClient = new GregorianCalendar(2017, 5, 26, 15, 40);
        Calendar outcomeTimeOfSixthClient = new GregorianCalendar(2017, 5, 26, 16, 0);
        Client sixthClient = new Client(incomeTimeOfSixthClient.getTimeInMillis());
        sixthClient.setOutcome(outcomeTimeOfSixthClient.getTimeInMillis());

        Calendar incomeTimeOfSeventhClient = new GregorianCalendar(2017, 5, 26, 18, 0);
        Calendar outcomeTimeOfSeventhClient = new GregorianCalendar(2017, 5, 26, 19, 50);
        Client seventhClient = new Client(incomeTimeOfSeventhClient.getTimeInMillis());
        seventhClient.setOutcome(outcomeTimeOfSeventhClient.getTimeInMillis());

        Calendar openingTime = new GregorianCalendar(2017, 5, 26, 8, 0);
        Calendar closingTime = new GregorianCalendar(2017, 5, 26, 20, 00);
        BankTime bankTime = new BankTime(openingTime, closingTime);
        bankTime.addClient(firstClient);
        bankTime.addClient(secondClient);
        bankTime.addClient(thirdClient);
        bankTime.addClient(fourthClient);
        bankTime.addClient(fifthClient);
        bankTime.addClient(sixthClient);
        bankTime.addClient(seventhClient);

        bankTime.showMaxPeriods();
        final String resultPart1 = "Максимальное количество людей было";
        final String resultPart2 = "С 14:50:00 по 15:15:00 - 3 клиентов";
        Joiner joiner = Joiner.on(System.lineSeparator());
        final String result = joiner.join(resultPart1, resultPart2, "");
        assertThat(output.toString(), is(result));
    }

    /**
     * Test when max period is 9:40-10:10 - 2 clients, 14:51:27-15:12:18 - 2 clients and 18:18-19:30 - 2 clients.
     */
    @Test
    public void whenThreePeriodsWithMaxNumber() {
        Calendar incomeTimeOfFirstClient = new GregorianCalendar(2017, 5, 26, 9, 0);
        Calendar outcomeTimeOfFirstClient = new GregorianCalendar(2017, 5, 26, 10, 10);
        Client firstClient = new Client(incomeTimeOfFirstClient.getTimeInMillis());
        firstClient.setOutcome(outcomeTimeOfFirstClient.getTimeInMillis());

        Calendar incomeTimeOfSecondClient = new GregorianCalendar(2017, 5, 26, 9, 40);
        Calendar outcomeTimeOfSecondClient = new GregorianCalendar(2017, 5, 26, 10, 25);
        Client secondClient = new Client(incomeTimeOfSecondClient.getTimeInMillis());
        secondClient.setOutcome(outcomeTimeOfSecondClient.getTimeInMillis());

        Calendar incomeTimeOfThirdClient = new GregorianCalendar(2017, 5, 26, 10, 35);
        Calendar outcomeTimeOfThirdClient = new GregorianCalendar(2017, 5, 26, 11, 40);
        Client thirdClient = new Client(incomeTimeOfThirdClient.getTimeInMillis());
        thirdClient.setOutcome(outcomeTimeOfThirdClient.getTimeInMillis());

        Calendar incomeTimeOfFourthClient = new GregorianCalendar(2017, 5, 26, 14, 30);
        Calendar outcomeTimeOfFourthClient = new GregorianCalendar(2017, 5, 26, 15, 12, 18);
        Client fourthClient = new Client(incomeTimeOfFourthClient.getTimeInMillis());
        fourthClient.setOutcome(outcomeTimeOfFourthClient.getTimeInMillis());

        Calendar incomeTimeOfFifthClient = new GregorianCalendar(2017, 5, 26, 14, 51, 27);
        Calendar outcomeTimeOfFifthClient = new GregorianCalendar(2017, 5, 26, 16, 0);
        Client fifthClient = new Client(incomeTimeOfFifthClient.getTimeInMillis());
        fifthClient.setOutcome(outcomeTimeOfFifthClient.getTimeInMillis());

        Calendar incomeTimeOfSixthClient = new GregorianCalendar(2017, 5, 26, 17, 0);
        Calendar outcomeTimeOfSixthClient = new GregorianCalendar(2017, 5, 26, 20, 0);
        Client sixthClient = new Client(incomeTimeOfSixthClient.getTimeInMillis());
        sixthClient.setOutcome(outcomeTimeOfSixthClient.getTimeInMillis());

        Calendar incomeTimeOfSeventhClient = new GregorianCalendar(2017, 5, 26, 18, 18);
        Calendar outcomeTimeOfSeventhClient = new GregorianCalendar(2017, 5, 26, 19, 30);
        Client seventhClient = new Client(incomeTimeOfSeventhClient.getTimeInMillis());
        seventhClient.setOutcome(outcomeTimeOfSeventhClient.getTimeInMillis());

        Calendar openingTime = new GregorianCalendar(2017, 5, 26, 8, 0);
        Calendar closingTime = new GregorianCalendar(2017, 5, 26, 20, 00);
        BankTime bankTime = new BankTime(openingTime, closingTime);
        bankTime.addClient(firstClient);
        bankTime.addClient(secondClient);
        bankTime.addClient(thirdClient);
        bankTime.addClient(fourthClient);
        bankTime.addClient(fifthClient);
        bankTime.addClient(sixthClient);
        bankTime.addClient(seventhClient);

        bankTime.showMaxPeriods();
        final String resultPart1 = "Максимальное количество людей было";
        final String resultPart2 = "С 09:40:00 по 10:10:00 - 2 клиентов";
        final String resultPart3 = "С 14:51:27 по 15:12:18 - 2 клиентов";
        final String resultPart4 = "С 18:18:00 по 19:30:00 - 2 клиентов";
        Joiner joiner = Joiner.on(System.lineSeparator());
        final String result = joiner.join(resultPart1, resultPart2, resultPart3, resultPart4, "");
        assertThat(output.toString(), is(result));
    }
}