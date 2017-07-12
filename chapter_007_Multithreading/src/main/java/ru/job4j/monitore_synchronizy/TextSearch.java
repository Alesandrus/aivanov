package ru.job4j.monitore_synchronizy;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ivanov_ab on 12.07.2017.
 */
public class TextSearch {
    ArrayList<Path> files = new ArrayList<>();

    class MyVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files.add(file);
            System.out.println(file.getFileName());
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        TextSearch search = new TextSearch();
        MyVisitor visitor = search.new MyVisitor();
        Path path = Paths.get("C:\\Users\\Ivanov_ab\\Documents\\Литература");
        Files.walkFileTree(path, visitor);
        Collections.sort(search.files);
        for (Path p : search.files) {
            System.out.println(p.getFileName());
        }
    }
}
