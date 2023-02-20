package ru.job4j.pools;

public class ArraySearch {

    public static int index(Object[] array, int from, int to, Object object) {
        for (int i = from; i < to; i++) {
            if (array[i].getClass() != object.getClass()) {
                throw new IllegalArgumentException("Different types!");
            }
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

}
