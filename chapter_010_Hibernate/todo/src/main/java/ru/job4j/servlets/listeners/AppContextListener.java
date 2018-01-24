package ru.job4j.servlets.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Слушатель контекста приложения.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 24.01.2018
 */
public class AppContextListener implements ServletContextListener {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Создание и сохранение SessionFactory в контексте приложения.
     *
     * @param sce событие.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext()
                .setAttribute("SessionFactory",
                        new Configuration().configure().buildSessionFactory());
    }

    /**
     * Закрытие SessionFactory при завершении работы приложения.
     *
     * @param sce событие.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactory factory = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
        if (factory != null) {
            factory.close();
        }
    }
}
