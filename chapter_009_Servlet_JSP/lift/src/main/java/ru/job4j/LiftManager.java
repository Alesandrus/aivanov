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
        LiftManager manager = new LiftManager();
        manager.start(args);
    }

    /**
     * Запуск лифта. Для выхода нужнно ввести "exit". Для вызова снаружи - "out".
     * Для нажатия кнопки внутри лифта - "in".
     *
     * @param args 1-й аругмент - количество этажей в доме. 2-й аргумент - высота этажа.
     *             3-й аргумент - скорость лифта. 4-й агрумент - время между открытием и закрытием дверей.
     */
    private void start(String[] args) {
        if (args.length == 4) {
            int numberOfFloors = Integer.parseInt(args[0]);
            Floor[] floors = new Floor[numberOfFloors];
            double height = Double.parseDouble(args[1]);
            for (int i = 0; i < numberOfFloors; i++) {
                floors[i] = new Floor(height);
            }

            double speedMPS = Double.parseDouble(args[2]);
            double closeTime = Double.parseDouble(args[3]);
            Lift lift = new Lift(speedMPS, closeTime);
            lift.setFloors(floors);

            House house = new House(floors);
            house.setLift(lift);

            lift.start();

            Scanner scanner = new Scanner(System.in);
            String command = "";
            while (!command.equals("exit")) {
                System.out.println("Для нажатия кнопки в подъезде введите out");
                System.out.println("Для нажатия кнопки внутри лифта введите in");
                System.out.println("Для выхода введите exit");
                command = scanner.nextLine();
                if (command.equals("exit")) {
                    lift.stop();
                }

                if (command.equals("out")) {
                    System.out.println("На каком этаже вы находитесь? Введите этаж от 1 до " + numberOfFloors);
                    System.out.println("Для выхода введите exit");
                    command = scanner.nextLine();
                    try {
                        int level = Integer.parseInt(command);
                        if (level < 1 || level > numberOfFloors) {
                            LOGGER.info("Этаж надо ввести от 1 до " + numberOfFloors);
                        } else {
                            lift.call(level);
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.info("Введите целочисленное значение");
                    }
                } else if (command.equals("in")) {
                    System.out.println("На какой этаж едем? Введите этаж от 1 до " + numberOfFloors);
                    System.out.println("Для выхода введите exit");
                    command = scanner.nextLine();
                    try {
                        int level = Integer.parseInt(command);
                        if (level < 1 || level > numberOfFloors) {
                            LOGGER.info("Этаж надо ввести от 1 до " + numberOfFloors);
                        } else {
                            lift.goTo(level);
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.info("Введите целочисленное значение");
                    }
                } else if (!command.equals("exit")) {
                    LOGGER.info("Вы куда-то не туда жмете");
                }
            }
        }
    }
}
