package ru.job4j.repository.postgresrepo.specs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.Address;
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
 * Класс для получения всех пользователей из базы данных Postgres с указанным адресом.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public class UserFinderByAddress implements FindSpecificationForUser {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Адрес для поиска пользователей.
     */
    private Address address;

    /**
     * Конструктор для установки адреса.
     *
     * @param address адрес.
     */
    public UserFinderByAddress(Address address) {
        this.address = address;
    }

    /**
     * Получение всех пользователей из базы данных Postgres с указанным адресом.
     *
     * @return список пользователей.
     */
    @Override
    public List<User> find() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = PostgresFactory.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT u.id uid, u.login ulogin, u.password upassword, u.name uname, "
                            + "a.id aid, r.id rid, r.name rname FROM users u INNER JOIN addresses a "
                            + "ON u.address_id = a.id INNER JOIN roles r ON u.role_id = r.id "
                            + "WHERE a.city = ? AND a.street =? AND a.home = ?");
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setInt(3, address.getHouse());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userID = resultSet.getInt("uid");
                User user = new User();
                user.setId(userID);
                user.setLogin(resultSet.getString("ulogin"));
                user.setPassword(resultSet.getString("upassword"));
                user.setName(resultSet.getString("uname"));

                address.setId(resultSet.getInt("aid"));
                user.setAddress(address);

                Role role = new Role();
                role.setId(resultSet.getInt("rid"));
                role.setName(resultSet.getString("rname"));
                user.setRole(role);

                user.setMusicTypes(new UserGetterSpec().getMusicTypesByID(userID, connection));

                userList.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return userList;
    }
}
