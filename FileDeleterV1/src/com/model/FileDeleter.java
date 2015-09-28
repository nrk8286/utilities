
package com.model;
import com.exceptionhandler.ExceptionHandler;
import java.io.File;
import javax.swing.JOptionPane;

public class FileDeleter {
	private  int counter = 0;
        public FileDeleter()
        {
            
        }
	public  int init(String path,String pattern) {
		try {
			fileDeleter(path,pattern);
			System.out.println(counter + " Files Deleted");
                        return counter;
		} catch (Exception e) {                        
			ExceptionHandler.writeToLog(e);
                        return 0;
		}
	}

	public  boolean isPatternMatched(String fileName,String pattern) {
		if (fileName.contains(pattern))
			return true;
		else
			return false;
	}

	public  void fileDeleter(String mainDirectory,String pattern) throws Exception {
		File file;
            file = new File(mainDirectory);
                //file = null;
		File[] files = file.listFiles();
                if(files==null)
                {
                    return;
                }
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				fileDeleter(files[i].getAbsolutePath(),pattern);
			} else if (isPatternMatched(files[i].getName(),pattern)) {
				files[i].delete();
				counter++;
			}
		}

	}
    
}
