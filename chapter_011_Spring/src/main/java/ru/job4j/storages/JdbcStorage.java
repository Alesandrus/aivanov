package ru.job4j.storages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.interfaces.Storage;
import ru.job4j.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Spring Jdbc Template.
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Constructor.
     */
    public JdbcStorage() {
    }

    /**
     * Setter for jdbcTemplate.
     *
     * @param jdbcTemplate .
     */
    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Add user to database.
     *
     * @param user .
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(User user) {
        String sql = "INSERT INTO users (name, surname) VALUES (:name, :surname)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("surname", user.getSurname());
        jdbcTemplate.update(sql, parameters);
    }

    /**
     * Get all users from database.
     *
     * @return all users.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                (resultSet, rowNum) ->
                        new User(resultSet.getString("name"),
                                resultSet.getString("surname")));
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
            connection = jdbcTemplate.getJdbcTemplate().getDataSource().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
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
}
