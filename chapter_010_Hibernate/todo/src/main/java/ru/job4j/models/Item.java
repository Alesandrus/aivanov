package ru.job4j.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Сущность, представляющая задание.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 24.01.2018
 */
@Entity
@Table(name = "items")
public class Item {
    /**
     * Id задания.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Описание задания.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Время создания задания.
     */
    @Column(name = "creation_time", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Timestamp created;

    /**
     * Флаг выполнения задания.
     */
    @Column(name = "done")
    private boolean done;

    /**
     * Геттер id.
     *
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * Сеттер id.
     *
     * @param id id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Геттер описания.
     *
     * @return описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Сеттер для описания.
     *
     * @param description описание.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Геттер для времени создания.
     *
     * @return время создания.
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Сеттер для времени создания.
     *
     * @param created время создания.
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Геттер выполнения.
     *
     * @return true если выполнено.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Сеттер для выполнения.
     *
     * @param done выполнения задания.
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}
