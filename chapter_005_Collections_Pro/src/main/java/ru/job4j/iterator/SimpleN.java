package ru.job4j.iterator;

/**
 * Class for check numbers.
 *
 * @author Alexander Ivanov
 * @since 30.03.2017
 * @version 1.0
 */
public class SimpleN {
    public static boolean checkSimple(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        boolean isSimple = true;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                isSimple = false;
                i = n;
            }
        }
        return isSimple;
    }

    public static void main(String[] args) {
        System.out.println(checkSimple(3));
    }
}
