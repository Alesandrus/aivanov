package ru.job4j.storages;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;

import static org.junit.Assert.*;

/**
 * MemoryStorage test.
 */
public class MemoryStorageTest {

    /**
     * Test add user, getAll and remove.
     */
    @Test
    public void whenAddUserToMemoryStorageThenGetItAndWhenRemoveUserThenNotGetIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memoryStorage = context.getBean(MemoryStorage.class);
        User user = new User("TestName", "TestSurname");

        memoryStorage.add(user);
        assertTrue(memoryStorage.getAll().contains(user));

        memoryStorage.remove(user);
        assertFalse(memoryStorage.getAll().contains(user));
    }
}