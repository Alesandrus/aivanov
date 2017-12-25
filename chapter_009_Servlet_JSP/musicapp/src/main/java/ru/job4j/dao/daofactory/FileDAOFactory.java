package ru.job4j.dao.daofactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.AddressDAO;
import ru.job4j.dao.MusicTypeDAO;
import ru.job4j.dao.RoleDAO;
import ru.job4j.dao.UserDAO;

/**
 * ДАО-фабрика для работы с файловой системой.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class FileDAOFactory extends DAOFactory {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Синглтон-переменная.
     */
    private static final FileDAOFactory FACTORY = new FileDAOFactory();

    /**
     * Приватный конструктор.
     */
    private FileDAOFactory() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return FileDAOFactory.
     */
    public static FileDAOFactory getInstance() {
        return FACTORY;
    }

    /**
     * Получение DAO для адреса.
     *
     * @return AddressDAO.
     */
    @Override
    public AddressDAO getAddressDAO() {
        return null;
    }

    /**
     * Получение DAO для музыкального типа.
     *
     * @return MusicTypeDAO.
     */
    @Override
    public MusicTypeDAO getMusicTypeDAO() {
        return null;
    }

    /**
     * Получение DAO для роли.
     *
     * @return RoleDAO.
     */
    @Override
    public RoleDAO getRoleDAO() {
        return null;
    }

    /**
     * Получение DAO для пользователя.
     *
     * @return UserDAO.
     */
    @Override
    public UserDAO getUserDAO() {
        return null;
    }

    /**
     * Закрытие ресурсов.
     */
    @Override
    public void closeFactory() {
        //close streams
    }
}
