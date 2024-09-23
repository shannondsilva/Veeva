package com.example.stepDefinations;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.example.runner.testNGtestRunner;
import com.example.utils.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.utils.Reusables.LogCapture;

public class stepDefination  {

    @Given("User opens the {string} and loads the {string}")
    public void userOpensTheAndLoadsThe(String browser, String URL) {

        browser = (browser != "")
                ? browser
                : Constants.getConfig().getProperty("browser");
        LogCapture.info("Browser invoker is ---->" + browser);
        URL = Constants.getConfig().getProperty(URL);
        Constants.getKey().openBrowser(browser, URL);
    }

    @When("I am on the CP Home Page and do some quick element loaded validations")
    public void iAmOnTheCPHomePageAndDoSomeQuickElementLoadedValidations() throws Exception {

        Constants.getCP_POM().setDriver();
        Constants.getCP_POM().handleCookiesAndPopups();
        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getCP_POM().getCP_MainMenuBar(),"3"),"PASSED","ERROR >> Main menu NOT visible");
        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getCP_POM().getCP_SubMenuBar(),"3"),"PASSED","ERROR >> Sub menu NOT visible");

    }

    @And("I navigate to the Shop Menu and select {string} category")
    public void iNavigateToTheShopMenuAndSelectCategory(String shopMenuCategory) throws Exception {

        Assert.assertEquals(Constants.getKey().MouseFunctions(Constants.getCP_POM().getCP_ShopMenu(),"MoveToElement"),"PASSED","ERROR >> MoveToElement failed");
        switch (shopMenuCategory){
            case "Men's":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_POM().getCP_ShopMenu_Mens(),""),"PASSED","ERROR >> getCP_ShopMenu_Mens clicked failed");
                break;
            case "Women's":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_POM().getCP_ShopMenu_Women(),""),"PASSED","ERROR >> getCP_ShopMenu_Women clicked failed");
                break;
            case "Kids":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_POM().getCP_ShopMenu_Kids(),""),"PASSED","ERROR >> getCP_ShopMenu_Kids clicked failed");
                break;
            default:
                LogCapture.error("Incorrect Shop menu cart provided");
        }


    }

    @And("I find all {string} from all paginated pages")
    public void iFindAllFromAllPaginatedPages(String productType) throws Throwable {
        Constants.getCP_ShopMen_POM().setDriver();
        String mensPrdPgTitle=Constants.getConfig().getProperty("CP_MensProductPageTitle");
        Assert.assertEquals(Constants.getKey().switchToWindow(mensPrdPgTitle),"PASSED","ERROR >> Child window switch failed");
        Constants.getCP_ShopMen_POM().handleCookiesAndPopups();
        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getCP_ShopMen_POM().getCP_Mens_Jacket_RadioBtn(),"4"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn visibleWaitCondition failed");
        Assert.assertEquals(Constants.getKey().javascrpiptScroll(Constants.getCP_ShopMen_POM().getCP_Mens_Jacket_RadioBtn(),""),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn javascrpiptScroll failed");
        Assert.assertEquals(Constants.getKey().KeyboardAction(Constants.getCP_ShopMen_POM().getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.getKey().KeyboardAction(Constants.getCP_ShopMen_POM().getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.getKey().KeyboardAction(Constants.getCP_ShopMen_POM().getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.getKey().KeyboardAction(Constants.getCP_ShopMen_POM().getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");

        switch(productType){
            case "Jackets":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_ShopMen_POM().getCP_Mens_Jacket_RadioBtn(),""),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn clicked failed");
                break;
            case "Footwear":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_ShopMen_POM().getCP_Mens_Footwear_RadioBtn(),""),"PASSED","ERROR >> getCP_Mens_Footwear_RadioBtn clicked failed");
                break;
            case "Hats":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_ShopMen_POM().getCP_Mens_Hat_RadioBtn(),""),"PASSED","ERROR >> getCP_Mens_Hat_RadioBtn clicked failed");
                break;
            default:
                LogCapture.error("Please give a valid productType option");
        }



        Constants.getCP_ShopMen_POM().saveProductListData();
    }

    @Then("I store each Price, Title, and Top Seller message of the {string} and {string} in a text file")
    public void iStoreEachPriceTitleAndTopSellerMessageOfTheAndInATextFile(String ShopMenuCategory, String ProductType) throws Exception {
        Constants.getKey().logListDataToFile();
    }

    @And("I attach the generated text file to the test report")
    public void iAttachTheGeneratedTextFileToTheTestReport() {
        ExtentTest test = ExtentCucumberAdapter.getCurrentStep();
        test.log(Status.INFO, "Attached file: <a href='" +System.getProperty("user.dir")+ File.separator+"test-output"+File.separator+"logDump"+File.separator+"testLogs"+Constants.getGenericString()+".txt"+ "'>Check Log file</a>");
    }

    @And("I attach the generated csv file to the test report")
    public void iAttachTheGeneratedcsvFileToTheTestReport() {
        ExtentTest test = ExtentCucumberAdapter.getCurrentStep();
        test.log(Status.INFO, "Attached file: <a href='" +System.getProperty("user.dir")+ File.separator+"test-output"+File.separator+"logDump"+File.separator+Constants.getGenericString()+""+ "'>Check Log file</a>");
    }

    @And("I navigate to the three dotted Menu and select {string} category")
    public void iNavigateToTheThreeDottedMenuAndSelectCategory(String MenuCategory) throws Exception {

        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getCP_POM().getCP_ThreeDottedButton(),"4"),"PASSED","ERROR >> MoveToElement failed");
        Assert.assertEquals(Constants.getKey().MouseFunctions(Constants.getCP_POM().getCP_ThreeDottedButton(),"MoveToElement"),"PASSED","ERROR >> MoveToElement failed");
        switch (MenuCategory){
            case "News & Features":
                Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getCP_POM().getCP_NewsAndFeaturesLink(),"4"),"PASSED","ERROR >> getCP_ShopMenu_Mens clicked failed");
                Assert.assertEquals(Constants.getKey().javascrpiptClick(Constants.getCP_POM().getCP_NewsAndFeaturesLink(),""),"PASSED","ERROR >> getCP_ShopMenu_Mens clicked failed");
                break;
            case "Stay Connected":
                //Future implementations here
                break;
            default:
                LogCapture.error("Incorrect Shop menu cart provided");
        }

    }

    @Then("I navigate to the News page and count all video feeds {string} {string}")
    public void iNavigateToTheNewsPageAndValidateAllVideoFeeds(String operator, String days) throws Exception {
        Constants.getCP_NewsAndFeatures_POM().setDriver();
        Constants.getCP_NewsAndFeatures_POM().countVideoFeeds(operator, Integer.parseInt(days));
        Constants.getKey().logListDataToFile();
    }

    @And("I scroll to the bottom of the page to the footer section")
    public void iScrollToTheBottomOfThePageToTheFooterSection() throws Exception {

        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getDP2_LandingPage_POM().getDP2_Secondary_Menu(), "4"),"PASSED","ERROR >> MoveToElement failed");
