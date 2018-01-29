package ru.job4j.dao.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.RoleDAO;
import ru.job4j.dao.daofactory.PostgresDAOFactory;
import ru.job4j.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс DAO для CRUD операций с ролью в базе данных Postgres.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class PostgresRoleDAO extends RoleDAO {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Фабрика, для получения соединения с базой данных Postgres.
     */
    PostgresDAOFactory factory = PostgresDAOFactory.getInstance();

    /**
     * Создание роли в базе данных Postgres.
     *
     * @param entity роли.
     * @return true если роль создалася успешно.
     */
    @Override
    public boolean create(Role entity) {
        boolean isCreated = false;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO roles (name) SELECT ? WHERE NOT EXISTS "
                            + "(SELECT id FROM roles WHERE name = ?) RETURNING id");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int roleID = resultSet.getInt("id");
                entity.setId(roleID);
                isCreated = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isCreated;
    }

    /**
     * Получение всех ролей из базы данных Postgres.
     *
     * @return список ролей.
     */
    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM roles");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setName(resultSet.getString("name"));
                roles.add(role);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return roles;
    }

    /**
     * Получение роли из базы данных Postgres по ID.
     *
     * @param id роли.
     * @return роль.
     */
    @Override
    public Role getByID(int id) {
        Role role = null;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM roles WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role();
                role.setId(id);
                role.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return role;
    }

    /**
     * Обновить роль в базе данных Postgres.
     *
     * @param entity роль.
     * @return true если роль успешно обновлена.
     */
    @Override
    public boolean update(Role entity) {
        int isUpdated = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE roles SET name = ? WHERE id = ?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());
            isUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isUpdated > 0;
    }

    /**
     * Удалить роль из базы данных Postgres.
     *
     * @param entity роль.
     * @return true если роль успешно удалена.
     */
    @Override
    public boolean delete(Role entity) {
        int isDeleted = 0;
        try (Connection connection = factory.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM roles WHERE name = ?");
            preparedStatement.setString(1, entity.getName());
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return isDeleted > 0;
    }
}
