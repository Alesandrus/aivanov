package ru.job4j.dao.daofactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.dao.hibernate.HibernateItemDAO;

/**
 * ДАО-фабрика для работы с базой данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 29.01.2018
 */
public class HibernateDAOFactory extends DAOFactory {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Фабрика сессий Hibernate.
     */
    private SessionFactory sessionFactory;

    /**
     * Синглтон-переменная.
     */
    private static final HibernateDAOFactory FACTORY = new HibernateDAOFactory();

    /**
     * Приватный конструктор.
     */
    private HibernateDAOFactory() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return HibernateDAOFactory.
     */
    public static HibernateDAOFactory getInstance() {
        return FACTORY;
    }

    /**
     * Получение DAO для Item.
     *
     * @return PostgresAddressDAO.
     */
    @Override
    public HibernateItemDAO getItemDAO() {
        return new HibernateItemDAO();
    }

    /**
     * Конфигурирование hibernate, подключение к бд.
     */
    private void config() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Получение SessionFactory.
     *
     * @return SessionFactory.
     */
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            config();
        }
        return sessionFactory;
    }

    /**
     * Вызов метода disconnectDB() для завкрытия соединения.
     */
    @Override
    public void closeFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
