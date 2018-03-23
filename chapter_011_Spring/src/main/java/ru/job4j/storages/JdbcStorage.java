package ru.job4j.storages;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.job4j.interfaces.Storage;
import ru.job4j.models.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Implementation of storage. Store user into database.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class JdbcStorage implements Storage {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * DataSource.
     */
    private BasicDataSource dataSource;

    /**
     * Constructor. Connecting to database.
     */
    @Autowired
    public JdbcStorage() {
        connectDB();
    }

    /**
     * Add user to database.
     *
     * @param user .
     */
    @Override
    public void add(User user) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO users (name, surname) VALUES (?, ?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Get all users from database.
     *
     * @return all users.
     */
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            users = getAllFromResultSet(resultSet);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return users;
    }

    /**
     * Removes the first occurrence of user from database.
     *
     * @param user .
     * @return true if user is removed, false if storage not contain user.
     */
    @Override
    public boolean remove(User user) {
        boolean isRemoved = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM users WHERE name = ? AND surname = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                rs.deleteRow();
                isRemoved = true;
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                LOGGER.error(e.getMessage(), e);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return isRemoved;
    }

    /**
     * Connect to database.
     */
    private void connectDB() {
        Properties dataBaseProperties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            dataBaseProperties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        String driver = dataBaseProperties.getProperty("driver");
        String url = dataBaseProperties.getProperty("url");
        String user = dataBaseProperties.getProperty("user");
        String password = dataBaseProperties.getProperty("password");
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    /**
     * Get all users from resultset.
     *
     * @param resultSet .
     * @return List of users.
     * @throws SQLException .
     */
    private List<User> getAllFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User(resultSet.getString("name"), resultSet.getString("surname"));
            users.add(user);
        }
        return users;
    }
}