//        Assert.assertEquals(Constants.getKey().KeyboardAction(Constants.getDP2_LandingPage_POM().getDP2_Footer(), "end"),"PASSED","ERROR >> KeyboardAction getDP2_LandingPage_POM failed");
        Assert.assertEquals(Constants.getKey().javascrpiptScroll(Constants.getDP2_LandingPage_POM().getDP2_Secondary_Menu(), ""),"PASSED","ERROR >> javascrpiptScroll getDP2_LandingPage_POM failed");

    }

    @And("I count the total hyperlinks in the DP footer section save these links into a csv file highlighting duplicates")
    public void iCountTheTotalHyperlinksInTheDPFooterSectionSaveTheseLinksIntoACsvFileHighlightingDeplucates() {
        Constants.getDP2_LandingPage_POM().countDP2FooterAndSave();
    }

    @When("I am on the DP Home Page and do some quick element loaded validations")
    public void iAmOnTheDPHomePageAndDoSomeQuickElementLoadedValidations() {
        Constants.getDP2_LandingPage_POM().setDriver();
        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getDP2_LandingPage_POM().getDP2_Secondary_Menu(),"3"),"PASSED","ERROR >> Main menu NOT visible");
        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getDP2_LandingPage_POM().getDP2_Logo(),"3"),"PASSED","ERROR >> Logo NOT visible");
        Assert.assertEquals(Constants.getKey().visibleWaitCondition(Constants.getDP2_LandingPage_POM().getDP2_Footer(),"3"),"PASSED","ERROR >> Footer NOT visible");
    }
}
