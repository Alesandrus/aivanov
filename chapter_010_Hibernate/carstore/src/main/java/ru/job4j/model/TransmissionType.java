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
 * Сущность, обозначающая тип трансмиссии.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "transmission_types")
public class TransmissionType implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public TransmissionType() {
    }

    /**
     * ID типа трансмиссии.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    /**
     * Название типа трансмиссии.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Множество трансмиссий данного типа.
     */
    @OneToMany(mappedBy = "transmissionType")
    private Set<Transmission> transmissions = new HashSet<>();

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
     * Setter name.
     *
     * @return name.
     */
    public Set<Transmission> getTransmissions() {
        return transmissions;
    }
}
