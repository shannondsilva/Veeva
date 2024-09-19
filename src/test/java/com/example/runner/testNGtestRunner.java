package com.example.runner;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.example.utils.Reusables.LogCapture;

@CucumberOptions(
        features = "src/test/featureFileDirectory" ,
        glue = {"com.example.stepDefinations","com.example.utils"},
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@CP_1"

)
public class testNGtestRunner extends AbstractTestNGCucumberTests {
//        private ExtentReports extent;
//
//        @BeforeClass
//        public void setup() throws IOException {
//                // Set up ExtentReports
//                ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
//                htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "extent-config.xml"));
//
//                extent = new ExtentReports();
//                extent.attachReporter(htmlReporter);
//                extent.setSystemInfo("Tester", "Shannon");
//        }
//
//        @AfterClass
//        public void tearDown() throws IOException {
//                // Add the productDetails.txt file to the Extent report
//                ExtentTest test = extent.createTest("Product Details Report");
//                File file = new File("productDetails.txt");
//                if (file.exists()) {
//                        BufferedReader br = new BufferedReader(new FileReader(file));
//                        String line;
//                        while ((line = br.readLine()) != null) {
//                                test.info(line);
//                        }
//                        br.close();
//                } else {
//                        test.warning("productDetails.txt file not found.");
//                }
//
//                // Flush the ExtentReports
//                extent.flush();
//        }
//
//        @AfterTest
//        public void afterTest() {
//                // You can place additional cleanup or logging here
//                Reporter.log("Test completed", true);
//        }

}
