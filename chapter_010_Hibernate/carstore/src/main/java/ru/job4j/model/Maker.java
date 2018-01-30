package ru.job4j.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность, обозначающая производителя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "makers")
public class Maker implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public Maker() {
    }

    /**
     * ID производителя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maker_id")
    private Long id;

    /**
     * Название производителя.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Множество трансмиссий от данного производителя.
     */
    @OneToMany(mappedBy = "maker")
    private Set<Transmission> transmissions = new HashSet<>();

    /**
     * Множество двигателей данного производителя.
     */
    @OneToMany(mappedBy = "maker")
    private Set<Motor> motors = new HashSet<>();

    /**
     * Множество кузовов данного производителя.
     */
    @OneToMany(mappedBy = "maker")
    private Set<Body> bodies = new HashSet<>();

    /**
     * Множество автомобилей данного производителей.
     */
    @OneToMany(mappedBy = "maker")
    private Set<Car> cars = new HashSet<>();

    /**
     * Getter id.
     *
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter id.
     *
     * @param id .
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter name.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name.
     *
     * @param name .
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter transmissions.
     *
     * @return transmissions.
     */
    public Set<Transmission> getTransmissions() {
        return transmissions;
    }

    /**
     * Getter motors.
     *
     * @return motors.
     */
    public Set<Motor> getMotors() {
        return motors;
    }

    /**
     * Getter bodies.
     *
     * @return bodies.
     */
    public Set<Body> getBodies() {
        return bodies;
    }

    /**
     * Getter cars.
     *
     * @return cars.
     */
    public Set<Car> getCars() {
        return cars;
    }
}
