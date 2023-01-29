package ru.job4j.io;

import java.io.IOException;
import java.util.function.Predicate;

public interface IReader {
    public String content(Predicate<Character> filter) throws IOException;
}