package ru.job4j.sqlruparser;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JJStarter {

    public void start() {
        JavaJobHunter hunter = new JavaJobHunter();
        long period = hunter.getPeriod();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(hunter, 0, period, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        JJStarter starter = new JJStarter();
        starter.start();
    }
}
