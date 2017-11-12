package ru.job4j.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppEvent implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);
    private Connection connection;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
            ServletContext context = sce.getServletContext();
            String database = context.getInitParameter("database");
            String postgresUser = context.getInitParameter("postgresUser");
            String postgresPassword = context.getInitParameter("postgresPassword");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + database, postgresUser, postgresPassword);
            context.setAttribute("connection", connection);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
