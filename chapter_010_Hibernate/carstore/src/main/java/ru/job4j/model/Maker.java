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

@Entity
@Table(name = "makers")
public class Maker implements Serializable {
    public Maker() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maker_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "maker")
    private Set<Transmission> transmissions = new HashSet<>();

    @OneToMany(mappedBy = "maker")
    private Set<Motor> motors = new HashSet<>();

    @OneToMany(mappedBy = "maker")
    private Set<Body> bodies = new HashSet<>();

    @OneToMany(mappedBy = "maker")
    private Set<Car> cars = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transmission> getTransmissions() {
        return transmissions;
    }

    public Set<Motor> getMotors() {
        return motors;
    }

    public Set<Body> getBodies() {
        return bodies;
    }

    public Set<Car> getCars() {
        return cars;
    }
}
