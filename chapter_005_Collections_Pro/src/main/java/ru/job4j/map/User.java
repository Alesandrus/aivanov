package ru.job4j.map;

import java.util.*;

/**
 * Class User.
 *
 * @author Alexander Ivanov
 * @since 07.04.2017
 * @version 1.0
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar firstBirth = new GregorianCalendar(1900, 0, 10);
        Calendar secondBirth = new GregorianCalendar(1900, 0, 10);
        User firstUser = new User("Alex", 2, firstBirth);
        User secondUser = new User("Alex", 2, secondBirth);
        Map<User, Object> map = new HashMap<>();
        map.put(firstUser, null);
        map.put(secondUser, null);
        System.out.println(map);
    }
}
