package by.training.matrix_multiplier.multiplier;


/**
 * Created by vladislav on 29.06.16.
 */
public class Multiplier implements Runnable{

    private int rowNumber;
    private int[] row;
    private int[][] matrix2;
    private int[][] result;

    public Multiplier(int rowNumber, int[] row, int[][] matrix2, int[][] result) {
        this.rowNumber = rowNumber;
        this.row = row;
        this.matrix2 = matrix2;
        this.result = result;
    }

    @Override
    public void run() {
        for(int i=0; i<matrix2.length; i++){
            int sum = 0;
            for(int j=0; j<matrix2[i].length; j++){
               sum+=matrix2[j][i]*row[j];
            }
            result[rowNumber][i] = sum;
        }
    }
}
