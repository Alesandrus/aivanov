package ru.job4j.monitore_synchronizy;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by Ivanov_ab on 12.07.2017.
 */
public class TextSearch {
    private ArrayList<Path> files = new ArrayList<>();
    private boolean fileIsFinding = false;

    public Path searchPath(Path startPath, String text) {
        SimpleFileVisitor<Path> visitor = new MyVisitor();
        try {
            Files.walkFileTree(startPath, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Path findPath = null;
        for (Path path : files) {
            Future<Path> future = executor.submit(new MyCallable(path, text));
            try {
                Path myPath = future.get();
                if (myPath != null) {
                    findPath = myPath;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return findPath;
    }

    class MyVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files.add(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

    class MyCallable implements Callable<Path> {

        private Path path;
        private String text;

        public MyCallable(Path path, String text) {
            this.path = path;
            this.text = text;
        }

        @Override
        public Path call() {
            String textFile = null;
            try {
                textFile = new String(Files.readAllBytes(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (textFile != null && textFile.contains(text) && !fileIsFinding) {
                fileIsFinding = true;
                return path;
            }
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        TextSearch search = new TextSearch();
        Path startPath = Paths.get("D:\\");
        String text = "Popa";
        Path path = search.searchPath(startPath, text);
        if (path != null) {
            System.out.println(path.getFileName());
        }
    }
}
