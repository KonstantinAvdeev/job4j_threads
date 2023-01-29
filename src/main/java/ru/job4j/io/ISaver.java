package ru.job4j.io;

import java.io.IOException;

public interface ISaver {
    public void saveContent(String content) throws IOException;
}
