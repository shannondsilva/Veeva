package com.example.utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import pageObjects.CoreProductLandingPage;
import pageObjects.MensShoppingMenu;
import pageObjects.NewsAndFeaturesPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Constants {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Properties> config = new ThreadLocal<>();
    private static ThreadLocal<Reusables> key = new ThreadLocal<>();
    private static ThreadLocal<CoreProductLandingPage> CP_POM = ThreadLocal.withInitial(CoreProductLandingPage::new);
    private static ThreadLocal<MensShoppingMenu> CP_ShopMen_POM = ThreadLocal.withInitial(MensShoppingMenu::new);


    private static ThreadLocal<NewsAndFeaturesPage> CP_NewsAndFeatures_POM = ThreadLocal.withInitial(NewsAndFeaturesPage::new);
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<String> genericString = new ThreadLocal<>();
    private static ThreadLocal<List<String>> genericList = ThreadLocal.withInitial(ArrayList::new);
    public static String PassedBlock = "PASSED";
    public static String FailedBlock = "FAILED";
    private static ThreadLocal<String> cucumberTags = new ThreadLocal<>();
    private static ThreadLocal<String> browser = new ThreadLocal<>();


    public static NewsAndFeaturesPage getCP_NewsAndFeatures_POM() {
        return CP_NewsAndFeatures_POM.get();
    }
    public static String getCucumberTags() {
        return cucumberTags.get();
    }

    public static String getTestNGBrowser() {
        return browser.get();
    }

    public static void setBrowser(String browserName) {
        System.out.println("================================================>"+browserName);
        browser.set(browserName);
    }
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static Properties getConfig() {
        return config.get();
    }

    public static void setConfig(Properties configInstance) {
        config.set(configInstance);
    }

    public static Reusables getKey() {
        return key.get();
    }

    public static void setKey(Reusables keyInstance) {
        key.set(keyInstance);
    }

    public static CoreProductLandingPage getCP_POM() {
        return CP_POM.get();
    }

    public static MensShoppingMenu getCP_ShopMen_POM() {
        return CP_ShopMen_POM.get();
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest testInstance) {
        test.set(testInstance);
    }

    public static String getGenericString() {
        return genericString.get();
    }

    public static void setGenericString(String value) {
        genericString.set(value);
    }

    public static List<String> getGenericList() {
        return genericList.get();
    }

    public static void setGenericList(List<String> list) {
        genericList.set(list);
    }

    // Method to clean up after each test
    public static void remove() {
        driver.remove();
        config.remove();
        key.remove();
        test.remove();
        genericString.remove();
        genericList.remove();
        CP_POM.remove();
        CP_ShopMen_POM.remove();
        CP_NewsAndFeatures_POM.remove();
        cucumberTags.remove();
        browser.remove();
    }
}
