package ru.job4j.sqlruparser;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Класс для запуска парсинга рубрики Вакансии сайта SQL.ru и сохранения в базу данных информации о вакансиях Java.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 11.10.2017
 */
public class JJStarter {

    /**
     * Запуск парсинга SQL.ru и сохранения полученной информации о вакансиях в базу данных.
     * Парсинг и сохранение производится с определенной частотой, которая извлекается из XML-файла jjhunter.xml.
     * Значение частоты, полученное из указанного файла измеряется в часах.
     */
    public void start() {
        JavaJobHunter hunter = new JavaJobHunter();
        long period = hunter.getPeriod();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(hunter, 0, period, TimeUnit.HOURS);
    }

    /**
     * Метод main().
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        JJStarter starter = new JJStarter();
        starter.start();
    }
}
