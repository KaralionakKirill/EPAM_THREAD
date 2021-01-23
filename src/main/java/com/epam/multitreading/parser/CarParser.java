package com.epam.multitreading.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CarParser {
    private static final Logger logger = LogManager.getLogger(CarParser.class);

    private static final String NUMBER_SPLITTER = " ";

    private static CarParser instance = new CarParser();


    private CarParser() {
    }

    public static CarParser getInstance() {
        return instance;
    }

    public List<Integer> parse(String line){
        List<Integer> data = new ArrayList<>();
        String[] numbers = line.split(NUMBER_SPLITTER);
        for(String number : numbers){
            data.add(Integer.parseInt(number));
        }
        logger.info( "Parse: " + line + " to " + data);
        return data;
    }
}
