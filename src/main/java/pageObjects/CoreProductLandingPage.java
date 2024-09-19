package pageObjects;

import com.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoreProductLandingPage {
    WebDriver driver;

    @FindBy(xpath="//img[@src='https://cdn.nba.com/logos/leagues/L/logo-nba.svg']")
    WebElement CP_Logo;
    @FindBy(xpath="(//ul[@role='menubar'])[1]")
    WebElement CP_MainMenuBar;

    @FindBy(xpath="(//ul[@role='menubar'])[2]")
    WebElement CP_SubMenuBar;

    @FindBy(xpath="//a[@href='https://shop.warriors.com/']")
    WebElement CP_ShopMenu;

    @FindBy(xpath="//a[@href='https://shop.warriors.com/']//following-sibling::nav//a[text()=\"Men's\"]")
    WebElement CP_ShopMenu_Mens;


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

    //This method needs to compulsory be called immediately after initiating the webDriver
    public void setDriver() {
        this.driver = Constants.driver;
        PageFactory.initElements(Constants.driver,this);
    }


}
