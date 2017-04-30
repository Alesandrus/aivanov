package ru.job4j.threads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WordsAndSpaces.
 *
 * @author Alexander Ivanov
 * @since 30.04.2017
 * @version 1.0
 */
public class WordsAndSpaces {
    /**
     * Number of words.
     */
    private int nWords = 0;

    /**
     * Number of spaces.
     */
    private int nSpaces = 0;

    /**
     * Count words and spaces in text by two threads.
     * @param s - text.
     */
    public void numberOfWordsAndSpaces(String s) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                String regEx = "\\w+";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(s);
                while (!Thread.currentThread().isInterrupted() && matcher.find()) {
                    nWords++;
                    System.out.println("Counted: " + nWords + " words");
                    if (System.currentTimeMillis() - startTime > 1000) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Number of words: " + nWords);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                String regEx = "\\s+";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(s);
                while (!Thread.currentThread().isInterrupted() && matcher.find()) {
                    nSpaces++;
                    System.out.println("Counted: " + nSpaces + " spaces");
                    if (System.currentTimeMillis() - startTime > 1000) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Number of spaces: " + nSpaces);
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main method.
     * @param args for cmd.
     */
    public static void main(String[] args) {
        System.out.println("Program is starting.......");
        WordsAndSpaces wordsAndSpaces = new WordsAndSpaces();
        wordsAndSpaces.numberOfWordsAndSpaces("Number of words should be twelve. "
                + "Number of spaces should be eleven");
        System.out.println("...............End");
    }
}
