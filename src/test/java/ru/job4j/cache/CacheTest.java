package ru.job4j.cache;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CacheTest {

    @Test
    public void whenAddThenGet() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        assertThat(cache.add(base2)).isEqualTo(true);
    }

    @Test
    public void whenDeleteThenGet() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base1);
        Cache cache2 = new Cache();
        cache2.add(base2);
        assertThat(cache.equals(cache2));
    }

    @Test
    public void whenUpdateThenGet() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        cache.update(base2);
        base1.setName("User1");
        cache.update(base1);
        Cache cache2 = new Cache();
        cache2.add(new Base(1, 1));
        cache2.add(new Base(2, 1));
        assertThat(cache.equals(cache2));
    }


}
