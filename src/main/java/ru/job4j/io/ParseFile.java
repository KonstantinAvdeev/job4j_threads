package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class ParseFile {
    private final File file;
    private final ISaver iSaver;
    private final IReader iReader;

    public ParseFile(File file, ISaver iSaver, IReader iReader) {
        this.file = file;
        this.iSaver = iSaver;
        this.iReader = iReader;
    }

    public String readContent() throws IOException {
        return iReader.content(data -> true);
    }

    public String readContentWithoutUnicode() throws IOException {
        return iReader.content(data -> data < 0x80);
    }

    public void saveContent(String content) throws IOException {
        iSaver.save(content);
    }

}