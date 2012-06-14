package com.datasift.fido.stepdefinitions;

import com.datasift.fido.selenium.DefaultSeleniumWrapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseSteps {

    protected static DefaultSeleniumWrapper selenium;
    private static Properties properties;
    private static String seleniumHost;
    private static Integer seleniumPort;
    private static String browser;
    private static String baseUrl;

    static {
        populateProperties();
        selenium = new DefaultSeleniumWrapper(seleniumHost, seleniumPort, browser, baseUrl);
    }

    private static void initializeProperties() {
        properties = new Properties();
        try {
            FileReader fileResource = new FileReader("test.properties");

            try {
                properties.load(fileResource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        seleniumHost = properties.getProperty("seleniumHost");
        seleniumPort = Integer.parseInt(properties.getProperty("seleniumPort"));
        browser = properties.getProperty("browser");
        baseUrl = properties.getProperty("baseUrl");
    }

    private static void populateProperties() {

        if (properties == null) {
            initializeProperties();
        }

    }

    /**
     * Add some formatting and extra info to an exception message to ease debugging and highlight exact point of failure
     *
     * @param e
     * @param message
     */
    protected String decorateException(Exception e, String message) {
        String retVal;

        retVal = "\n\t----------------------------------------------------------------------------------------\n\n\t" +
                e.getMessage() + " \n\t" +
                message + " \n" +
                "\n\t----------------------------------------------------------------------------------------\n";

        return retVal;
    }
}
