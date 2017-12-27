package ru.job4j.repository.postgresrepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.MusicType;
import ru.job4j.repository.MusicTypeRepository;
import ru.job4j.repository.specifications.FindSpecificationForMusicType;

import java.util.List;

/**
 * Репозиторий для работы базы данных Postgres с музкальными типами.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public class PostgresMusicTypeRepo extends MusicTypeRepository {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Синглтон-переменная.
     */
    private static final PostgresMusicTypeRepo MUSIC_TYPE_REPO = new PostgresMusicTypeRepo();

    /**
     * Приватный конструктор.
     */
    private PostgresMusicTypeRepo() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return PostgresFactory.
     */
    public static PostgresMusicTypeRepo getInstance() {
        return MUSIC_TYPE_REPO;
    }

    /**
     * Метод запрашивающий объект реализующий интерфейс спецификации и возвращающий список музыкальных типов.
     *
     * @param findSpecification объект реализующий интерфейс FindSpecificationForMusicType.
     * @return список музыкальных типов.
     */
    @Override
    public List<MusicType> querry(FindSpecificationForMusicType findSpecification) {
        return findSpecification.find();
    }
}
