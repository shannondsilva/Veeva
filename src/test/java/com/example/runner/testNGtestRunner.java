package com.example.runner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.example.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.example.utils.Reusables.LogCapture;

@CucumberOptions(
        features = "src/test/featureFileDirectory",
        glue = {"com.example.stepDefinations", "com.example.utils"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
//        tags = "@CP_1"

)
public class testNGtestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setup(String browser) {
        Constants.setBrowser(browser);
    }
//    mvn test -Dcucumber.filter.tags="@smoke and not @ignore"

    @BeforeClass
    public void clearLogDump() {
        File folder = new File(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "logDump");
        if (folder.exists() && folder.isDirectory()) {
            System.out.println("Folder exists. Clearing contents...");
            deleteFolderContents(folder);
            System.out.println("Folder contents cleared.");
        } else {
            System.out.println("Folder does not exist. Creating new folder...");
            folder.mkdirs();
        }
    }

    public static void deleteFolderContents(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteFolderContents(file); // Recursively delete subfolders
                }
                file.delete(); // Delete file or empty folder
            }
        }
    }

//    Commenting this, since need to limit parallel executions to <test> block
    @Override
    @DataProvider(parallel = true)  // Enable parallel execution
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
