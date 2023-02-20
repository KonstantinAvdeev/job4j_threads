package ru.job4j.pools;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class ParallelArraySearchTest {

    @Test
    public void whenDifferentTypes() {
        Throwable differentTypesThrowable = catchThrowable(() -> {
            ParallelArraySearch.search(new Object[]{"Hi", "Bye", "Boy", "Girl", "Home"}, 456);
        });
        assertThat(differentTypesThrowable).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenLinearSearch() {
        assertThat(ParallelArraySearch.search(new Object[]{5, 2423, 456, 24, 66, 452, 1}, 456)).isEqualTo(2);
    }

    @Test
    public void whenParallelSearch() {
        assertThat(ParallelArraySearch.search(new Object[]{5, 2423, 456, 24, 66, 452, 1, 14253, 23423412, 425, 6, 23, 252, 345, 14324},
                1)).isEqualTo(6);
    }

    @Test
    public void whenElementNotFound() {
        assertThat(ParallelArraySearch.search(new Object[]{5, 2423, 456, 24, 66, 452, 1, 14253, 23423412, 425, 6, 23, 252, 345, 14324},
                0)).isEqualTo(-1);
    }

}