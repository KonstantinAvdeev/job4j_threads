package ru.job4j.pools;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelArraySearchTest {

    @Test
    public void whenString() {
        assertThat(ParallelArraySearch.parallelSearch(new Object[]{"Hi", "Bye", "Boy", "Girl", "Home"}, "Boy")).isEqualTo(2);
    }

    @Test
    public void whenDouble() {
        assertThat(ParallelArraySearch.parallelSearch(new Object[]{2.1, 3.3, 1.0, 5.6, 6.2, 3.5, 8.8, 99, 1.2, 45.3, 1.6},
                6.2)).isEqualTo(4);
    }

    @Test
    public void whenLinearSearch() {
        assertThat(ParallelArraySearch.parallelSearch(new Object[]{5, 2423, 456, 24, 66, 452, 1}, 456)).isEqualTo(2);
    }

    @Test
    public void whenParallelSearch() {
        assertThat(ParallelArraySearch.parallelSearch(new Object[]{5, 2423, 456, 24, 66, 452, 1, 14253, 23423412, 425, 6, 23, 252, 345, 14324},
                1)).isEqualTo(6);
    }

    @Test
    public void whenElementNotFound() {
        assertThat(ParallelArraySearch.parallelSearch(new Object[]{5, 2423, 456, 24, 66, 452, 1, 14253, 23423412, 425, 6, 23, 252, 345, 14324},
                0)).isEqualTo(-1);
    }

}