package ru.job4j.dao.daopostgres;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.MusicTypeDAO;
import ru.job4j.dao.daofactory.PostgresDAOFactory;
import ru.job4j.model.Address;
import ru.job4j.model.MusicType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс DAO для CRUD операций с музыкальным типом в базе данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class PostgresMusicTypeDAO extends MusicTypeDAO {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Фабрика, для получения соединения с базой данных Postgres.
     */
    PostgresDAOFactory factory = PostgresDAOFactory.getInstance();

    /**
     * Создание музыкального типа в базе данных Postgres.
     *
     * @param entity музыкальный тип.
     * @return true если музыкальный тип создался успешно.
     */
    @Override
    public boolean create(MusicType entity) {
        boolean isCreated = false;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO musictypes (type) SELECT ? WHERE NOT EXISTS "
                            + "(SELECT id FROM musictypes WHERE type = ?) RETURNING id");
            preparedStatement.setString(1, entity.getType());
            preparedStatement.setString(2, entity.getType());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int musicTypeID = resultSet.getInt("id");
                entity.setId(musicTypeID);
                isCreated = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isCreated;
    }

    /**
     * Получение всех музыкальных типов из базы данных Postgres.
     *
     * @return список музыкальных типов.
     */
    @Override
    public List<MusicType> getAll() {
        List<MusicType> musicTypes = new ArrayList<>();
        try (Connection connection = factory.getConnection()) {
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

    /**
     * Получение музыкального типа из базы данных Postgres по ID.
     *
     * @param id музыкального типа.
     * @return музыкальный тип.
     */
    @Override
    public MusicType getByID(int id) {
        MusicType musicType = null;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM musictypes WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                musicType = new MusicType();
                musicType.setId(id);
                musicType.setType(resultSet.getString("type"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return musicType;
    }

    /**
     * Обновить музыкальный тип в базе данных Postgres.
     *
     * @param entity музыкальный тип.
     * @return true если музыкальный тип успешно обновлен.
     */
    @Override
    public boolean update(MusicType entity) {
        int isUpdated = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE musictypes SET type = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getType());
            preparedStatement.setInt(2, entity.getId());
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isUpdated > 0;
    }

    /**
     * Удалить музыкальный тип из базы данных Postgres.
     *
     * @param entity музыкальный тип.
     * @return true если музыкальный тип успешно удален.
     */
    @Override
    public boolean delete(MusicType entity) {
        int isDeleted = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM musictypes WHERE type = ?");
            preparedStatement.setString(1, entity.getType());
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isDeleted > 0;
    }
}
