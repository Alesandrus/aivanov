package ru.job4j.testTask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс для тестирования SameChars.
 *
 * @author Alexander Ivanov
 * @version 1.0
 * @since 21.10.2017
 */
public class SameCharsTest {
    /**
     * Тестирование метода isSameWithMap() когда из одного слова можно составить другое.
     */
    @Test
    public void testIsSameWithMapWhenWordContainsCharsOfOther() {
        SameChars sameChars = new SameChars();
        String first = "mama";
        String second = "amam";
        boolean result = sameChars.isSameWithMap(first, second);
        assertThat(result, is(true));
    }

    /**
     * Тестирование метода isSameWithMap() когда из одного слова нельзя составить другое.
     */
    @Test
    public void testIsSameWithMapWhenWordNoContainsCharsOfOther() {
        SameChars sameChars = new SameChars();
        String first = "mama";
        String second = "mamm";
        boolean result = sameChars.isSameWithMap(first, second);
        assertThat(result, is(false));
    }

    /**
     * Тестирование метода isSameWithMerge() когда из одного слова можно составить другое.
     */
    @Test
    public void testIsSameWithMergeWhenWordContainsCharsOfOther() {
        SameChars sameChars = new SameChars();
        String first = "mama";
        String second = "amam";
        boolean result = sameChars.isSameWithMerge(first, second);
        assertThat(result, is(true));
    }

    /**
     * Тестирование метода isSameWithMerge() когда из одного слова нельзя составить другое.
     */
    @Test
    public void testIsSameWithMergeWhenWordNoContainsCharsOfOther() {
        SameChars sameChars = new SameChars();
        String first = "mama";
        String second = "maaa";
        boolean result = sameChars.isSameWithMerge(first, second);
        assertThat(result, is(false));
    }

    /**
     * Тестирование метода isSameWithMap() анаграммы "просветитель - терпеливость".
     */
    @Test
    public void testIsSameWithMap2() {
        SameChars sameChars = new SameChars();
        String first = "просветитель";
        String second = "терпеливость";
        boolean result = sameChars.isSameWithMap(first, second);
        assertThat(result, is(true));
    }

    /**
     * Тестирование метода isSameWithMerge() анаграммы "просветитель - терпеливость".
     */
    @Test
    public void testIsSameWithMerge2() {
        SameChars sameChars = new SameChars();
        String first = "просветитель";
        String second = "терпеливость";
        boolean result = sameChars.isSameWithMerge(first, second);
        assertThat(result, is(true));
    }

    /**
     * Тестирование метода isSameWithMap() анаграммы "апельсин - спаниель".
     */
    @Test
    public void testIsSameWithMap3() {
        SameChars sameChars = new SameChars();
        String first = "апельсин";
        String second = "спаниель";
        boolean result = sameChars.isSameWithMap(first, second);
        assertThat(result, is(true));
    }

    /**
     * Тестирование метода isSameWithMerge() анаграммы "апельсин - спаниель".
     */
    @Test
    public void testIsSameWithMerge3() {
        SameChars sameChars = new SameChars();
        String first = "апельсин";
        String second = "спаниель";
        boolean result = sameChars.isSameWithMerge(first, second);
        assertThat(result, is(true));
    }
}