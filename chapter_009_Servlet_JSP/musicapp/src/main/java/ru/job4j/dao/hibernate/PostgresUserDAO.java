package ru.job4j.dao.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.UserDAO;
import ru.job4j.dao.daofactory.PostgresDAOFactory;
import ru.job4j.model.Address;
import ru.job4j.model.MusicType;
import ru.job4j.model.Role;
import ru.job4j.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс DAO для CRUD операций с пользователем в базе данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class PostgresUserDAO extends UserDAO {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Фабрика, для получения соединения с базой данных Postgres.
     */
    PostgresDAOFactory factory = PostgresDAOFactory.getInstance();

    /**
     * Создание пользователя в базе данных Postgres.
     *
     * @param entity пользователь.
     * @return true если пользователь создался успешно.
     */
    @Override
    public boolean create(User entity) {
        boolean isCreated = false;
        try (Connection connection = factory.getConnection()) {
            Address address = entity.getAddress();
            PostgresAddressDAO postgresAddressDAO = new PostgresAddressDAO();
            postgresAddressDAO.create(address);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (login, password, name, role_id, address_id) "
                            + "SELECT ?, ?, ?, (SELECT id FROM roles WHERE name = ?), ? WHERE NOT EXISTS "
                            + "(SELECT id FROM users WHERE login = ?) RETURNING id");
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getRole().getName());
            preparedStatement.setInt(5, entity.getAddress().getId());
            preparedStatement.setString(6, entity.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("id");
                entity.setId(userID);
                String insertUserMusic = "INSERT INTO usersmusic (user_id, musictype_id) VALUES ("
                        + userID + ", (SELECT id FROM musictypes WHERE type = '%s'))";
                Statement statement = connection.createStatement();
                List<MusicType> musicTypes = entity.getMusicTypes();
                for (MusicType music : musicTypes) {
                    statement.addBatch(String.format(insertUserMusic, music.getType()));
                }
                statement.executeBatch();
                isCreated = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isCreated;
    }

    /**
     * Получение всех пользователей из базы данных Postgres.
     *
     * @return список пользователей.
     */
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = factory.getConnection()) {
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
     * Получение пользователя из базы данных Postgres по ID.
     *
     * @param id пользователя.
     * @return пользователя.
     */
    @Override
    public User getByID(int id) {
        User user = null;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT u.login ulogin, u.password upassword, u.name uname, "
                            + "a.id aid, a.city acity, a.street astreet, a.home ahome, r.id rid, r.name rname "
                            + "FROM users u INNER JOIN roles r ON u.role_id = r.id "
                            + "INNER JOIN addresses a ON u.address_id = a.id WHERE u.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setName(resultSet.getString("uname"));
                user.setLogin(resultSet.getString("ulogin"));
                user.setPassword(resultSet.getString("upassword"));
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
                user.setMusicTypes(getMusicTypesByID(id, connection));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Обновить пользователя в базе данных Postgres.
     *
     * @param entity пользователь.
     * @return true если пользователь успешно обновлен.
     */
    @Override
    public boolean update(User entity) {
        int isUpdated = 0;
        boolean isAddressUpdated = false;
        boolean isRoleUpdated = false;
        boolean isMusicTypesUpdated = false;
        try (Connection connection = factory.getConnection()) {
            PostgresAddressDAO addressDAO = new PostgresAddressDAO();
            isAddressUpdated = addressDAO.update(entity.getAddress());
            PostgresRoleDAO roleDAO = new PostgresRoleDAO();
            isRoleUpdated = roleDAO.update(entity.getRole());
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET login = ?, password = ?, name = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setInt(4, entity.getId());
            isUpdated = preparedStatement.executeUpdate();

            List<MusicType> newMusicTypes = new ArrayList<>(entity.getMusicTypes());
            PreparedStatement statement = connection
                    .prepareStatement("SELECT musictype_id, type FROM musictypes m INNER JOIN usersmusic u "
                            + "ON m.id = u.musictype_id WHERE u.user_id = ?");
            statement.setInt(1, entity.getId());
            ResultSet musicTypesResultSet = statement.executeQuery();
            List<MusicType> oldMusicTypes = new ArrayList<>();
            while (musicTypesResultSet.next()) {
                MusicType type = new MusicType();
                type.setId(musicTypesResultSet.getInt("musictype_id"));
                type.setType(musicTypesResultSet.getString("type"));
                oldMusicTypes.add(type);
            }
            if (oldMusicTypes.size() != newMusicTypes.size() || !oldMusicTypes.containsAll(newMusicTypes)) {
                Statement musicTypeStatement = connection.createStatement();
                String deleteOldUserMusic = "DELETE FROM usersmusic WHERE user_id = " + entity.getId();
                String insertUserMusic = "INSERT INTO usersmusic (user_id, musictype_id) VALUES ("
                        + entity.getId() + ", (SELECT id FROM musictypes WHERE type = '%s'))";
                musicTypeStatement.execute(deleteOldUserMusic);
                for (MusicType music : newMusicTypes) {
                    musicTypeStatement.addBatch(String.format(insertUserMusic, music.getType()));
                }
                musicTypeStatement.executeBatch();
                isMusicTypesUpdated = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isUpdated > 0 || isAddressUpdated || isRoleUpdated || isMusicTypesUpdated;
    }

    /**
     * Удалить пользователя из базы данных Postgres.
     *
     * @param entity пользователь.
     * @return true если пользователь успешно удален.
     */
    @Override
    public boolean delete(User entity) {
        int isDeleted = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM usersmusic WHERE user_id = ?");
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setInt(1, entity.getId());
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isDeleted > 0;
    }

    /**
     * Получить список музыкальных типов пользователя по ID.
     *
     * @param userID     ID пользователя.
     * @param connection соединение с базой данных.
     * @return список музыкальных типов пользователя.
     * @throws SQLException .
     */
    private List<MusicType> getMusicTypesByID(int userID, Connection connection) throws SQLException {
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
