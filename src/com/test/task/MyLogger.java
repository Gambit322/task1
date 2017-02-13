package com.test.task;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by oleg on 13.02.17.
 */
public class MyLogger {
    public final static Logger logger = Logger.getLogger(MyLogger.class.getName());
    private static FileHandler fh = null;

    public static void init() {
        try {
            fh = new FileHandler("log.txt");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }
}