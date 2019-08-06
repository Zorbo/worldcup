package com.worldcup.demo.worldcup.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessData {

    private static final Logger logger = LoggerFactory.getLogger(ProcessData.class);


    public static String getLine(String inputData, int lineNumber) {
        String[] split = readFile(inputData).split("[\\r?\\n]+");
        return split[lineNumber];
    }

    private static String readFile(String path) {
        String inputData = "";
        try {
            File file = new File(path);
            inputData = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException | NullPointerException e) {
            logger.warn("Error reading file: " + path + "\n" + e.getMessage());
        }
        return inputData;
    }
}
