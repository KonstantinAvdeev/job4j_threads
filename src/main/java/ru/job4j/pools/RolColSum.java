package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = rowAndColSync(matrix, i);
        }
        return sums;
    }

    public static Sums rowAndColSync(int[][] matrix, int element) {
        Sums sums = new Sums();
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            rowSum += matrix[element][i];
            colSum += matrix[i][element];
        }
        sums.setRowSum(rowSum);
        sums.setColSum(colSum);
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = rowAndColAsync(matrix, i).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> rowAndColAsync(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> rowAndColSync(matrix, index));
    }

}