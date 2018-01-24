package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.models.Item;
import ru.job4j.utills.Parser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        SessionFactory factory = (SessionFactory) getServletContext().getAttribute("SessionFactory");
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Item item : items) {
            builder.append(Parser.parseToJson(item));
            builder.append(", ");
        }
        builder.delete(builder.lastIndexOf(","), builder.length());
        builder.append("]");
        String json = builder.toString();
        resp.setContentType("application/json");
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
        SessionFactory factory = (SessionFactory) getServletContext().getAttribute("SessionFactory");
        String description = req.getParameter("description");
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescription(description);
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }
}
