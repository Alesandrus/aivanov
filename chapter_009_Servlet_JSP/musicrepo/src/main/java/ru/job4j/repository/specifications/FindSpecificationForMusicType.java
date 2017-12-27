package ru.job4j.repository.specifications;

import ru.job4j.model.MusicType;

import java.util.List;

/**
 * Интерфейс для получения списка музыкальных типов.
 */
public interface FindSpecificationForMusicType {
    /**
     * Получение списка музыкальных типов.
     * @return список музыкальных типов.
     */
    List<MusicType> find();
}
