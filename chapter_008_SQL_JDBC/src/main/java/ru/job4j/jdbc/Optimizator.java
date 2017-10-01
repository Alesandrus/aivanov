package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class Optimizator.
 * Create new Table in database, getting data from this table and create 1.xml file in current directory.
 * Then parse 1.xml by XSLT and create 2.xml.
 * Then parse 2.xml and sum all integer fields.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 01.10.2017
 */
public class Optimizator {
    /**
     * Start application.
     *
     * @param args from CMD-line.
     */
    public static void main(String[] args) {
        int number;
        String url = "";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Укажите адрес базы данных либо оставьте адрес по умолчанию "
                    + "для этого введите пустую строку");
            String input = scanner.nextLine();
            if (!url.equals(input)) {
                url = input;
            }
            System.out.println("Введите число записей для вставки в базу данных");
            number = scanner.nextInt();
        }
        long start = System.currentTimeMillis();
        SQLTableCreator tableCreator = new SQLTableCreator();
        if (url.equals("")) {
            tableCreator.setUrl();
        } else {
            tableCreator.setUrl(url);
        }
        tableCreator.setMaxCount(number);

        XMLCreator xmlCreator = new XMLCreator();
        xmlCreator.setTableName("test");
        xmlCreator.setFieldName("field");
        XMLTransformer transformer = new XMLTransformer();
        XMLSumParser sumParser = new XMLSumParser();
        try (Connection con = tableCreator.getConnection()) {
            tableCreator.insertRows(con);
            xmlCreator.createXML(con);
            transformer.transform();
            long sum = sumParser.parseAndSum();
            System.out.println("Арифметическая сумма всех записей равна - " + sum);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        long finish = System.currentTimeMillis();
        long time = (finish - start) / 1000;
        System.out.println("Время выполнения программы - " + time + " сек");
    }
}
