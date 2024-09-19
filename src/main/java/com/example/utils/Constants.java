package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogCombiner;
import pageObjects.CoreProductLandingPage;

import java.util.Properties;

public class Constants {

    public static WebDriver driver;
    public static String PassedBlock = "PASSED";
    public static String FailedBlock = "FAILED";
    public static Properties applicationProperty;
    public static Properties config;
    public static Properties MarsApp;
    public static Reusables key;

    public static CoreProductLandingPage CP_POM = new CoreProductLandingPage();

}
