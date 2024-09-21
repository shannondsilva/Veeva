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


    @Given("User launches browser {string}")
    public void user_launches_browser(String browser) {

        String vObjBrowser = Constants.getConfig().getProperty("browser");
        Assert.assertEquals("PASSED",Constants.getKey().openBrowser(vObjBrowser,""));

    }
    @When("User enters {string} into the browser and lands on the page")
    public void user_enters_into_the_browser_and_lands_on_the_page(String url) {

        Assert.assertEquals("PASSED",Constants.getKey().navigateToURL(url,""));
    }


    ArrayList<String> storeMultiLevelElements = new ArrayList<>();
    @Given("User needs to find the chemical element count for {string}")
    public void userNeedsToFindTheChemicalElementCountFor(String formula) throws ClassNotFoundException {
//        formula = Mg(OH)2
        Class<?> clazzsy = Class.forName("com.example.stepDefinations.stepDefination");
        Field[] fld = clazzsy.getDeclaredFields();
        Constructor<?>[] ctr = clazzsy.getDeclaredConstructors();
        Method[] mtd = clazzsy.getDeclaredMethods();
        for(Field f: fld){
            String dd = f.getName();
           System.out.println(dd);
        }for(Constructor<?> c: ctr){
           System.out.println(c.getName());
        }for(Method m: mtd){
           System.out.println(m.getName());
        }

        if(formula.contains("(") || formula.contains(")")){
            int firstOccur = formula.indexOf("(");
            int lastOccur = formula.lastIndexOf(")");
            int globalMultiplier1;
            String remainingSimpleString;
            if(Character.isDigit(formula.charAt(lastOccur+1))) {
                 globalMultiplier1 = Integer.parseInt(String.valueOf(formula.charAt(lastOccur + 1)));
                 remainingSimpleString = formula.replace(formula.substring(firstOccur,lastOccur+2),"");
                Map<String, Integer> elementCnt = simpleFormulaeExtraction(remainingSimpleString);
                LogCapture.info("");
            }else{
                remainingSimpleString = formula.replace(formula.substring(firstOccur,lastOccur+1),"");
            }
            String firstLevelString = formula.substring(firstOccur+1,lastOccur);
            userNeedsToFindTheChemicalElementCountFor(firstLevelString);
            if(Character.isDigit(formula.charAt(lastOccur+2))){

            }
            LogCapture.info("");
        }
        else {
            Map<String, Integer> elementCnt = simpleFormulaeExtraction(formula);
            LogCapture.info("Here is you element count --> " + elementCnt);
        }
    }

    private Map<String, Integer> simpleFormulaeExtraction(String formula) {
        Map<String, Integer> elementCnt = new HashMap<>();
        for(int i = 0; i< formula.length(); i++){
            if(Character.isUpperCase(formula.charAt(i)) ){
                int totalChar = formula.length();
                String eleShort;
                int eleCnt;
                if(totalChar> i+1 && Character.isLowerCase(formula.charAt(i+1))){
                    eleShort=Character.toString(formula.charAt(i))+Character.toString(formula.charAt(i+1));
                }else{
                    eleShort= Character.toString(formula.charAt(i));
                }

                if(totalChar> i+1 && Character.isDigit(formula.charAt(i+1))){
                    eleCnt = Character.getNumericValue(formula.charAt(i+1));
                }else if(totalChar> i+1 && totalChar> i+2 && Character.isDigit(formula.charAt(i+2)) && !Character.isUpperCase(formula.charAt(i+1))){
                    eleCnt = Character.getNumericValue(formula.charAt(i+2));
                }else {
                    if (elementCnt.containsKey(eleShort)) {
                        eleCnt = elementCnt.get(eleShort) + 1;
                    }
                    else{
                        eleCnt = 1;
                    }
                }
                elementCnt.put(eleShort,eleCnt);
            }

        }
        return elementCnt;
    }



    @Given("Find Duplicates in an Array")
    public void findDuplicatesInAnArray() {

        int[] x = {1,2,2,3,1,6,4,6,76,7,0};
        Map<Integer, Integer>  duplicateCount = new HashMap<>();

        for(int i=0; i<x.length; i++){
            if(duplicateCount.containsKey(x[i])){
                duplicateCount.put(x[i],duplicateCount.get(x[i])+1);
            }
            else{
                duplicateCount.put(x[i],1);
            }
        }
        LogCapture.info(duplicateCount);
        int i = 8;
        //1,3,5,7,11,13,17,19,23,29
        boolean isPrime=false;
        for(int y = 2; y<=i/2; y++){
            if(i%y==0){
                isPrime=false;
                break;
            }
            else{
                isPrime=true;
            }
        }
        LogCapture.info(isPrime ? "PRIME NUMBER" : "NOT PRIME");




    }

    @Given("To test my docker knowledge")
    public void toTestMyDockerKnowledge() throws MalformedURLException, InterruptedException {

        try {


            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("browserName","chrome");
            Thread.sleep(10000);
            RemoteWebDriver rDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
            rDriver.get("https://goodgoodpiggy.com/");
            LogCapture.info(rDriver.getTitle());
            Thread.sleep(20000);
            rDriver.quit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }


    @Given("BAT file executions for docker up")
    public void batFileExecutionsForDockerUp() throws IOException {

        Runtime rt = Runtime.getRuntime();
        File file = new File("output.txt");
        File fileDown = new File("outputDown.txt");
        if(file.exists()){
            LogCapture.info("Deleting output.txt file...");
            file.delete();
        }
        if(fileDown.exists()){
            LogCapture.info("Deleting outputDown.txt file...");
            fileDown.delete();
        }
        rt.exec("cmd /c start dockerUp.bat");
        while(!file.exists()){
            LogCapture.info("Waiting for output.txt to generate.");
            file = new File("output.txt");
        }
        BufferedReader br = new BufferedReader(new FileReader("output.txt"));
        String output = br.readLine();
        while(output==null){
            LogCapture.info("output.txt is still empty");
            output = br.readLine();
        }
        while(output != null && !output.contains("Node has been added")){
            LogCapture.info(output);
            output = br.readLine();
        }

        //Perform some actions

        rt.exec("cmd /c start dockerDown.bat");

        while(!fileDown.exists()){
            LogCapture.info("Waiting for outputDown.txt to generate.");
        }
        BufferedReader brClose = new BufferedReader(new FileReader("outputDown.txt"));

        while(brClose.readLine()==null){
            LogCapture.info("outputDown.txt is still empty");
        }
        while(output != null && !brClose.readLine().contains("Running 13/13")){
            LogCapture.info("Still waiting for outputDown nodes to come  up");
        }




    }

    @Given("Demo test program")
    public void demoTestProgram() throws FileNotFoundException {
        String str = "3409tnnj3t%#vonjjvnrshannonopi040un3tjceshanno8wntcwkc0949i9t4wcshannon094utcm";
        String strCompare="shannon";
        int numberOfOccurences=0;
        for(int i=0; i<str.length();i++){
            if(str.charAt(i)==strCompare.charAt(0)){
                boolean isFullTextMatch=false;
                for(int j = 1; j <strCompare.length(); j++){
                    if((i+j)<str.length() && str.charAt(i+j)==strCompare.charAt(j)){
                        isFullTextMatch=true;
                    }else {
                        isFullTextMatch=false;
                        break;
                    }
                }
                if(isFullTextMatch){
                    numberOfOccurences++;
                }
            }
        }
        LogCapture.info("Number of occurence --> "+numberOfOccurences);
    }

    public void test(){
//        Map<String,String> mp = new HashMap<>();
//        Set<Map.Entry<String,String>> es = mp.entrySet();
//        Map<String,String> ht = new Hashtable<>();
//         Iterator<Map.Entry<String,String>> io  = ht.entrySet().iterator();
//         io.next().getKey();
//
//        for(Map.Entry<String,String> etry:mp.entrySet()){
//
//        }
//
//        Set<String> st = new HashSet<>();
//        st.add(null);
//        st.add(null);
//
//        Set<String> ts = new TreeSet<>();
//        ts.add(null);

        List<Integer> arr = Arrays.asList(1,3,4,4,1,2,4,5,7);
        arr.stream().distinct().forEach(s->LogCapture.info(" ----------> "+s));

    }


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

    @And("I navigate to the three dotted Menu and select {string} category")
    public void iNavigateToTheThreeDottedMenuAndSelectCategory(String MenuCategory) throws Exception {

        Assert.assertEquals(Constants.getKey().MouseFunctions(Constants.getCP_POM().getCP_ThreeDottedButton(),"MoveToElement"),"PASSED","ERROR >> MoveToElement failed");
        switch (MenuCategory){
            case "News & Features":
                Assert.assertEquals(Constants.getKey().click(Constants.getCP_POM().getCP_NewsAndFeaturesLink(),""),"PASSED","ERROR >> getCP_ShopMenu_Mens clicked failed");
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
}
