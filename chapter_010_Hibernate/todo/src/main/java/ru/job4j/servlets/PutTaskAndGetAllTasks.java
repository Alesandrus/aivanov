package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.ItemDAO;
import ru.job4j.dao.daofactory.DAOFactory;
import ru.job4j.model.Item;
import ru.job4j.utills.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервлет, отвечающий за создание задания и за получение из базы всех заданий.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 24.01.2018
 */
public class PutTaskAndGetAllTasks extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Метод для получения всех заданий.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = new ArrayList<>();
        int factoryID = (Integer) getServletContext().getAttribute("factoryID");
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryID);
        if (daoFactory != null) {
            ItemDAO itemDAO = daoFactory.getItemDAO();
            items = itemDAO.getAll();
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Item item : items) {
            builder.append(Parser.parseToJson(item));
            builder.append(", ");
        }
        if (items.size() > 0) {
            builder.delete(builder.lastIndexOf(","), builder.length());
        }
        builder.append("]");
        String json = builder.toString();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print(json);
    }

    /**
     * Метод для создания задания.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        Item item = new Item();
        item.setDescription(description);
        int factoryID = (Integer) getServletContext().getAttribute("factoryID");
        DAOFactory daoFactory = DAOFactory.getDAOFactory(factoryID);
        if (daoFactory != null) {
            ItemDAO itemDAO = daoFactory.getItemDAO();
            itemDAO.create(item);
        }
    }
}
