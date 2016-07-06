package com.training.file_processor.processor;

import com.training.file_processor.manager.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by vladislav on 05.07.16.
 */
public class Processor extends Thread {

    private double result;
    private FileManager fileManager;

    public Processor(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void run() {
        File file;

        while ((file=fileManager.getFile())!=null){
            double subResult = 0;
            try (Scanner scanner = new Scanner(file)){
                int operation = scanner.nextInt();

                switch (operation){
                    case Operation.ADDITION:{
                        while (scanner.hasNextDouble()){
                            subResult+=scanner.nextDouble();
                        }
                        break;
                    }
                    case Operation.MULTIPLICATION:{
                        subResult = 1;
                        while (scanner.hasNextDouble()){
                            subResult*=scanner.nextDouble();
                        }
                        break;
                    }
                    case Operation.SUM_OF_SQUARES:{
                        while (scanner.hasNextDouble()){
                            subResult+=Math.pow(scanner.nextDouble(), 2.0);
                        }
                        break;
                    }
                }


            } catch (FileNotFoundException e) {
                return;
            }

            result+=subResult;
        }
    }

    public double getResult(){
        return result;
    }

}
