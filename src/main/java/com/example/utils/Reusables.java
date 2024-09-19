package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Reusables {

    public static final Logger LogCapture = LogManager.getLogger(Reusables.class);

    public String openBrowser(String data, String value) {
        try {
            LogCapture.info("openBrowser method called");
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.managed_default_content_settings.images", 1);
            options.addArguments("--no-proxy-server");
            options.setExperimentalOption("prefs", prefs);

            Constants.driver = new ChromeDriver(options);
            Constants.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Constants.driver.manage().window().maximize();
            Constants.driver.get(value);
            LogCapture.info("openBrowser method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("openBrowser method failed");
            return Constants.FailedBlock;
        }
    }

    public String click(String data, String value) {
        try {
            LogCapture.info("click method called");
            Constants.driver.findElement(By.xpath(data)).click();
            LogCapture.info("click method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("click method failed");
            return Constants.FailedBlock;
        }
    }


    public String fluentWait(String data, String value) {
        try {

            Wait<WebDriver> fWait = new FluentWait<>(Constants.driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(NoSuchElementException.class);
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("click method failed");
            return Constants.FailedBlock;
        }
    }

    public String visibleWaitCondition(String data, String value) {
        try {
            LogCapture.info("visibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.driver, Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(data)));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("visibleWaitCondition method failed");
            return Constants.FailedBlock;
        }
    }

    public String visibleWaitCondition(WebElement element, String value) {
        try {
            LogCapture.info("visibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.driver, Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.visibilityOf(element));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("visibleWaitCondition method failed :"+e);
            return Constants.FailedBlock;
        }
    }

    public String InvisibleWaitCondition(String data, String value) {
        try {
            LogCapture.info("InvisibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.driver, Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(data)));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("InvisibleWaitCondition method failed");
            return Constants.FailedBlock;
        }
    }


    public String navigateToURL(String data, String value) {
        try {
            LogCapture.info("navigateToURL method called");
            Constants.driver.navigate().to(Constants.config.getProperty(data));
            LogCapture.info("navigateToURL method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("navigateToURL method failed");
            return Constants.FailedBlock;
        }
    }

    public String writeToElement(String data, String value) {
        try {
            LogCapture.info("writeToElement method called");
            visibleWaitCondition(data,"10");
            Constants.driver.findElement(By.xpath(data)).sendKeys(value);
            LogCapture.info("writeToElement method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("writeToElement method failed");
            return Constants.FailedBlock;
        }
    }


    public String selectTagDropDownValidation(String data, String value) {
        try {
            LogCapture.info("writeToElement method called");
            Select select = new Select(Constants.driver.findElement(By.xpath(data)));
            click(data,"");
            Arrays.asList(value.split("\\|")).forEach(a->select.selectByVisibleText(a));
            LogCapture.info("writeToElement method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("writeToElement method failed");
            return Constants.FailedBlock;
        }
    }

}
