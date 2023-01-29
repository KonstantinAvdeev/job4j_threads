package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;
    private final ISaver iSaver;
    private final IReader iReader;

    public ParseFile(File file, ISaver iSaver, IReader iReader) {
        this.file = file;
        this.iSaver = iSaver;
        this.iReader = iReader;
    }

    public synchronized File getFile() {
        return file;
    }

    public String readContent(Predicate<Character> filter) throws IOException {
        return iReader.content(filter);
    }

    public void saveContent(String content) throws IOException {
        iSaver.saveContent(content);
    }

    class Saver implements ISaver {
        @Override
        public void saveContent(String content) throws IOException {
            try (BufferedOutputStream o = new BufferedOutputStream(new FileOutputStream(file))) {
                for (int i = 0; i < content.length(); i += 1) {
                    o.write(content.charAt(i));
                }
            }
        }
    }

    class Reader implements IReader {
        @Override
        public String content(Predicate<Character> filter) throws IOException {
            String output;
            try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
                output = "";
                int data;
                while ((data = i.read()) > 0) {
                    if (filter.test((char) data)) {
                        output += (char) data;
                    }
                }
            }
            return output;
        }
    }
}
