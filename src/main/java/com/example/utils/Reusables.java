package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

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

            switch (data.toLowerCase()) {
                case "chrome" -> Constants.setDriver(new ChromeDriver(options));
                case "edge" -> Constants.setDriver(new EdgeDriver());
                case "firefox" -> Constants.setDriver(new FirefoxDriver());
            }
            Constants.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Constants.getDriver().manage().window().maximize();
            Constants.getDriver().get(value);
            LogCapture.info("openBrowser method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("openBrowser method failed");
            return Constants.FailedBlock;
        }
    }


    public String click(WebElement element, String value) {
        try {
            LogCapture.info("click method called");
            element.click();
            LogCapture.info("click method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("click method failed");
            return Constants.FailedBlock;
        }
    }

    public String visibleWaitCondition(WebElement element, String value) {
        try {
            LogCapture.info("visibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.getDriver(), Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.visibilityOf(element));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("visibleWaitCondition method failed :"+e);
            return Constants.FailedBlock;
        }
    }

    public String verifyElementProperties(WebElement element, String data) throws Exception {
        try {
            LogCapture.info("verifyElementProperties method called");
            switch (data) {
                case "visible":
                    try {
                        element.isDisplayed();
                        LogCapture.info("element is visible");
                        return Constants.PassedBlock;
                    } catch (Exception e) {
                        LogCapture.error("element is NOT visible");
                        return Constants.FailedBlock;
                    }
                case "enabled":
                    if(element.getAttribute("enabled")==null){
                        LogCapture.info("element is enabled");
                        return Constants.PassedBlock;
                    }else {
                        LogCapture.error("element is NOT enabled");
                        return Constants.FailedBlock;
                    }
                default:
                    LogCapture.error("No property matched");
                    LogCapture.info("verifyElementProperties method executed");
                    return Constants.FailedBlock;
            }
        } catch (Exception e) {
            return Constants.FailedBlock;
        }
    }

    public String KeyboardAction(WebElement element, String data) throws Exception {
        try {
            if (data.equalsIgnoreCase("enter")) {
                element.sendKeys(Keys.ENTER);
            } else if (data.equalsIgnoreCase("tab")) {
                element.sendKeys(Keys.TAB);
            } else if (data.equalsIgnoreCase("space")) {
                element.sendKeys(Keys.SPACE);
            } else if (data.equalsIgnoreCase("downArrow")) {
                element.sendKeys(Keys.ARROW_DOWN);
            } else if (data.equalsIgnoreCase("selectall")) {
                element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            } else if (data.equalsIgnoreCase("delete")) {
                element.sendKeys(Keys.DELETE);
            } else if (data.equalsIgnoreCase("upArrow")) {
                element.sendKeys(Keys.ARROW_UP);
            } else if (data.equalsIgnoreCase("esc")) {
                System.out.println("esc");
                element.sendKeys(Keys.ESCAPE);
            } else if (data.equalsIgnoreCase("backSpace")) {
                element.sendKeys(Keys.BACK_SPACE);
            } else if (data.equalsIgnoreCase("ctrl-a")) {
                element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            } else if (data.equalsIgnoreCase("page-down")) {
                element.sendKeys(Keys.PAGE_DOWN);
            } else if (data.equalsIgnoreCase("end")) {
                element.sendKeys(Keys.END);
            } else if (data.equalsIgnoreCase("Home")) {
                element.sendKeys(Keys.HOME);
            }


        } catch (Exception e) {
            return Constants.FailedBlock;

        }
        return Constants.PassedBlock;
    }

    public String MouseFunctions(WebElement element, String data) throws Exception {
        try {
            Actions action = new Actions(Constants.getDriver());
            if (data.equalsIgnoreCase("clickAndHold")) {
                action.moveToElement(element).build().perform();
                action.clickAndHold(element).build().perform();
                return Constants.PassedBlock;
            } else if (data.equalsIgnoreCase("ReleaseMouseClick")) {
                action.moveToElement(element).build().perform();
                action.release(element).build().perform();
                return Constants.PassedBlock;
            } else if (data.equalsIgnoreCase("DoubleClick")) {
                action.doubleClick(element).build().perform();
                return Constants.PassedBlock;
            } else if (data.equalsIgnoreCase("MoveToElement")) {
                action.moveToElement(element);
                action.perform();
                return Constants.PassedBlock;
            } else if (data.equalsIgnoreCase("RightClick")) {
                action.contextClick(element).build().perform();
                return Constants.PassedBlock;
            } else if (data.equalsIgnoreCase("MoveToElementClick")) {
                action.moveToElement(element).click();
                action.perform();
                return Constants.PassedBlock;
            } else if (data.equalsIgnoreCase("click")) {
                action.click(element).build().perform();
                return Constants.PassedBlock;
            }
            return Constants.FailedBlock;
        } catch (Exception e) {

        }
        return Constants.FailedBlock;
    }

    public String switchToWindow(String data) throws Throwable {
        String title;
        try {
            String mainWindowHandle = Constants.getDriver().getWindowHandle();
            Set<String> allWindowHandles = Constants.getDriver().getWindowHandles();
            Iterator<String> iterator = allWindowHandles.iterator();
            while (iterator.hasNext()) {
                String ChildWindow = iterator.next();
                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                    Constants.getDriver().switchTo().window(ChildWindow);
                    title = Constants.getDriver().switchTo().window(ChildWindow).getTitle();
                    System.out.println("Window title " + title);
                    if (title.equalsIgnoreCase(data)) {
                        LogCapture.info("User successfully verify window title" + title);
                        break;
                    }
                }
            }
            return Constants.PassedBlock;

        } catch (Exception ex) {
            LogCapture.error(".....Switch window is not working...  " + ex);
            return Constants.FailedBlock;
        }
    }

    public String getText(WebElement element, String data) throws Exception {
        String actual;
        try {
            actual = element.getText();
            if (actual.length() < 1) {
                actual = element.getAttribute("value");
                System.out.println("Actual Length:->" + actual.length());
            }
            LogCapture.info("Actual:->" + actual);
        } catch (Exception e) {
            return Constants.FailedBlock;
        }
        return actual;
    }

    public String javascrpiptScroll(WebElement element, String data) throws Exception {
        try {
            String js = "arguments[0].scrollIntoView();";
            ((JavascriptExecutor) Constants.getDriver()).executeScript(js, element);
        } catch (Exception e) {
            return Constants.FailedBlock + "object does not exist " + e.getMessage();
        }
        return Constants.PassedBlock;
    }

    public String InvisibleWaitCondition(WebElement element, String value) {
        try {
            LogCapture.info("InvisibleWaitCondition method called");
            WebDriverWait wait = new WebDriverWait(Constants.getDriver(), Duration.ofSeconds(Long.parseLong(value)));
            wait.until(ExpectedConditions.visibilityOf(element));
            LogCapture.info("visibleWaitCondition method executed");
            return Constants.PassedBlock;
        } catch (Exception e) {
            LogCapture.error("InvisibleWaitCondition method failed");
            return Constants.FailedBlock;
        }
    }

    public String returnDDMMYYSSString() {
        try {
            LogCapture.info("returnDDMMYYSSString method called");
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyssSSS");
            Date now = new Date();
            LogCapture.info("returnDDMMYYSSString method executed");
            return formatter.format(now);
        } catch (Exception e) {
            LogCapture.error("returnDDMMYYSSString method failed");
            return Constants.FailedBlock;
        }
    }

    public String getAttributeText(WebElement element, String attribute) {
        try {
            LogCapture.info("getAttributeText method called");
            return element.getAttribute(attribute);
        } catch (Exception e) {
            LogCapture.error("getAttributeText method failed");
            return Constants.FailedBlock;
        }
    }

    public void logListDataToFile() throws Exception {
        Constants.setGenericString(Constants.getKey().returnDDMMYYSSString());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "logDump" + File.separator + "testLogs" + Constants.getGenericString() + ".txt"))) {
            Constants.getGenericList().stream().forEach(element -> {
                try {
                    writer.write(element);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
