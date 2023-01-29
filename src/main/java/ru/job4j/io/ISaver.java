package ru.job4j.io;

import java.io.IOException;

public interface ISaver {
    public void save(String content) throws IOException;
}
