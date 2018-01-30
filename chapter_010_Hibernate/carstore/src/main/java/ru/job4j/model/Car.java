package ru.job4j.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Сущность, обозначающая автомобиль.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public Car() {
    }

    /**
     * ID автомобиля.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    /**
     * Название автомобиля.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Производитель автомобиля.
     */
    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    /**
     * Трансмиссия автомобиля.
     */
    @ManyToOne
    @JoinColumn(name = "trans_id", nullable = false)
    private Transmission transmission;

    /**
     * Двигатель автомобиля.
     */
    @ManyToOne
    @JoinColumn(name = "motor_id", nullable = false)
    private Motor motor;

    /**
     * Кузов автомобиля.
     */
    @ManyToOne
    @JoinColumn(name = "body_id", nullable = false)
    private Body body;

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
     * Getter maker.
     *
     * @return maker.
     */
    public Maker getMaker() {
        return maker;
    }

    /**
     * Setter maker.
     *
     * @param maker .
     */
    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    /**
     * Getter transmission.
     * @return transmission.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Setter transmission.
     * @param transmission .
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Getter motor.
     * @return motor.
     */
    public Motor getMotor() {
        return motor;
    }

    /**
     * Setter motor.
     * @param motor .
     */
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    /**
     * Getter body.
     * @return body.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Setter body.

     * @param body .
     */
    public void setBody(Body body) {
        this.body = body;
    }
}
