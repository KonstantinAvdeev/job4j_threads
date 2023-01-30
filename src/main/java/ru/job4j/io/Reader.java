package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class Reader implements IReader {
    private final File file;

    public Reader(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) != -1) {
                if (filter.test((char) data)) {
                    builder.append((char) data);
                }
            }
        }
        return builder.toString();
    }

}