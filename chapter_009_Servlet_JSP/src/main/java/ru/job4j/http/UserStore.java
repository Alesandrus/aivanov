package ru.job4j.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class UserStore {
    private static final UserStore store = new UserStore();
    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    private UserStore() {
    }

    public static UserStore getInstance() {
        return store;
    }

    public synchronized void setConnection(Connection connection) {
        this.connection = connection;
    }

    public synchronized boolean addUser(String name, String login, String email) {
        int addResult = 0;
        try (PreparedStatement statement = connection
                .prepareStatement("INSERT INTO users (name, login, email) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, email);
            addResult = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return addResult != 0;
    }

    /**
     * Если запись о пользователе не имеется то вернет -1, если есть, но не получилось добавить вернет 0,
     * если есть и запись добавлена вернет 1, -2 если все ячейки пустые
     *
     * @return
     */
    public synchronized int updateUser(String login, String newName, String newLogin, String newEmail) {
        int updateResult = 0;
        try (PreparedStatement fullStatement = connection
                .prepareStatement("UPDATE users SET name = ?, login = ?, email = ?"
                        + "WHERE login = ?");
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (newName.isEmpty() && newLogin.isEmpty() && newEmail.isEmpty()) {
                    updateResult = -2;
                } else {
                    if (newName.isEmpty()) {
                        newName = resultSet.getString("name");
                    }
                    if (newLogin.isEmpty()) {
                        newLogin = login;
                    }
                    if (newEmail.isEmpty()) {
                        newEmail = resultSet.getString("email");
                    }
                    fullStatement.setString(1, newName);
                    fullStatement.setString(2, newLogin);
                    fullStatement.setString(3, newEmail);
                    fullStatement.setString(4, login);
                    updateResult = fullStatement.executeUpdate();
                    connection.commit();
                }
            } else {
                updateResult = -1;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                LOGGER.info("Сбой при обновлении пользователя. Попытка откатиться и снова его обновить");
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.info("Откат не удался");
                LOGGER.error(e1.getMessage(), e1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return updateResult;
    }

    public synchronized int deleteUser(String login) {
        int deleteResult = 0;
        try (PreparedStatement statement = connection
                .prepareStatement("DELETE FROM users WHERE login = ?")) {
            connection.setAutoCommit(false);
            if (validateUser(login)) {
                statement.setString(1, login);
                deleteResult = statement.executeUpdate();
                connection.commit();
            } else {
                deleteResult = -1;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                LOGGER.info("Сбой при удалении пользователя. Попытка откатиться и снова его удалить");
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.info("Откат не удался");
                LOGGER.error(e1.getMessage(), e1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return deleteResult;
    }

    /**
     * Если пользователя нет в БД, то возвращает user с установленным полем exist в false
     *
     * @return
     */
    public synchronized User getUser(String login) {
        User user = new User();
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(resultSet.getTimestamp("createdate").getTime());
                user.setCreateDate(date);
            } else {
                user.setExist(false);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    private synchronized boolean validateUser(String login) {
        boolean check = false;
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT * FROM users WHERE login = ?")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return check;
    }
}
