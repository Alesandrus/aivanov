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
 * Класс для получения всех пользователей из базы данных Postgres с указанной ролью.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 27.12.2017
 */
public class UserFinderByRole implements FindSpecificationForUser {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Роль для поиска пользователей.
     */
    private Role role;

    /**
     * Конструктор для установки роли.
     *
     * @param role роль.
     */
    public UserFinderByRole(Role role) {
        this.role = role;
    }

    /**
     * Получение всех пользователей из базы данных Postgres с указанной ролью.
     *
     * @return список пользователей.
     */
    @Override
    public List<User> find() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = PostgresFactory.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT u.id uid, u.login ulogin, u.password upassword, u.name uname, "
                            + "a.id aid, a.city acity, a.street astreet, a.home ahome, r.id rid "
                            + "FROM users u LEFT OUTER JOIN addresses a "
                            + "ON u.address_id = a.id INNER JOIN roles r ON u.role_id = r.id WHERE r.name = ?");
            preparedStatement.setString(1, role.getName());
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

                role.setId(resultSet.getInt("rid"));
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
