package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет, отвечающий за обновление информации о пользователе.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 19.11.2017
 */
public class UpdateUser extends HttpServlet {
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
        String newLogin = new String(req.getParameter("newlogin")
                .getBytes("iso-8859-1"), "utf-8");
        int updateResult = users.updateUser(login, name, newLogin, email);
        if (updateResult > 0) {
            resp.sendRedirect(req.getContextPath() + "/successupdate.jsp");
        } else if (updateResult == 0) {
            resp.sendRedirect(req.getContextPath() + "/dbcrash.jsp");
        } else if (updateResult == -1) {
            resp.sendRedirect(req.getContextPath() + "/notfound.jsp");
        } else if (updateResult == -2) {
            resp.sendRedirect(req.getContextPath() + "/emptyfields.jsp");
        }
    }
}
