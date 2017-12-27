package ru.job4j.repository.factoryrepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.repository.MusicTypeRepository;
import ru.job4j.repository.UserRepository;

/**
 * Фабрика для работы с файловой системой (Не реализована).
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class FileFactory extends AbstractFactory {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Синглтон-переменная.
     */
    private static final FileFactory FACTORY = new FileFactory();

    /**
     * Приватный конструктор.
     */
    private FileFactory() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return FileFactory.
     */
    public static FileFactory getInstance() {
        return FACTORY;
    }

    /**
     * Получение репозитория для пользователя.
     *
     * @return UserRepository.
     */
    @Override
    public UserRepository getUserRepository() {
        return null;
    }

    @Override
    public MusicTypeRepository getMusicTypeRepository() {
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
