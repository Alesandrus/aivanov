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

@Entity
@Table(name = "transmissions")
public class Transmission implements Serializable {
    public Transmission() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    @Column(name = "number_of_speed", nullable = false)
    private int numberOfSpeed;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TransmissionType transmissionType;

    @OneToMany(mappedBy = "transmission")
    private Set<Car> cars = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public int getNumberOfSpeed() {
        return numberOfSpeed;
    }

    public void setNumberOfSpeed(int numberOfSpeed) {
        this.numberOfSpeed = numberOfSpeed;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Set<Car> getCars() {
        return cars;
    }
}
