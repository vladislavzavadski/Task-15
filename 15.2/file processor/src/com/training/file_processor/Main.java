package com.training.file_processor;

import com.training.file_processor.manager.FileManager;
import com.training.file_processor.processor.Processor;

import java.io.*;
import java.util.*;

/**
 * Created by vladislav on 05.07.16.
 */
public class Main {

    private static final String INPUT_FILE_REG_EXP = "in[0-9]*\\.dat";
    private static final String OUTPUT_FILE = "out.dat";

    public static void main(String[] args) throws InterruptedException, IOException {
        File directory = new File(args[0]);
        List<Processor> threads = new ArrayList<>();
        double result = 0.0;
        int threadsCount;
        FileFilter filenameFilter = pathname -> pathname.isFile()&&pathname.getName().matches(INPUT_FILE_REG_EXP);
        File[] files = directory.listFiles(filenameFilter);

        try {
            threadsCount = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex){
            threadsCount = 3;
        }

        if(threadsCount>files.length){
            threadsCount = files.length;
        }

        LinkedList<File> fileList = new LinkedList<>();
        fileList.addAll(Arrays.asList(files));

        FileManager fileManager = new FileManager(fileList);

        for (int i=0; i<threadsCount; i++){
            Processor processor = new Processor(fileManager);
            threads.add(processor);
            processor.start();
        }

        for(Processor thread:threads){
            thread.join();
            result+=thread.getResult();
        }

        File outputFile = new File(args[0]+OUTPUT_FILE);
        if(!outputFile.exists()){
            outputFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.close();
    }
}
