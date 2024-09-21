package pageObjects;

import com.example.utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogCombiner;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MensShoppingMenu {
    WebDriver driver;

    @FindBy(xpath = "//button//i[@class='icon icon-close-alt']")
    WebElement CP_Mens_ClosePopup;

    @FindBy(xpath = "//li//span[text()='Jackets']")
    WebElement CP_Mens_Jacket_RadioBtn;

    @FindBy(xpath = "(//li[@class='simple-list hide-for-large']/div)[1]")
    WebElement CP_Mens_TotalPages;

    @FindBy(xpath = "(//li[@class='next-page']/a)[1]")
    WebElement CP_Mens_NextPage;

    @FindBy(xpath = "//div[@class='product-card row']")
    List<WebElement> CP_Mens_ProductCards;

    @FindBy(xpath = "//span[@class='lowest']//span[@class='money-value']")
    List<WebElement> CP_Mens_ProductPrices;

    @FindBy(xpath = "//div[@class='product-card-title']/a")
    List<WebElement> CP_Mens_ProductTitles;

    @FindBy(xpath = "//div[@class='product-vibrancy top-seller-vibrancy']/span")
    List<WebElement> CP_Mens_ProductMessage;

    public List<WebElement> getCP_Mens_ProductMessage() {
        return CP_Mens_ProductMessage;
    }

    public WebElement getCP_Mens_Jacket_RadioBtn() {
        return CP_Mens_Jacket_RadioBtn;
    }

    public WebElement getCP_Mens_TotalPages() {
        return CP_Mens_TotalPages;
    }

    public WebElement getCP_Mens_NextPage() {
        return CP_Mens_NextPage;
    }

    public List<WebElement> getCP_Mens_ProductCards() {
        return CP_Mens_ProductCards;
    }

    public List<WebElement> getCP_Mens_ProductPrices() {
        return CP_Mens_ProductPrices;
    }

    public List<WebElement> getCP_Mens_ProductTitles() {
        return CP_Mens_ProductTitles;
    }

    public WebElement getCP_Mens_ClosePopup() {
        return CP_Mens_ClosePopup;
    }

    public void setDriver() {
        this.driver = Constants.getDriver();
        PageFactory.initElements(Constants.getDriver(), this);
    }

    public void saveProductListData() throws Exception {
        Constants.setGenericString(Constants.getKey().returnDDMMYYSSString());
        try {
            int productCounter = 0;
            boolean isEnabled=true;
            List<String> localLst = new ArrayList<>();
            while(isEnabled) {
                isEnabled = Constants.getKey().verifyElementProperties(getCP_Mens_NextPage(), "enabled").equals("PASSED");
                int numberOfProducts = CP_Mens_ProductCards.size();
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx "+numberOfProducts);
                for (int i = 0; i < numberOfProducts; i++) {
                    productCounter++;
                    String title = CP_Mens_ProductTitles.get(i).getText();
                    String price = CP_Mens_ProductPrices.get(i).getText();
                    String specialMessage = "";
                    try {
                        specialMessage = CP_Mens_ProductMessage.get(i).getText();
                    } catch (Exception e) {
                        specialMessage = "<No Special Message>";
                    }
                    // Write each product's details to the file
                    System.out.println("=======================================================================================================");
                    localLst.add("--------------Below Product "+productCounter+"-----------------------------------");
                    localLst.add("Product Title: " + title);
                    localLst.add("Product Price: " + price);
                    localLst.add("Product Message: " + specialMessage);
                    System.out.println("======================================================================================================="+i);
                }
                Constants.getKey().click(getCP_Mens_NextPage(),"");
                // Re-fetch the elements after page reload
                CP_Mens_ProductCards = driver.findElements(By.xpath("//div[@class='product-card row']"));
                CP_Mens_ProductPrices = driver.findElements(By.xpath("//span[@class='lowest']//span[@class='money-value']"));
                CP_Mens_ProductTitles = driver.findElements(By.xpath("//div[@class='product-card-title']/a"));
                CP_Mens_ProductMessage = driver.findElements(By.xpath("//div[@class='product-vibrancy top-seller-vibrancy']/span"));
            }
            Constants.setGenericList(localLst);
            System.out.println("Product details successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logProductListDataToFile() throws Exception {
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

    public void handleCookiesAndPopups() throws Exception {
        String isVisibleCookie = Constants.getKey().verifyElementProperties(getCP_Mens_ClosePopup(),"visible");
        Constants.getKey().visibleWaitCondition(getCP_Mens_ClosePopup(),"visible");
        if(isVisibleCookie.equals("PASSED")){
            Constants.getKey().click(getCP_Mens_ClosePopup(),"");
        }
    }


}
