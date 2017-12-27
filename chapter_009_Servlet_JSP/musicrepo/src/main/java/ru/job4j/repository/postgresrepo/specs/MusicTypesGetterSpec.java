package ru.job4j.repository.postgresrepo.specs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.MusicType;
import ru.job4j.repository.factoryrepo.PostgresFactory;
import ru.job4j.repository.specifications.FindSpecificationForMusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения всех музыкальных типов из базы данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public class MusicTypesGetterSpec implements FindSpecificationForMusicType {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Получаем все музыкальные типы.
     *
     * @return список музыкальных типов.
     */
    @Override
    public List<MusicType> find() {
        List<MusicType> musicTypes = new ArrayList<>();
        try (Connection connection = PostgresFactory.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM musictypes");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MusicType musicType = new MusicType();
                musicType.setType(resultSet.getString("type"));
                musicTypes.add(musicType);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musicTypes;
    }
}
