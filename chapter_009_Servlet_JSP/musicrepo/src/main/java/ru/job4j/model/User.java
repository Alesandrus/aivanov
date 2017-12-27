package ru.job4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс, обозначающий сущность - пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class User implements Serializable {
    /**
     * ID пользователя.
     */
    private int id;

    /**
     * Логин пользователя.
     */
    private String login;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Роль пользователя.
     */
    private Role role;

    /**
     * Адрес пользователя.
     */
    private Address address;

    /**
     * Список музыкальных типов пользователя.
     */
    private List<MusicType> musicTypes = new ArrayList<>();

    /**
     * Геттер для ID пользоватедя.
     *
     * @return ID пользователя.
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер для ID пользователя.
     *
     * @param id пользователя.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Геттер для логина пользователя.
     *
     * @return логин.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Сеттер для логина пользователя.
     *
     * @param login пользователя.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Геттер для пароля пользователя.
     *
     * @return пароль.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер для пароля пользователя.
     *
     * @param password пользователя.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Геттер для имени пользователя.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для имени пользователя.
     *
     * @param name пользователя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для роли польщзователя.
     *
     * @return роль.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Сеттер для роли пользователя.
     *
     * @param role пользователя.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Геттер для адреса пользователя.
     *
     * @return адрес.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Сеттер для адреса пользователя.
     *
     * @param address пользователя.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Геттер для списка музкальных типов пользователя.
     *
     * @return список музыкальных типов.
     */
    public List<MusicType> getMusicTypes() {
        return musicTypes;
    }

    /**
     * Сеттер для списка музыкальных типов пользователя.
     *
     * @param musicTypes список музыкальных типов пользователя.
     */
    public void setMusicTypes(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }

    /**
     * Оценивает пользователей. Равны если логины одинаковые.
     *
     * @param o другой объект.
     * @return true если условия равенства удовлетворены.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    /**
     * Получает хэшкод по логину.
     *
     * @return хэшкод.
     */
    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
