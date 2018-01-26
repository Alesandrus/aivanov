package ru.job4j.servlets;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
 * Сервлет, отвечающий за получение из базы всех невыполненных заданий и за изменение выполнения задания.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 24.01.2018
 */
public class SetDoneAndGetUnDone extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Метод для получения всех невыполненных заданий.
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
        List<Item> items = session.createQuery("from Item where done = false").list();
        session.getTransaction().commit();
        session.close();
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
     * Метод для изменения статуса выполнения задания.
     *
     * @param req  запрос.
     * @param resp ответ.
     * @throws ServletException .
     * @throws IOException      .
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("param");
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(json);
        long id = -1;
        boolean done = false;
        while (!parser.isClosed()) {
            JsonToken token = parser.nextToken();
            if (JsonToken.FIELD_NAME.equals(token)) {
                String field = parser.getCurrentName();
                token = parser.nextToken();
                if (field.equals("id")) {
                    id = parser.getValueAsLong();
                } else if (field.equals("done")) {
                    done = parser.getValueAsBoolean();
                }
            }
        }
        if (id != -1) {
            SessionFactory sessionFactory = (SessionFactory) getServletContext().getAttribute("SessionFactory");
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Item item = (Item) session.createQuery("from Item where id = " + id).list().get(0);
            if (item.isDone() != done) {
                item.setDone(done);
                session.update(item);
            }
            session.getTransaction().commit();
            session.close();
        }
    }
}
