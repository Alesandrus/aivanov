package ru.job4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;
import ru.job4j.storages.UserStorage;

import java.util.Scanner;

/**
 * Class for operating data in storage.
 */
public class ImportUser {
    /**
     * Main.
     *
     * @param args .
     */
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage storage = context.getBean(UserStorage.class);
        operate(storage);
    }

    /**
     * Operate data in storage.
     *
     * @param storage .
     */
    private static void operate(UserStorage storage) {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        do {
            System.out.println("------------------------------------------------");
            System.out.println("Для добавления пользователя введите \"1\"");
            System.out.println("Для просмотра всех пользователей введите \"2\"");
            System.out.println("Для удаления пользователя введите \"3\"");
            System.out.println("Для выхода введите \"4\"");
            cmd = scanner.nextLine();
            if (cmd.equals("1")) {
                System.out.println("Введите имя пользователя");
                String name = scanner.nextLine();
                System.out.println("Введите фамилию пользователя");
                String surname = scanner.nextLine();
                storage.add(new User(name, surname));
            } else if (cmd.equals("2")) {
                for (User user : storage.getAll()) {
                    System.out.println(user.getName() + " " + user.getSurname());
                }
            } else if (cmd.equals("3")) {
                System.out.println("Введите имя пользователя");
                String name = scanner.nextLine();
                System.out.println("Введите фамилию пользователя");
                String surname = scanner.nextLine();
                boolean isRemoved = storage.remove(new User(name, surname));
                if (isRemoved) {
                    System.out.println("Пользователь удален");
                } else {
                    System.out.println("Пользователя с введенными данными не существует");
                }
            }
        } while (!cmd.equals("4"));
        System.out.println("До свидания");
    }
}
