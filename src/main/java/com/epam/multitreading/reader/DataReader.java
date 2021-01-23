package com.epam.multitreading.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {
    private static final Logger logger = LogManager.getLogger(DataReader.class);

    private static final String FILE_PATH = "src/main/resources/";
    private static final DataReader instance = new DataReader();

    private DataReader() {
    }

    public static DataReader getInstance(){
        return instance;
    }

    public List<String> readLines(String fileName) {
        StringBuilder path = new StringBuilder(FILE_PATH);
        path.append(fileName);
        List<String> content = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()))) {
            content = bufferedReader.lines().map(String::trim).collect(Collectors.toList());
            logger.info("Read from file: {} data: {}.", fileName, content);
        } catch (IOException e) {
            logger.error(e);
        }
        return content;
    }
}
