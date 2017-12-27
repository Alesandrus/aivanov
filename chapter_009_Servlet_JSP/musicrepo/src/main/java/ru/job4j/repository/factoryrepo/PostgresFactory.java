package ru.job4j.repository.factoryrepo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.repository.postgresrepo.PostgresMusicTypeRepo;
import ru.job4j.repository.postgresrepo.PostgresUserRepo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Фабрика репозиториев для работы с базой данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class PostgresFactory extends AbstractFactory {
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
    private static final PostgresFactory FACTORY = new PostgresFactory();

    /**
     * Приватный конструктор.
     */
    private PostgresFactory() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return PostgresFactory.
     */
    public static PostgresFactory getInstance() {
        return FACTORY;
    }

    /**
     * Получение репозитория для пользователя.
     *
     * @return UserRepository.
     */
    @Override
    public PostgresUserRepo getUserRepository() {
        return PostgresUserRepo.getInstance();
    }

    /**
     * Получение репозитория для роли.
     *
     * @return MusicTypeRepository.
     */
    @Override
    public PostgresMusicTypeRepo getMusicTypeRepository() {
        return PostgresMusicTypeRepo.getInstance();
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
