package ru.job4j.repository.postgresrepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.Address;
import ru.job4j.model.MusicType;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;
import ru.job4j.repository.factoryrepo.PostgresFactory;
import ru.job4j.repository.specifications.FindSpecificationForUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Репозиторий для работы базы данных Postgres с пользователями.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public class PostgresUserRepo extends UserRepository {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Синглтон-переменная.
     */
    private static final PostgresUserRepo USER_REPO = new PostgresUserRepo();

    /**
     * Приватный конструктор.
     */
    private PostgresUserRepo() {
    }

    /**
     * Получение экземпляра фабрики.
     *
     * @return PostgresFactory.
     */
    public static PostgresUserRepo getInstance() {
        return USER_REPO;
    }

    /**
     * Добавление пользователя в базу данных.
     *
     * @param user пользователь.
     */
    @Override
    public void add(User user) {
        try (Connection connection = PostgresFactory.getInstance().getConnection()) {
            Address address = user.getAddress();
            addAddressToDB(address, connection);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (login, password, name, role_id, address_id) "
                            + "SELECT ?, ?, ?, (SELECT id FROM roles WHERE name = ?), ? WHERE NOT EXISTS "
                            + "(SELECT id FROM users WHERE login = ?) RETURNING id");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getRole().getName());
            preparedStatement.setInt(5, user.getAddress().getId());
            preparedStatement.setString(6, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("id");
                user.setId(userID);
                List<MusicType> musicTypes = user.getMusicTypes();
                addMusicTypeToDB(userID, musicTypes, connection);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Метод запрашивающий объект реализующий интерфейс спецификации и возвращающий список пользователей.
     *
     * @param findSpecification объект реализующий интерфейс FindSpecificationForUser.
     * @return список музыкальных типов.
     */
    @Override
    public List<User> querry(FindSpecificationForUser findSpecification) {
        return findSpecification.find();
    }

    /**
     * Добавление адреса в базу данных, если таковой еще в не не присутствует. Также устанавливает ID адреса.
     *
     * @param address    адрес.
     * @param connection соединение с БД.
     * @throws SQLException .
     */
    private void addAddressToDB(Address address, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO addresses (city, street, home) SELECT ?, ?, ? WHERE NOT EXISTS "
                        + "(SELECT id FROM addresses WHERE city=? AND street=? AND home=?) RETURNING id");
        preparedStatement.setString(1, address.getCity());
        preparedStatement.setString(2, address.getStreet());
        preparedStatement.setInt(3, address.getHouse());
        preparedStatement.setString(4, address.getCity());
        preparedStatement.setString(5, address.getStreet());
        preparedStatement.setInt(6, address.getHouse());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            address.setId(resultSet.getInt("id"));
        } else {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT id FROM addresses WHERE city=? AND street=? AND home=?");
            statement.setString(1, address.getCity());
            statement.setString(2, address.getStreet());
            statement.setInt(3, address.getHouse());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                address.setId(set.getInt("id"));
            }
        }
    }

    /**
     * Добавление музыкальных типов пользователя в базу данных.
     *
     * @param userID     ID пользователя.
     * @param musicTypes список музыкальных типов пользователя.
     * @param connection соединение с БД.
     * @throws SQLException .
     */
    private void addMusicTypeToDB(int userID, List<MusicType> musicTypes, Connection connection) throws SQLException {
        String insertUserMusic = "INSERT INTO usersmusic (user_id, musictype_id) VALUES ("
                + userID + ", (SELECT id FROM musictypes WHERE type = '%s'))";
        Statement statement = connection.createStatement();
        for (MusicType music : musicTypes) {
            statement.addBatch(String.format(insertUserMusic, music.getType()));
        }
        statement.executeBatch();
    }
}
