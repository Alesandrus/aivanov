package ru.job4j.testTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Class SameChars.
 * Класс для сравнения строк на возможность получения одной из символов другой и наоборот.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 21.10.2017
 */
public class SameChars {
    /**
     * Метод использующий для сравнения map.
     *
     * @param first  первая строка.
     * @param second вторая строка.
     * @return true если сравнение увенчалось успехом.
     */
    public boolean isSameWithMap(String first, String second) {
        Map<Character, Integer> map = new HashMap<>();
        boolean isSame = true;
        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();
        for (Character c : firstArr) {
            map.merge(c, 1, (a, b) -> a + b);
        }
        for (Character c : secondArr) {
            if (map.containsKey(c)) {
                map.merge(c, 1, (a, b) -> a - b);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            } else {
                isSame = false;
                break;
            }
        }
        if (isSame && !map.isEmpty()) {
            isSame = false;
        }
        return isSame;
    }

    /**
     * Метод использующий для сравнения сортировку слиянием.
     *
     * @param first  первая строка.
     * @param second вторая строка.
     * @return true если сравнение увенчалось успехом.
     */
    public boolean isSameWithMerge(String first, String second) {
        boolean isSame;
        int nElements = first.length();
        if (nElements == second.length()) {
            char[] firstArr = first.toCharArray();
            char[] secondArr = second.toCharArray();
            recMerge(firstArr, 0, nElements - 1);
            recMerge(secondArr, 0, nElements - 1);
            isSame = isSortedCharArraysSame(firstArr, secondArr);
        } else {
            isSame = false;
        }
        return isSame;
    }

    /**
     * Метод использующий рекурсию для сортировки и слияния всего массива символов.
     *
     * @param arr   массив символов.
     * @param lower нижняя граница массива для сортировки.
     * @param upper верхняя граница массива для сортировки.
     */
    private void recMerge(char[] arr, int lower, int upper) {
        if (lower == upper) {
            return;
        }
        int mid = (lower + upper) / 2;
        recMerge(arr, lower, mid);
        recMerge(arr, mid + 1, upper);
        mergeSort(arr, lower, mid + 1, upper);
    }

    /**
     * Метод для слияния сортированных участков массива.
     *
     * @param arr    массив символов.
     * @param lower  индекс нижней границы участка массива.
     * @param middle индекс середины участка массива.
     * @param upper  индекс верхней границы массива.
     */
    private void mergeSort(char[] arr, int lower, int middle, int upper) {
        int nElements = upper - lower + 1;
        char[] workSpace = new char[nElements];
        int firstIndex = lower;
        int secondIndex = middle;
        int workIndex = 0;
        while (firstIndex <= middle - 1 && secondIndex <= upper) {
            if (arr[firstIndex] < arr[secondIndex]) {
                workSpace[workIndex++] = arr[firstIndex++];
            } else {
                workSpace[workIndex++] = arr[secondIndex++];
            }
        }
        while (firstIndex <= middle - 1) {
            workSpace[workIndex++] = arr[firstIndex++];
        }
        while (secondIndex <= upper) {
            workSpace[workIndex++] = arr[secondIndex++];
        }
        for (workIndex = 0; workIndex < nElements; workIndex++) {
            arr[lower + workIndex] = workSpace[workIndex];
        }
    }

    /**
     * Сравнение двух отсортированных массивов на идентичность.
     *
     * @param first  первый отсортированный массив.
     * @param second второй отсортированный массив.
     * @return true если массивы идентичны.
     */
    private boolean isSortedCharArraysSame(char[] first, char[] second) {
        boolean isSame = true;
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }
}
