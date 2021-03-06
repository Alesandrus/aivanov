package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, отвечающий за обновление пользователем информации о себе.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 08.12.2017
 */
public class UserSelfUpdate extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Метод для отображения результата обновления информации о пользователе.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String name = new String(req.getParameter("name")
                .getBytes("iso-8859-1"), "utf-8");
        String login = new String(req.getParameter("login")
                .getBytes("iso-8859-1"), "utf-8");
        String email = new String(req.getParameter("email")
                .getBytes("iso-8859-1"), "utf-8");
        String password = new String(req.getParameter("password")
                .getBytes("iso-8859-1"), "utf-8");
        int updateResult = users.updateUser(login, name, "", email, password, 2);
        if (updateResult > 0) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/successupdate.jsp");
            dispatcher.forward(req, resp);
        } else if (updateResult == 0) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/dbcrash.jsp");
            dispatcher.forward(req, resp);
        } else if (updateResult == -1) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/notfound.jsp");
            dispatcher.forward(req, resp);
        } else if (updateResult == -2) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/emptyfields.jsp");
            dispatcher.forward(req, resp);
        }
    }

    /**
     * Метод для перенаправления запроса на страницу обновления пользователя.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/UpdateUser.jsp");
        dispatcher.forward(req, resp);
    }
}
