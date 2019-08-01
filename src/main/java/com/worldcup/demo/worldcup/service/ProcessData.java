package com.worldcup.demo.worldcup.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProcessData {


    public static String getLine(String inputData, int lineNumber) {
        String[] split = readFile(inputData).split("[\\r?\\n]+");
        return split[lineNumber];
    }

    private static String readFile(String path) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }
}
