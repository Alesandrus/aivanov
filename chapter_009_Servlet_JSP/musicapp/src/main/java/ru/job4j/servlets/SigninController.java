package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.daofactory.DAOFactory;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Сервлет, отвечающий за аутентификацию пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 06.12.2017
 */
public class SigninController extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Метод для перенаправления на страницу аутентификации.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    /**
     * Аутентификация пользователя.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int factoryID = (Integer) getServletContext().getAttribute("factoryID");
        DAOFactory factory = DAOFactory.getDAOFactory(factoryID);
        if (factory != null) {
            List<User> users = factory.getUserDAO().getAll();
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            boolean hasUser = false;
            Iterator<User> iterator = users.iterator();
            while (iterator.hasNext()) {
                User userForCheck = iterator.next();
                if (userForCheck.equals(user)) {
                    user = userForCheck;
                    hasUser = true;
                    break;
                }
            }
            if (hasUser) {
                String role = user.getRole().getName();
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("role", role);
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            } else {
                req.setAttribute("error", "Credential error");
                doGet(req, resp);
            }
        }
    }
}
