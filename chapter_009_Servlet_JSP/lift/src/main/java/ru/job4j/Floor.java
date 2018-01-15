package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Этаж дома.
 */
public class Floor {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Высота этажа.
     */
    private double height;

    /**
     * Конструктор этажа.
     *
     * @param height высота.
     */
    public Floor(double height) {
        this.height = height;
    }

    /**
     * Getter для высоты.
     *
     * @return высоту.
     */
    public double getHeight() {
        return height;
    }
}
