package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, отвечающий за создание пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 19.11.2017
 */
public class CreateUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Метод, выполняющийся при инициализации сервлета и устанавливающий в объекте users соединение с базой данных.
     *
     * @throws ServletException .
     */
    @Override
    public void init() throws ServletException {
        users.connectDB();
    }

    /**
     * Метод для создания пользователя.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = new String(req.getParameter("name")
                .getBytes("iso-8859-1"), "utf-8");
        String login = new String(req.getParameter("login")
                .getBytes("iso-8859-1"), "utf-8");
        String email = new String(req.getParameter("email")
                .getBytes("iso-8859-1"), "utf-8");
        boolean addResult = users.addUser(name, login, email);
        if (addResult) {
            resp.sendRedirect(req.getContextPath() + "/successcreate.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/failcreate.jsp");
        }
    }

    /**
     * Закрытие соединения при окончании работы сервера.
     */
    @Override
    public void destroy() {
        users.disconnectDB();
    }
}
