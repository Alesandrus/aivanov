package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Класс для запуск лифта.
 */
public class LiftManager {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Main method.
     *
     * @param args from cmd. 1-й аругмент - количество этажей в доме. 2-й аргумент - высота этажа.
     *             3-й аргумент - скорость лифта. 4-й агрумент - время между открытием и закрытием дверей.
     */
    public static void main(String[] args) {
        if (args.length == 4) {
            Floor[] floors = new Floor[Integer.parseInt(args[0])];
            for (int i = 0; i < floors.length; i++) {
                floors[i] = new Floor(Double.parseDouble(args[1]));
            }

            double speedMPS = Double.parseDouble(args[2]);
            double closeTime = Double.parseDouble(args[3]);
            Lift lift = new Lift(speedMPS, closeTime);

            House house = new House(floors);
            house.setLift(lift);

            LiftManager manager = new LiftManager();
            manager.start(house);
        }
    }

    /**
     * Запуск лифта. Для выхода нужнно ввести "exit". Для вызова снаружи - "out".
     * Для нажатия кнопки внутри лифта - "in".
     *
     * @param house дом с лифтом.
     */
    public void start(House house) {
        Lift lift = house.getLift();
        if (lift == null) {
            LOGGER.error("Лифт в доме не установлен!");
            return;
        }
        lift.start();
        int numberOfFloors = house.getFloors().length;

        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!command.equals("exit")) {
            System.out.println("Для нажатия кнопки в подъезде введите out");
            System.out.println("Для нажатия кнопки внутри лифта введите in");
            System.out.println("Для выхода введите exit");
            command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            if (command.equals("out")) {
                System.out.println("На каком этаже вы находитесь? Введите этаж от 1 до " + numberOfFloors);
                command = scanner.nextLine();
                outCall(lift, command, numberOfFloors);
            } else if (command.equals("in")) {
                System.out.println("На какой этаж нужно попасть? Введите этаж от 1 до " + numberOfFloors);
                command = scanner.nextLine();
                inCall(lift, command, numberOfFloors);
            } else if (!command.equals("exit")) {
                LOGGER.info("Ошибка ввода");
            }
        }
        lift.stop();
    }

    /**
     * Обработка вызова лифта из подъезда.
     *
     * @param lift   лифт.
     * @param floor  введенный этаж.
     * @param floors количество этажей в доме.
     */
    private void outCall(Lift lift, String floor, int floors) {
        try {
            int level = Integer.parseInt(floor);
            if (level < 1 || level > floors) {
                LOGGER.info("Этаж надо ввести от 1 до " + floors);
            } else {
                lift.call(level);
            }
        } catch (NumberFormatException e) {
            LOGGER.info("Введите целочисленное значение");
        }
    }

    /**
     * Обработка нажатия кнокпи внутри лифта.
     *
     * @param lift   лифт.
     * @param floor  введенный этаж.
     * @param floors количество этажей в доме.
     */
    private void inCall(Lift lift, String floor, int floors) {
        try {
            int level = Integer.parseInt(floor);
            if (level < 1 || level > floors) {
                LOGGER.info("Этаж надо ввести от 1 до " + floors);
            } else {
                lift.goTo(level);
            }
        } catch (NumberFormatException e) {
            LOGGER.info("Введите целочисленное значение");
        }
    }
}
