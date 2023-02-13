package ru.job4j.cache;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class CacheTest {

    @Test
    public void whenAddThenGet() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        assertThat(cache.add(base2)).isTrue();
    }

    @Test
    public void whenDeleteThenGet() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base1);
        Map<Integer, Base> memory = new ConcurrentHashMap<>();
        memory.put(base2.getId(), base2);
        assertThat(cache.getMemory()).isEqualTo(memory);
    }

    @Test
    public void whenUpdateThenGet() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        assertThat(cache.update(base1)).isTrue();
        assertThat(cache.update(base2)).isTrue();
    }

    @Test
    public void whenThrowExceptions() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        Base base3 = null;
        cache.add(base1);
        cache.add(base2);
        cache.update(base1);
        Throwable throwOptimisticException = catchThrowable(() -> {
            cache.update(base1);
        });
        assertThat(throwOptimisticException).isInstanceOf(OptimisticException.class);
        Throwable throwAddNullValue = catchThrowable(() -> {
            cache.add(base3);
        });
        assertThat(throwAddNullValue).isInstanceOf(NullPointerException.class);
    }

}