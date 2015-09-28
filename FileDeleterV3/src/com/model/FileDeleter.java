package com.model;

import com.exceptionhandler.ExceptionHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDeleter {

    static private List<String> fileList = new ArrayList<String>();

    public FileDeleter() {

    }

    public List<String> init(String path, String pattern) {
        try {
            fileDeleter(path, pattern);
            return fileList;
        } catch (Exception e) {
            ExceptionHandler.writeToLog(e);
            return fileList;
        }
    }

    public boolean isPatternMatched(String fileName, String pattern) {
        if (fileName.contains(pattern)) {
            return true;
        } else {
            return false;
        }
    }

    public void fileDeleter(String mainDirectory, String pattern) throws Exception {
        File file;
        file = new File(mainDirectory);
        //file = null;
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                fileDeleter(files[i].getAbsolutePath(), pattern);
            } else if (isPatternMatched(files[i].getName(), pattern)) {
                files[i].delete();
                fileList.add(files[i].getAbsolutePath());
            }
        }

    }

}
