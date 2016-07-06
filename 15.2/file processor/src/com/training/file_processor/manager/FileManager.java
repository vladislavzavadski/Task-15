package com.training.file_processor.manager;


import java.io.File;
import java.util.LinkedList;

/**
 * Created by vladislav on 05.07.16.
 */
public class FileManager {
    private LinkedList<File> fileList;

    public FileManager(LinkedList<File> fileList) {
        this.fileList = fileList;
    }

    public synchronized File getFile(){
        if(!fileList.isEmpty()) {
            return fileList.remove();
        }
        else {
            return null;
        }
    }
}
