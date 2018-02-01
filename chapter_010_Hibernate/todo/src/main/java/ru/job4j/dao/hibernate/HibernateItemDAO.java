package ru.job4j.dao.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.job4j.dao.ItemDAO;
import ru.job4j.dao.daofactory.HibernateDAOFactory;
import ru.job4j.model.Item;

import java.util.List;

/**
 * Класс DAO для CRUD операций с пользователем в базе данных.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 29.01.2018
 */
public class HibernateItemDAO extends ItemDAO {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Фабрика, для получения соединения с базой данных.
     */
    private HibernateDAOFactory factory = HibernateDAOFactory.getInstance();

    /**
     * Создание задания в базе данных.
     *
     * @param entity задание.
     */
    @Override
    public void create(Item entity) {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Получение всех заданий из базы данных.
     *
     * @return список заданий.
     */
    @Override
    public List<Item> getAll() {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    /**
     * Получение задания из базы данных по ID.
     *
     * @param id задания.
     * @return задание.
     */
    @Override
    public Item getByID(long id) {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        Item item = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    /**
     * Обновить задание в базе данных.
     *
     * @param entity задание.
     */
    @Override
    public void update(Item entity) {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        Item itemFromDB = session.get(Item.class, entity.getId());
        if (entity.isDone() == itemFromDB.isDone()
                && entity.getDescription().equals(itemFromDB.getDescription())) {
            return;
        }
        itemFromDB.setDescription(entity.getDescription());
        itemFromDB.setDone(entity.isDone());
        session.update(itemFromDB);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Удалить задание из базы данных.
     *
     * @param entity пользователь.
     */
    @Override
    public void delete(Item entity) {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Обновить статус задания.
     *
     * @param item задание.
     */
    @Override
    public void updateStatus(Item item) {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        Item itemFromDB = session.get(Item.class, item.getId());
        if (item.isDone() != itemFromDB.isDone()) {
            itemFromDB.setDone(item.isDone());
            session.update(itemFromDB);
        }
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Получение всех невыполненных задний.
     *
     * @return список невыполненных заданий.
     */
    @Override
    public List<Item> getAllUnDone() {
        Session session = factory.getSessionFactory().openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item where done = false").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }
}
