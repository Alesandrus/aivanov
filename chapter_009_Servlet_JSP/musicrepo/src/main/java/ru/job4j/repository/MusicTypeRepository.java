package ru.job4j.repository;

import ru.job4j.model.MusicType;
import ru.job4j.repository.specifications.FindSpecificationForMusicType;

import java.util.List;

/**
 * Абстрактный класс репозитория музыкальных типов.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public abstract class MusicTypeRepository {
    /**
     * Метод запрашивающий объект реализующий интерфейс спецификации и возвращающий список музыкальных типов.
     *
     * @param findSpecification объект реализующий интерфейс FindSpecificationForMusicType.
     * @return список музыкальных типов.
     */
    public abstract List<MusicType> querry(FindSpecificationForMusicType findSpecification);
}
