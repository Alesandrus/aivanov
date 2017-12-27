package ru.job4j.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс, обозначающий сущность - адрес.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class Address implements Serializable {
    /**
     * ID адреса.
     */
    private int id;

    /**
     * Город.
     */
    private String city;

    /**
     * Улица.
     */
    private String street;

    /**
     * Дом.
     */
    private int house;

    /**
     * Геттер для ID.
     *
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для ID.
     *
     * @param id адреса.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для города.
     *
     * @return город.
     */
    public String getCity() {
        return city;
    }

    /**
     * Сеттер для города.
     *
     * @param city город.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Геттер для улицы.
     *
     * @return улицу.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Сеттер для улицы.
     *
     * @param street улица.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Геттер для дома.
     *
     * @return дом.
     */
    public int getHouse() {
        return house;
    }

    /**
     * Сеттер для дома.
     *
     * @param house дом.
     */
    public void setHouse(int house) {
        this.house = house;
    }

    /**
     * Адреса равны если у них одинаковые значения города, улицы, дома.
     *
     * @param o другой объект.
     * @return true если условия равенства удовлетворены.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return house == address.house
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street);
    }

    /**
     * Получает хэшкод по городу, улице и дому.
     *
     * @return хэшкод.
     */
    @Override
    public int hashCode() {
        return Objects.hash(city, street, house);
    }
}
