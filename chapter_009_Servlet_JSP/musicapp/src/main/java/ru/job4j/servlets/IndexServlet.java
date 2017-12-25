package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.daofactory.DAOFactory;
import ru.job4j.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет, отвечающий за получение списка пользователей и передаче его в запросе для отображения на главной странице.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class IndexServlet extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Получение списка пользователей и передача его в запросе для отображения на главной странице.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int factoryID = (Integer) getServletContext().getAttribute("factoryID");
        DAOFactory factory = DAOFactory.getDAOFactory(factoryID);
        if (factory != null) {
            List<User> users = factory.getUserDAO().getAll();
            req.setAttribute("users", users);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/Main.jsp");
        dispatcher.forward(req, resp);
    }
}
