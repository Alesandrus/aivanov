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
 * Сущность, обозначающая кузов автомобиля.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 30.01.2018
 */
@Entity
@Table(name = "bodies")
public class Body implements Serializable {
    /**
     * Конструктор по умолчанию.
     */
    public Body() {
    }

    /**
     * Id кузова.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "body_id")
    private Long id;

    /**
     * Производитель.
     */
    @ManyToOne
    @JoinColumn(name = "maker_id", nullable = false)
    private Maker maker;

    /**
     * Вид кузова.
     */
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private BodyType bodyType;

    /**
     * Множество машин, сконструированных из данного кузова.
     */
    @OneToMany(mappedBy = "body")
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
     * Getter bodyType.
     *
     * @return bodyType.
     */
    public BodyType getBodyType() {
        return bodyType;
    }

    /**
     * Setter bodyType.
     *
     * @param bodyType .
     */
    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    /**
     * Getter cara.
     *
     * @return cars.
     */
    public Set<Car> getCars() {
        return cars;
    }
}
