/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exceptionhandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Inferno
 */
public class ExceptionHandler {

    private static BufferedWriter bufferedWriter;

    public static void writeToLog(Exception error) {
        try {
            if (bufferedWriter == null) {
                bufferedWriter = new BufferedWriter(new FileWriter(new File("error.log")));
            }
            if (error instanceof NullPointerException) {
                bufferedWriter.write("Null Pointer Exception");
            } else {
                bufferedWriter.write(error.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
