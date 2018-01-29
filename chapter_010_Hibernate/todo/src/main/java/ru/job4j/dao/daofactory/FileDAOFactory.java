package ru.job4j.dao.daofactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.ItemDAO;

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
     * Получение DAO для Item.
     *
     * @return ItemDAO.
     */
    @Override
    public ItemDAO getItemDAO() {
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
