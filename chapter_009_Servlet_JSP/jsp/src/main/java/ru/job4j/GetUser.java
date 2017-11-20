package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Сервлет, отвечающий за отображение информации о пользователе.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 19.11.2017
 */
public class GetUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Переменная, хранящая объект-синглтон UserStore.
     */
    private final UserStore users = UserStore.getInstance();

    /**
     * Метод для получения иноформации о пользователе из базы данных и отображения ее на html-странице.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String loginParametr = req.getParameter("login");
        User user = users.getUser(loginParametr);
        if (user.isExist()) {
            String name = user.getName();
            String login = user.getLogin();
            String email = user.getEmail();
            Calendar calendar = user.getCreateDate();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            String date = sdf.format(calendar.getTime());
            HttpSession session = req.getSession();
            session.setAttribute("name", name);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("date", date);
            resp.sendRedirect(req.getContextPath() + "/userinfo.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/notfound.jsp");
        }
    }
}
