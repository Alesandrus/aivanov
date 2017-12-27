package ru.job4j.repository.postgresrepo.specs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.Address;
import ru.job4j.model.MusicType;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.repository.factoryrepo.PostgresFactory;
import ru.job4j.repository.specifications.FindSpecificationForUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения всех пользователей из базы данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public class UserGetterSpec implements FindSpecificationForUser {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Получаем всех пользователей находящихся в базе данных.
     *
     * @return список пользователей.
     */
    @Override
    public List<User> find() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = PostgresFactory.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT u.id uid, u.login ulogin, u.password upassword, u.name uname, "
                            + "a.id aid, a.city acity, a.street astreet, a.home ahome, r.id rid, r.name rname "
                            + "FROM users u LEFT OUTER JOIN addresses a "
                            + "ON u.address_id = a.id INNER JOIN roles r ON u.role_id = r.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userID = resultSet.getInt("uid");
                User user = new User();
                user.setId(userID);
                user.setLogin(resultSet.getString("ulogin"));
                user.setPassword(resultSet.getString("upassword"));
                user.setName(resultSet.getString("uname"));
                Address address = new Address();
                address.setId(resultSet.getInt("aid"));
                address.setCity(resultSet.getString("acity"));
                address.setStreet(resultSet.getString("astreet"));
                address.setHouse(resultSet.getInt("ahome"));
                user.setAddress(address);
                Role role = new Role();
                role.setId(resultSet.getInt("rid"));
                role.setName(resultSet.getString("rname"));
                user.setRole(role);
                user.setMusicTypes(getMusicTypesByID(userID, connection));
                userList.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return userList;
    }

    /**
     * Получить список музыкальных типов пользователя по ID.
     *
     * @param userID     ID пользователя.
     * @param connection соединение с базой данных.
     * @return список музыкальных типов пользователя.
     * @throws SQLException .
     */
    public List<MusicType> getMusicTypesByID(int userID, Connection connection) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("SELECT m.id mid, m.type mtype FROM musictypes m "
                        + "INNER JOIN usersmusic u ON m.id = u.musictype_id WHERE u.user_id = ?");
        statement.setInt(1, userID);
        ResultSet musicResultSet = statement.executeQuery();
        List<MusicType> musicTypes = new ArrayList<>();
        while (musicResultSet.next()) {
            MusicType type = new MusicType();
            type.setId(musicResultSet.getInt("mid"));
            type.setType(musicResultSet.getString("mtype"));
            musicTypes.add(type);
        }
        return musicTypes;
    }
}
