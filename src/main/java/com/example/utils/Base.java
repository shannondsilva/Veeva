package com.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.*;
import java.util.Properties;

public class Base extends Reusables {

    @Before
    public void initialiseParameters(Scenario scenario) throws IOException {

        Constants.setKey(new Reusables());

        InputStream inputStream1 = new FileInputStream("config.properties");
        Constants.setConfig(new Properties());
        Constants.getConfig().load(inputStream1);

    }

    @After
    public void tearDown(Scenario scenario){

        if(Constants.getDriver()!=null){
            Constants.getDriver().quit();
        }
        LogCapture.info("Scenario Name --> "+scenario.getName());
    }
}
