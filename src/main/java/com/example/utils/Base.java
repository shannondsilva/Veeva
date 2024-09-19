package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.*;
import java.util.Properties;

public class Base extends Reusables {

    @Before
    public void initialiseParameters(Scenario scenario) throws IOException {

        Constants.key = new Reusables();

        InputStream inputStream = new FileInputStream(System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"application.properties");
        Constants.applicationProperty = new Properties();
        Constants.applicationProperty.load(inputStream);

        InputStream inputStream1 = new FileInputStream("config.properties");
        Constants.config = new Properties();
        Constants.config.load(inputStream1);


        InputStream inputStream2 = new FileInputStream(System.getProperty("user.dir")+ File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"MarsApp.properties");
        Constants.MarsApp = new Properties();
        Constants.MarsApp.load(inputStream2);
        
    }

    @After
    public void tearDown(Scenario scenario){
        if(Constants.driver!=null){
            Constants.driver.quit();
        }

        LogCapture.info("Scenario Name --> "+scenario.getName());
    }

}
