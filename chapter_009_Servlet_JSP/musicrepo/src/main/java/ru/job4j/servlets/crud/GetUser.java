package ru.job4j.servlets.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.User;
import ru.job4j.repository.factoryrepo.AbstractFactory;
import ru.job4j.repository.postgresrepo.specs.UserGetterSpec;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сервлет, отвечающий за отображение информации о пользователе.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class GetUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

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
        String loginParametr = req.getParameter("login");
        int factoryID = (Integer) getServletContext().getAttribute("factoryID");
        AbstractFactory factory = AbstractFactory.getFactory(factoryID);
        if (factory != null) {
            List<User> users = factory.getUserRepository().querry(new UserGetterSpec());
            req.setAttribute("users", users);
            User user = new User();
            user.setLogin(loginParametr);
            boolean userExist = false;
            for (User userSearch : users) {
                if (userSearch.equals(user)) {
                    user = userSearch;
                    userExist = true;
                    break;
                }
            }
            if (userExist) {
                req.setAttribute("login", user.getLogin());
                req.setAttribute("password", user.getPassword());
                req.setAttribute("name", user.getName());
                req.setAttribute("role", user.getRole().getName());
                req.setAttribute("city", user.getAddress().getCity());
                req.setAttribute("street", user.getAddress().getStreet());
                req.setAttribute("house", user.getAddress().getHouse());
                req.setAttribute("musictypes", user.getMusicTypes());

                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/userinfo.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/notfound.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
