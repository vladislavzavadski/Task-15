package by.training.matrix_multiplier;

import by.training.matrix_multiplier.multiplier.Multiplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vladislav on 29.06.16.
 */
public class Main {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        List<Thread> threads = new ArrayList<>();
        int matrixSize = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
        int[][] matrix1 = new int[matrixSize][matrixSize];
        int[][] matrix2 = new int[matrixSize][matrixSize];
        int[][] result = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                matrix1[i][j] = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
                matrix2[i][j] = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
            }
        }

        for(int i=0; i<matrix1.length; i++){
            Thread thread = new Thread(new Multiplier(i, matrix1[i], matrix2, result));
            thread.start();
            threads.add(thread);
        }

        showMatrix(matrix1);
        showMatrix(matrix2);

        for(Thread thread:threads){
            thread.join();
        }

        showMatrix(result);


    }

    private static void showMatrix(int[][] matrix){
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.print(anAMatrix + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
