package ru.job4j.utills;

import ru.job4j.models.Item;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Парсер Item в JSON.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 24.01.2018
 */
public class Parser {
    /**
     * Парсинг item в JSON-формат.
     *
     * @param item задание для парсинга.
     * @return строку в JSON-формате.
     */
    public static String parseToJson(Item item) {
        long id = item.getId();
        String description = item.getDescription();
        Timestamp created = item.getCreated();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        boolean done = item.isDone();
        return "{\"id\": " + id + ", \"description\": \"" + description
                + "\", \"created\": \"" + sdf.format(created)
                + "\", \"done\": " + done + "}";
    }
}
