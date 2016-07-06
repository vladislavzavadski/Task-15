package by.training.matrix_multiplication.multiplicator;

import by.training.matrix_multiplication.manager.MatrixManager;
import javafx.util.Pair;

/**
 * Created by vladislav on 28.06.16.
 */
public class Multiplier implements Runnable {
    private int[][] matrix1;
    private int[][] matrix2;
    private int[][] result;
    private MatrixManager matrixManager;

    public Multiplier(int[][] matrix1, int[][] matrix2, int[][] result, MatrixManager matrixManager) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.matrixManager = matrixManager;
    }

    @Override
    public void run() {
        Pair<Integer, Integer> pair;

        while ((pair = matrixManager.getPair())!=null){
            int rowNumber = pair.getKey();
            int columnNumber = pair.getValue();
            int sum = 0;
            for(int i=0; i<matrix1.length; i++){
                sum+=matrix1[rowNumber][i]*matrix2[i][columnNumber];
            }
            result[rowNumber][columnNumber] = sum;
        }
    }
}
