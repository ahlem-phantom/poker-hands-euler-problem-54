package com.poker.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    /**
     * Gets a BufferedReader for a file located in the resources folder.
     *
     * @param filePath The path to the file to be read, relative to the resources folder.
     * @return A BufferedReader for reading the file.
     * @throws IllegalArgumentException if the file is not found.
    */
    public static BufferedReader getFileResource(String filePath) {
        InputStream fileStream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
        if (fileStream == null) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }
        return new BufferedReader(new InputStreamReader(fileStream));
    }
}