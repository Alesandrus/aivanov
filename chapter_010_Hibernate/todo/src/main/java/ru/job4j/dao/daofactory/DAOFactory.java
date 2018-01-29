package ru.job4j.dao.daofactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.ItemDAO;

/**
 * Абстрактная ДАО-фабрика.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 29.01.2018
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
     * Получение DAO для Item.
     *
     * @return ItemDAO.
     */
    public abstract ItemDAO getItemDAO();

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
                return HibernateDAOFactory.getInstance();
            case FILESYSTEM:
                return FileDAOFactory.getInstance();
            default:
                return null;
        }
    }
}
