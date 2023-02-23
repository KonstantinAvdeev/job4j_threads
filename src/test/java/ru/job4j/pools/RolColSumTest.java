package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RolColSumTest {
    @Test
    public void whenMatrixHas2Elements() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 2}, {3, 4}};
        Sums[] syncSums = RolColSum.sum(matrix);
        Sums[] asyncSums = RolColSum.asyncSum(matrix);
        Sums expected = new Sums();
        expected.setRowSum(3);
        expected.setColSum(4);
        assertThat(syncSums[0]).isEqualTo(expected);
        assertThat(asyncSums[0]).isEqualTo(expected);
    }

    @Test
    public void whenMatrixHas3Elements() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Sums[] syncSums = RolColSum.sum(matrix);
        Sums[] asyncSums = RolColSum.asyncSum(matrix);
        Sums expected = new Sums();
        expected.setRowSum(24);
        expected.setColSum(18);
        assertThat(syncSums[2]).isEqualTo(expected);
        assertThat(asyncSums[2]).isEqualTo(expected);
    }

}