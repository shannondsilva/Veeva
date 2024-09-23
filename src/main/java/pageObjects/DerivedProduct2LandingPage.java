package pageObjects;

import com.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DerivedProduct2LandingPage {
    WebDriver driver;

    @FindBy(xpath = "//img[@src='https://cdn.nba.com/logos/leagues/L/logo-nba.svg']")
    WebElement DP2_Logo;
    @FindBy(tagName = "footer")
    WebElement DP2_Footer;
    @FindBy(xpath = "//footer//a")
    List<WebElement> DP2_Footer_HyperLinks;
    @FindBy(xpath = "//nav[@aria-label='Chicago Bulls navigation']")
    WebElement DP2_Secondary_Menu;

    public WebElement getDP2_Logo() {
        return DP2_Logo;
    }
    public WebElement getDP2_Footer() {
        return DP2_Footer;
    }
    public WebElement getDP2_Secondary_Menu() {
        return DP2_Secondary_Menu;
    }

    public List<WebElement> getDP2_Footer_HyperLinks() {
        return DP2_Footer_HyperLinks;
    }
    public void countDP2FooterAndSave(){

        Map<String, Integer> elementCount = new HashMap<>();
        for (WebElement element : getDP2_Footer_HyperLinks()) {
            elementCount.put(element.getAttribute("href"), elementCount.getOrDefault(element.getAttribute("href"), 0) + 1);
        }
        // Write duplicates to CSV and highlight them
        writeDuplicatesToCSV(elementCount);
    }

    private static void writeDuplicatesToCSV(Map<String, Integer> elementTextCount) {
        String csvFile = "duplicates_web_elements_"+Constants.getKey().returnDDMMYYSSString()+".csv";
        Constants.setGenericString(csvFile);
        try (FileWriter writer = new FileWriter(System.getProperty("user.dir")+ File.separator +"test-output"+File.separator+"logDump"+File.separator+csvFile)) {
            writer.append("Element Text,Count,Duplicate\n"); // CSV headers

            for (Map.Entry<String, Integer> entry : elementTextCount.entrySet()) {
                String elementText = entry.getKey();
                int count = entry.getValue();
                // Check if it's a duplicate
                String isDuplicate = count > 1 ? "Yes" : "No";
                // Write each element with its count and duplication status
                writer.append(elementText).append(",")
                        .append(String.valueOf(count)).append(",")
                        .append(isDuplicate).append("\n");
            }
            System.out.println("CSV file generated successfully: " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDriver() {
        this.driver = Constants.getDriver();
        PageFactory.initElements(Constants.getDriver(), this);
    }
}
