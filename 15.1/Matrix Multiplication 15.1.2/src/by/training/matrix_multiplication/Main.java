package by.training.matrix_multiplication;

import by.training.matrix_multiplication.manager.MatrixManager;
import by.training.matrix_multiplication.multiplicator.Multiplier;

import java.util.Random;

/**
 * Created by vladislav on 28.06.16.
 */
public class Main {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int matrixSize = random.nextInt(MAX_VALUE-MIN_VALUE+1)+MIN_VALUE;
        int[][] matrix1 = new int[matrixSize][matrixSize];
        int[][] matrix2 = new int[matrixSize][matrixSize];
        int[][] result = new int[matrixSize][matrixSize];

        for(int i=0; i<matrix1.length; i++){
            for (int j=0; j<matrix1[i].length; j++){
                matrix1[i][j] = random.nextInt(MAX_VALUE-MIN_VALUE+1)+MIN_VALUE;
                matrix2[i][j] = random.nextInt(MAX_VALUE-MIN_VALUE+1)+MIN_VALUE;
            }
        }

        MatrixManager matrixManager = new MatrixManager(matrixSize);
        Multiplier multiplier = new Multiplier(matrix1, matrix2, result, matrixManager);
        Multiplier multiplier1 = new Multiplier(matrix1, matrix2, result, matrixManager);

        Thread thread = new Thread(multiplier);
        Thread thread1 = new Thread(multiplier1);

        thread.start();
        thread1.start();

        showMatrix(matrix1);
        showMatrix(matrix2);

        thread.join();
        thread1.join();

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
