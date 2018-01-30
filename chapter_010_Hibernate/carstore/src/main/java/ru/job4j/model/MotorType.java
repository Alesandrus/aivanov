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
 * Сущность, обозначающая тип двигателя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "motor_types")
public class MotorType implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public MotorType() {
    }

    /**
     * ID типа двигателя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    /**
     * Название типа двигателя.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Множество двигателей одного типа.
     */
    @OneToMany(mappedBy = "motorType")
    Set<Motor> motors = new HashSet<>();

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
     * Getter motors.
     *
     * @return motors.
     */
    public Set<Motor> getMotors() {
        return motors;
    }
}
