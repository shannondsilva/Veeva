package pageObjects;

import com.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.utils.Reusables.LogCapture;

public class NewsAndFeaturesPage {
    WebDriver driver;
    @FindBy(xpath = "//a[contains(@href,'videos')]//ancestor::li//time")
    List<WebElement> CP_News_VideoLinks;

    public List<WebElement> getCP_News_VideoLinks() {
        return CP_News_VideoLinks;
    }



    public void setDriver() {
        this.driver = Constants.getDriver();
        PageFactory.initElements(Constants.getDriver(), this);
    }

    public void countVideoFeeds(String operator, int days) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH);
        LocalDate today = LocalDate.now();
        List<String> localLst = new ArrayList<>();
        for (WebElement e : getCP_News_VideoLinks()) {
            String dateText = Constants.getKey().getAttributeText(e,"datetime");
            try {
                LocalDate videoDate = dateFormat.parse(dateText).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                long daysDifference = ChronoUnit.DAYS.between(videoDate, today);
                if (evaluateCondition(daysDifference, operator, days)) {
                    LogCapture.info("Video posted on " + dateText + " matches the condition (" + operator + days + "). Difference: " + daysDifference + " days.");
                    localLst.add("-------------------------------------------------------------------------------------------");
                    localLst.add("Video posted on " + dateText + " matches the condition (" + operator + days + "). Difference: " + daysDifference + " days.");
                }
            } catch (ParseException pe) {
                LogCapture.error("Error parsing video feed date: " + dateText, pe);
            }
        }
        Constants.setGenericList(localLst);
    }

    private boolean evaluateCondition(long daysDifference, String operator, int days) {
        switch (operator) {
            case ">":
                return daysDifference > days;
            case ">=":
                return daysDifference >= days;
            case "<":
                return daysDifference < days;
            case "<=":
                return daysDifference <= days;
            case "=":
            case "==":
                return daysDifference == days;
            case "!=":
                return daysDifference != days;
            default:
                LogCapture.error("Invalid operator: " + operator);
                return false;
        }
    }

}
