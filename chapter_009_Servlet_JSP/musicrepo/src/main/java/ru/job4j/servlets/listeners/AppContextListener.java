package ru.job4j.servlets.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.repository.factoryrepo.AbstractFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Слушатель контекста приложения.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 25.12.2017
 */
public class AppContextListener implements ServletContextListener {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Сохранение в контексте приложения атрибута со значением используемой фабрики.
     *
     * @param sce событие.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("factoryID", getFactoryId());
    }

    /**
     * Закрытие ресурсов связанных с фабрикой при завершении работы приложения.
     *
     * @param sce событие.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AbstractFactory factory = AbstractFactory.getFactory(getFactoryId());
        if (factory != null) {
            factory.closeFactory();
        }
    }

    /**
     * Получение значения используемой фабрики из файла с настройками.
     *
     * @return номер фабрики.
     */
    private int getFactoryId() {
        Properties dataBaseProperties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            dataBaseProperties.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Integer.parseInt(dataBaseProperties.getProperty("factory"));
    }
}
