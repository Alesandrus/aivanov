package ru.job4j.dao.daofactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.daopostgres.PostgresAddressDAO;
import ru.job4j.dao.daopostgres.PostgresMusicTypeDAO;
import ru.job4j.dao.daopostgres.PostgresRoleDAO;
import ru.job4j.dao.daopostgres.PostgresUserDAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ДАО-фабрика для работы с базой данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class PostgresDAOFactory extends DAOFactory {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Пул соединенний с БД.
     */
    private BasicDataSource dataSource;

    /**
     * Синглтон-переменная.
     */
    private static final PostgresDAOFactory FACTORY = new PostgresDAOFactory();

    /**
     * Приватный конструктор.
     */
    private PostgresDAOFactory() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return PostgresDAOFactory.
     */
    public static PostgresDAOFactory getInstance() {
        return FACTORY;
    }

    /**
     * Получение DAO для адреса.
     *
     * @return PostgresAddressDAO.
     */
    @Override
    public PostgresAddressDAO getAddressDAO() {
        return new PostgresAddressDAO();
    }

    /**
     * Получение DAO для музыкального типа.
     *
     * @return PostgresMusicTypeDAO.
     */
    @Override
    public PostgresMusicTypeDAO getMusicTypeDAO() {
        return new PostgresMusicTypeDAO();
    }

    /**
     * Получение DAO для роли.
     *
     * @return PostgresRoleDAO.
     */
    @Override
    public PostgresRoleDAO getRoleDAO() {
        return new PostgresRoleDAO();
    }

    /**
     * Получение DAO для пользователя.
     *
     * @return PostgresUserDAO.
     */
    @Override
    public PostgresUserDAO getUserDAO() {
        return new PostgresUserDAO();
    }

    /**
     * Соединение с базой данных. Используется пул соединений.
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
     * Получение соединения из пула соединений.
     *
     * @return соединение.
     * @throws SQLException .
     */
    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            connectDB();
        }
        return dataSource.getConnection();
    }

    /**
     * Закрытие соединения с базой данных.
     */
    private void disconnectDB() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Вызов метода disconnectDB() для завкрытия соединения.
     */
    @Override
    public void closeFactory() {
        if (dataSource != null) {
            disconnectDB();
        }
    }
}
