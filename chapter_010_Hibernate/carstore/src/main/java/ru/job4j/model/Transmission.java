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
 * Сущность, обозначающая трансмиссию двигателя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "transmissions")
public class Transmission implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public Transmission() {
    }

    /**
     * ID трансмиссии.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private Long id;

    /**
     * Производитель трансмиссии.
     */
    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    /**
     * Количество скоростей трансмиссии.
     */
    @Column(name = "number_of_speed", nullable = false)
    private int numberOfSpeed;

    /**
     * Тип трансмиссии.
     */
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TransmissionType transmissionType;

    /**
     * Множество автомобилей, в которых установлена данная трансмиссия.
     */
    @OneToMany(mappedBy = "transmission")
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
     * Getter numberOfSpeed.
     *
     * @return numberOfSpeed.
     */
    public int getNumberOfSpeed() {
        return numberOfSpeed;
    }

    /**
     * Setter numberOfSpeed.
     *
     * @param numberOfSpeed .
     */
    public void setNumberOfSpeed(int numberOfSpeed) {
        this.numberOfSpeed = numberOfSpeed;
    }

    /**
     * Getter transmissionType.
     *
     * @return transmissionType.
     */
    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    /**
     * Setter transmissionType.
     *
     * @param transmissionType .
     */
    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
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
