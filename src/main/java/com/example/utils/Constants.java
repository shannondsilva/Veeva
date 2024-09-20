package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogCombiner;
import pageObjects.CoreProductLandingPage;
import pageObjects.MensShoppingMenu;

import java.util.ArrayList;
import java.util.List;
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
    public static MensShoppingMenu CP_ShopMen_POM = new MensShoppingMenu();

    public static ExtentReports extent;

    public static ExtentTest test;
    public static String genericString;

    public static List<String> genericList = new ArrayList<>();


}
