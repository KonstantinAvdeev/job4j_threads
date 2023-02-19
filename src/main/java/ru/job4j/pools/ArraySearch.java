package ru.job4j.pools;

public class ArraySearch {

    public static int index(Object[] array, Object object) {
        for (int i = 0; i < array.length; i++) {
            if (object.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

}
