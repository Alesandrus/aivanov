package ru.job4j.dao.daofactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.AddressDAO;
import ru.job4j.dao.MusicTypeDAO;
import ru.job4j.dao.RoleDAO;
import ru.job4j.dao.UserDAO;

/**
 * Абстрактная ДАО-фабрика.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class DAOFactory {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Константа для подключения к базе данных Postgres.
     */
    public static final int POSTGRES = 1;

    /**
     * Константа для подключения к файловой системе.
     */
    public static final int FILESYSTEM = 2;

    /**
     * Получение DAO для адреса.
     *
     * @return AddressDAO.
     */
    public abstract AddressDAO getAddressDAO();

    /**
     * Получение DAO для музыкального типа.
     *
     * @return MusicTypeDAO.
     */
    public abstract MusicTypeDAO getMusicTypeDAO();

    /**
     * Получение DAO для роли.
     *
     * @return RoleDAO.
     */
    public abstract RoleDAO getRoleDAO();

    /**
     * Получение DAO для пользователя.
     *
     * @return UserDAO.
     */
    public abstract UserDAO getUserDAO();

    /**
     * Закрытие ресурсов.
     */
    public abstract void closeFactory();

    /**
     * Получение конкретной фабрики.
     *
     * @param whichFactory для опредения фабрики.
     * @return DAOFactory.
     */
    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case POSTGRES:
                return PostgresDAOFactory.getInstance();
            case FILESYSTEM:
                return FileDAOFactory.getInstance();
            default:
                return null;
        }
    }
}
