package com.oddisey.flappy.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    private FileUtils() {}

    public static String loadAsString(String file) {
        String result = "";

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String buffer = "";

            while ((buffer = fileReader.readLine()) != null) {
                result += buffer + "\n";
            }

            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
