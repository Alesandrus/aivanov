package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет вместо индексной страницы. Получает список пользователей.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 19.11.2017
 */
public class UsersController extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Получение списка пользователей.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> usersList = users.getAll();
        req.setAttribute("users", usersList);
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role != null && role.equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
        } else if (role != null && role.equals("user")) {
            req.getRequestDispatcher("/WEB-INF/views/UsersViewForUser.jsp").forward(req, resp);
        }
    }
}
