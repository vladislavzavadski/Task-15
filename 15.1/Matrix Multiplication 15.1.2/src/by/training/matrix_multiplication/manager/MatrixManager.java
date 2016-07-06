package by.training.matrix_multiplication.manager;

import javafx.util.Pair;

import java.util.LinkedList;

/**
 * Created by vladislav on 28.06.16.
 */
public class MatrixManager {
    private LinkedList<Pair<Integer, Integer>> rowColumnPairs;

    public MatrixManager(int matrixSize){
        rowColumnPairs = new LinkedList<>();
        for(int i=0; i<matrixSize; i++){
            for (int j=0; j<matrixSize; j++){
                Pair<Integer, Integer> pair = new Pair<>(i, j);
                rowColumnPairs.add(pair);
            }
        }
    }

    public synchronized Pair<Integer, Integer> getPair(){
        if(!rowColumnPairs.isEmpty()){
            return rowColumnPairs.remove();
        }
        else{
            return null;
        }
    }
}
