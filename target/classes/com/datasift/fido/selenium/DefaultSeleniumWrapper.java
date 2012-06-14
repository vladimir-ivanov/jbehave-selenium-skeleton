package com.datasift.fido.selenium;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleniumException;

import java.util.Arrays;

public class DefaultSeleniumWrapper extends DefaultSelenium {

    public DefaultSeleniumWrapper(String serverHost, int serverPort, String browserStartCommand, String browserURL) {
        super(serverHost, serverPort, browserStartCommand, browserURL);
    }

    public void waitAndType(String cssSelector, String value) {
        this.waitForElement(cssSelector);
        super.type("css=" + cssSelector, value);
        this.fireEvent("css=" + cssSelector, "blur");
    }

    public void waitAndType(String cssSelector, String value, String timeout) {
        this.waitForElement(cssSelector, timeout);
        super.type("css=" + cssSelector, value);
        this.fireEvent("css=" + cssSelector, "blur");
    }

    //@todo - tidy up method overrides
    public void waitAndClick(String cssSelector) {
        this.waitForElement(cssSelector);
        super.click("css=" + cssSelector);
    }

    public void clickIfExists(String cssSelector) {
        try {
            super.click("css=" + cssSelector);
        } catch (SeleniumException e) {
            // do nothing, don't want to fail if element does not exist
        }
    }

    public void waitForElementToBeRemoved(String cssSelector) {
        String javascriptWrapperString = "selenium.browserbot.getCurrentWindow().document.querySelector('" + cssSelector + "')==null";
        try {
            super.waitForCondition(javascriptWrapperString, "30000");
        } catch (SeleniumException e) {
            throw new SeleniumException("Waited for " + cssSelector + " to be removed : " + e.getMessage(), e);
        }
    }


    public void waitForElement(String cssSelector) {
        String javascriptWrapperString = "selenium.browserbot.getCurrentWindow().document.querySelector('" + cssSelector + "')!=null";
        try {
            super.waitForCondition(javascriptWrapperString, "30000");
        } catch (SeleniumException e) {
            throw new SeleniumException("Waited for " + cssSelector + " : " + e.getMessage(), e);
        }
    }

    public void waitForElement(String cssSelector, String timeout) {
        String javascriptWrapperString = "selenium.browserbot.getCurrentWindow().document.querySelector('" + cssSelector + "')!=null";
        try {
            super.waitForCondition(javascriptWrapperString, timeout);
        } catch (SeleniumException e) {
            throw new SeleniumException("Waited for " + cssSelector + " : " + e.getMessage(), e);
        }
    }

    public void sleep(Integer milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void typeKeyPressKeyUp(String elementLocator, String textToType) {

        char[] chars = textToType.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            String key = Character.toString(chars[i]);
            this.keyDown(elementLocator, key);
            this.keyPress(elementLocator, key);
            this.keyUp(elementLocator, key);
        }
        this.fireEvent(elementLocator, "blur");
    }

    public Boolean dropDownMatches(String[] dropDownOptions, String[] expectedOptions) {
        java.util.List<String> dropDownList = Arrays.asList(dropDownOptions);
        java.util.List expectedDropDownList = Arrays.asList(expectedOptions);
        return dropDownList.containsAll(expectedDropDownList);
    }
}
