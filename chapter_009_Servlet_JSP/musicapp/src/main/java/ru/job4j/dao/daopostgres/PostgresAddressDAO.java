package ru.job4j.dao.daopostgres;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.AddressDAO;
import ru.job4j.dao.daofactory.PostgresDAOFactory;
import ru.job4j.model.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс DAO для CRUD операций с адресом в базе данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class PostgresAddressDAO extends AddressDAO {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Фабрика, для получения соединения с базой данных Postgres.
     */
    PostgresDAOFactory factory = PostgresDAOFactory.getInstance();

    /**
     * Создание адреса в базе данных Postgres.
     *
     * @param entity адрес.
     * @return true если адрес создался успешно.
     */
    @Override
    public boolean create(Address entity) {
        boolean isCreated = false;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO addresses (city, street, home) SELECT ?, ?, ? WHERE NOT EXISTS "
                            + "(SELECT id FROM addresses WHERE city=? AND street=? AND home=?) RETURNING id");
            preparedStatement.setString(1, entity.getCity());
            preparedStatement.setString(2, entity.getStreet());
            preparedStatement.setInt(3, entity.getHouse());
            preparedStatement.setString(4, entity.getCity());
            preparedStatement.setString(5, entity.getStreet());
            preparedStatement.setInt(6, entity.getHouse());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
                isCreated = true;
            } else {
                PreparedStatement statement = connection.prepareStatement("SELECT id FROM addresses "
                        + "WHERE city=? AND street=? AND home=?");
                statement.setString(1, entity.getCity());
                statement.setString(2, entity.getStreet());
                statement.setInt(3, entity.getHouse());
                ResultSet set = statement.executeQuery();
                if (set.next()) {
                    entity.setId(set.getInt("id"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isCreated;
    }

    /**
     * Получение всех адресов из базы данных Postgres.
     *
     * @return список адресов.
     */
    @Override
    public List<Address> getAll() {
        List<Address> addressList = new ArrayList<>();
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM addresses");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHouse(resultSet.getInt("home"));
                addressList.add(address);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return addressList;
    }

    /**
     * Получение адреса из базы данных Postgres по ID.
     *
     * @param id адреса.
     * @return адрес.
     */
    @Override
    public Address getByID(int id) {
        Address address = null;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM addresses WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                address = new Address();
                address.setId(id);
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setHouse(resultSet.getInt("home"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return address;
    }

    /**
     * Обновить адрес в базе данных Postgres.
     *
     * @param entity адрес.
     * @return true если адрес успешно обновлен.
     */
    @Override
    public boolean update(Address entity) {
        int isUpdated = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE addresses SET city = ?, street = ?, home = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getCity());
            preparedStatement.setString(2, entity.getStreet());
            preparedStatement.setInt(3, entity.getHouse());
            preparedStatement.setInt(4, entity.getId());
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isUpdated > 0;
    }

    /**
     * Удалить адрес из базы данных Postgres.
     *
     * @param entity адрес.
     * @return true если адрес успешно удален.
     */
    @Override
    public boolean delete(Address entity) {
        int isDeleted = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM addresses WHERE city = ? AND street = ? AND home = ?");
            preparedStatement.setString(1, entity.getCity());
            preparedStatement.setString(2, entity.getStreet());
            preparedStatement.setInt(3, entity.getHouse());
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isDeleted > 0;
    }
}
