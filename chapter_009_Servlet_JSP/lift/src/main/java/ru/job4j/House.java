package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Дом, состоящий из этажей и лифта.
 */
public class House {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Этажи.
     */
    private Floor[] floors;

    /**
     * Лифт.
     */
    private Lift lift;

    /**
     * Конструктор для дома.
     *
     * @param floors этажи.
     */
    public House(Floor[] floors) {
        this.floors = floors;
    }

    /**
     * Установка лифта в дом.
     *
     * @param lift лифт.
     */
    public void setLift(Lift lift) {
        this.lift = lift;
        lift.setFloors(floors);
    }

    /**
     * Получить ссылку на лифт.
     *
     * @return лифт.
     */
    public Lift getLift() {
        return lift;
    }

    /**
     * Получить этажи дома.
     *
     * @return этажи.
     */
    public Floor[] getFloors() {
        return floors;
    }
}
