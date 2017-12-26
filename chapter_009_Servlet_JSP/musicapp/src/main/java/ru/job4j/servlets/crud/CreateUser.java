package ru.job4j.servlets.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.AddressDAO;
import ru.job4j.dao.UserDAO;
import ru.job4j.dao.daofactory.DAOFactory;
import ru.job4j.model.Address;
import ru.job4j.model.MusicType;
import ru.job4j.model.Role;
import ru.job4j.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет, отвечающий за создание пользователя.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class CreateUser extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

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
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String role = req.getParameter("roles");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int house = Integer.parseInt(req.getParameter("house"));
        String[] musicTypes = req.getParameterValues("musictypes");

        Role userRole = new Role();
        userRole.setName(role);
        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setHouse(house);
        List<MusicType> musicTypeList = new ArrayList<>();
        if (musicTypes != null) {
            for (int i = 0; i < musicTypes.length; i++) {
                MusicType type = new MusicType();
                type.setType(musicTypes[i]);
                musicTypeList.add(type);
            }
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setRole(userRole);
        user.setAddress(address);
        user.setMusicTypes(musicTypeList);

        int factoryID = (Integer) getServletContext().getAttribute("factoryID");
        DAOFactory factory = DAOFactory.getDAOFactory(factoryID);
        if (factory != null) {
            UserDAO userDAO = factory.getUserDAO();
            List<User> users = userDAO.getAll();
            if (!users.contains(user)) {
                AddressDAO addressDAO = factory.getAddressDAO();
                addressDAO.create(address);
                userDAO.create(user);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/successcreate.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/failcreate.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }

    /**
     * Метод для перенаправления запроса на страницу создания пользователя.
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
            List<MusicType> musicTypes = factory.getMusicTypeDAO().getAll();
            req.setAttribute("musictypes", musicTypes);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/Create.jsp");
        dispatcher.forward(req, resp);
    }
}
