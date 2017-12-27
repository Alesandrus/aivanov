package ru.job4j.repository.factoryrepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.repository.MusicTypeRepository;
import ru.job4j.repository.UserRepository;

/**
 * Абстрактная фабрика для получения репозиториев.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public abstract class AbstractFactory {
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
     * Получение репозитория для пользователя.
     *
     * @return UserRepository.
     */
    public abstract UserRepository getUserRepository();

    /**
     * Получение репозитория для роли.
     *
     * @return MusicTypeRepository.
     */
    public abstract MusicTypeRepository getMusicTypeRepository();

    /**
     * Закрытие ресурсов.
     */
    public abstract void closeFactory();

    /**
     * Получение конкретной фабрики.
     *
     * @param whichFactory для опредения фабрики.
     * @return AbstractFactory.
     */
    public static AbstractFactory getFactory(int whichFactory) {
        switch (whichFactory) {
            case POSTGRES:
                return PostgresFactory.getInstance();
            case FILESYSTEM:
                return FileFactory.getInstance();
            default:
                return null;
        }
    }
}
