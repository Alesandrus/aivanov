package ru.job4j.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность, обозначающая мотор автомобиля.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "motors")
public class Motor implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public Motor() {
    }

    /**
     * ID автомобильного двигателя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motor_id")
    private Long id;

    /**
     * Производитель двигателя.
     */
    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    /**
     * Мощность двигателя в л.с.
     */
    @Column(name = "power", nullable = false)
    private int power;

    /**
     * Объем двигателя в кубических сантиметрах.
     */
    @Column(name = "engine_volume", nullable = false)
    private int engineVolume;

    /**
     * Тип двигателя.
     */
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private MotorType motorType;

    /**
     * Множество автомобилей, в котрых установлен данный двигатель.
     */
    @OneToMany(mappedBy = "motor")
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
     * Getter power.
     *
     * @return power.
     */
    public int getPower() {
        return power;
    }

    /**
     * Setter power.
     *
     * @param power .
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Getter engineVolume.
     *
     * @return engineVolume.
     */
    public int getEngineVolume() {
        return engineVolume;
    }

    /**
     * Setter engineVolume.
     *
     * @param engineVolume .
     */
    public void setEngineVolume(int engineVolume) {
        this.engineVolume = engineVolume;
    }

    /**
     * Getter motorType.
     *
     * @return motorType.
     */
    public MotorType getMotorType() {
        return motorType;
    }

    /**
     * Setter motorType.
     *
     * @param motorType .
     */
    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
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
