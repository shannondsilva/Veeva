package pageObjects;

import com.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoreProductLandingPage {
    WebDriver driver;

    @FindBy(xpath = "//img[@src='https://cdn.nba.com/logos/leagues/L/logo-nba.svg']")
    WebElement CP_Logo;
    @FindBy(xpath = "(//ul[@role='menubar'])[1]")
    WebElement CP_MainMenuBar;

    @FindBy(xpath = "(//ul[@role='menubar'])[2]")
    WebElement CP_SubMenuBar;

    @FindBy(xpath = "//a[@href='https://shop.warriors.com/']")
    WebElement CP_ShopMenu;

    @FindBy(xpath = "//a[@href='https://shop.warriors.com/']//following-sibling::nav//a[text()=\"Men's\"]")
    WebElement CP_ShopMenu_Mens;

    @FindBy(xpath = "//a[@href='https://shop.warriors.com/']//following-sibling::nav//a[text()=\"Women's\"]")
    WebElement CP_ShopMenu_Women;

    @FindBy(xpath = "//a[@href='https://shop.warriors.com/']//following-sibling::nav//a[text()=\"Kids\"]")
    WebElement CP_ShopMenu_Kids;
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement CP_OneTrustCookieAccept;

    @FindBy(xpath = "//div[@role='dialog']//div[text()='x']")
    WebElement CP_CancelPopup;

    public WebElement getCP_ShopMenu_Women() {
        return CP_ShopMenu_Women;
    }

    public WebElement getCP_ShopMenu_Kids() {
        return CP_ShopMenu_Kids;
    }

    public WebElement getCP_ThreeDottedButton() {
        return CP_ThreeDottedButton;
    }

    @FindBy(xpath = "//nav[@aria-label='header-secondary-menu']//span")
    WebElement CP_ThreeDottedButton;

    @FindBy(xpath = "(//a[text()='News & Features'])[1]")
    WebElement CP_NewsAndFeaturesLink;

    public WebElement getCP_NewsAndFeaturesLink() {
        return CP_NewsAndFeaturesLink;
    }

    public WebElement getCP_Logo() {
        return CP_Logo;
    }

    public WebElement getCP_MainMenuBar() {
        return CP_MainMenuBar;
    }

    public WebElement getCP_SubMenuBar() {
        return CP_SubMenuBar;
    }

    public WebElement getCP_ShopMenu() {
        return CP_ShopMenu;
    }

    public WebElement getCP_ShopMenu_Mens() {
        return CP_ShopMenu_Mens;
    }

    public WebElement getCP_OneTrustCookieAccept() {
        return CP_OneTrustCookieAccept;
    }

    public WebElement getCP_CancelPopup() {
        return CP_CancelPopup;
    }

    public void setDriver() {
        this.driver = Constants.getDriver();
        PageFactory.initElements(Constants.getDriver(), this);
    }

    public void handleCookiesAndPopups() throws Exception {
        String isVisibleCookie = Constants.getKey().verifyElementProperties(getCP_OneTrustCookieAccept(), "visible");
        String isVisiblePopup = Constants.getKey().verifyElementProperties(getCP_CancelPopup(), "visible");
        if (isVisibleCookie.equals("PASSED")) {
            Constants.getKey().click(getCP_OneTrustCookieAccept(), "");
        }
        if (isVisiblePopup.equals("PASSED")) {
            Constants.getKey().click(getCP_CancelPopup(), "");
        }
    }

}
