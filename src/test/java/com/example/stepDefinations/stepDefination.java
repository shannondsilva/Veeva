package com.example.stepDefinations;

import com.example.utils.*;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.log.Log;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import io.cucumber.java.it.Ma;
import io.cucumber.java.sl.In;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.example.utils.Reusables.LogCapture;

public class stepDefination  {


    @Given("User launches browser {string}")
    public void user_launches_browser(String browser) {

        String vObjBrowser = Constants.config.getProperty("browser");
        Assert.assertEquals("PASSED",Constants.key.openBrowser(vObjBrowser,""));

    }
    @When("User enters {string} into the browser and lands on the page")
    public void user_enters_into_the_browser_and_lands_on_the_page(String url) {

        Assert.assertEquals("PASSED",Constants.key.navigateToURL(url,""));
    }
    @Then("User check for the home and books field")
    public void user_check_for_the_username_and_password_field() {

        String vObjbooks = Constants.applicationProperty.getProperty("books");
        String vObjhome = Constants.applicationProperty.getProperty("home");

        Assert.assertEquals("PASSED",Constants.key.visibleWaitCondition(vObjbooks,"3"));
        Assert.assertEquals("PASSED",Constants.key.visibleWaitCondition(vObjhome,"3"));

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

    @Given("Run my naukri.com profile")
    public void runMyNaukriComProfile() {

        Constants.key.openBrowser("Chrome","");
        Constants.driver.get("https://www.naukri.com/");
        Constants.key.visibleWaitCondition("//a[@id='login_Layer']","10");
        Constants.key.click("//a[@id='login_Layer']","");

        Constants.key.writeToElement("//input[@placeholder='Enter your active Email ID / Username']","shannondsilva001@gmail.com");
        Constants.key.writeToElement("//input[@placeholder='Enter your password']","shannon2681994");
        Constants.key.click("//button[@type='submit']","");
        Constants.key.visibleWaitCondition("//div[text()='Profile performance']","10");



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

    @Given("Demo test StreamsAndLamda")
    public void demoTestStreamsAndLamda() {

        List<Integer> arr = Arrays.asList(1,3,4,4,1,2,4,5,7);
        arr.stream().distinct().sorted().forEach(s->LogCapture.info(" ----------> "+s));


        List<Integer> mp = arr.stream().distinct().sorted().collect(Collectors.toList());
        LogCapture.info("---MP----->"+mp);

        List<String> str = Arrays.asList("wrfsfsf","wcfsd","rfcaef","rfasfsd");
        List<String> mp1 = Stream.of("dsf","tfds","gsrg","sgrsgs").filter(s->s.startsWith("s")).map(s->s.toUpperCase()).collect(Collectors.toList());
        LogCapture.info("---MP----->"+mp1);

//        Problem: Given a list of integers, find the sum of all even numbers.
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int d = numbers.stream().filter(s->s%2==0).mapToInt(s->s.intValue()).sum();
        LogCapture.info("d---->"+d);

//        Problem: Given a list of strings, convert all strings to uppercase.
        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        List<String> lst = strings.stream().map(s->s.toUpperCase()).collect(Collectors.toList());
        LogCapture.info("lst---->"+lst);

//        Problem: Given a list of strings, find the longest string.
        List<String> stringsFruits = Arrays.asList("apple", "banana", "cherry", "date");
        String maxWord = stringsFruits.stream().max(Comparator.comparingInt(s->s.length())).orElse(null);
        LogCapture.info("maxWord---->"+maxWord);


//        Problem: Given a list of strings, group them by their lengths.
        List<String> stringsGroup = Arrays.asList("apple", "banana", "cherry", "date", "fig", "grape");
        Map<Integer,List<String>> stringsGroupMap = stringsGroup.stream().collect(Collectors.groupingBy(s->s.length()));
        LogCapture.info("stringsGroupMap---->"+stringsGroupMap);


//        Problem: Given a list of integers, find all distinct elements.
        List<Integer> numbersDistinct = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10);
        List<Integer> distinctList = numbersDistinct.stream().distinct().collect(Collectors.toList());
        LogCapture.info("distinctList---->"+distinctList);


//        Problem: Given a list of integers, find the first element greater than 5.
        List<Integer> numbersFirstGreat = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int firstGreater = numbersFirstGreat.stream().filter(s->s>5).findFirst().orElse(null);
        LogCapture.info("firstGreater---->"+firstGreater);


//        Problem: Given a list of lists, flatten it into a single list.
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );

        List<Integer> flatList = listOfLists.stream().flatMap(s->s.stream()).collect(Collectors.toList());
        LogCapture.info("flatList---->"+flatList);


//        Problem: Given a list of integers, partition the list into even and odd numbers.
        List<Integer> numbersPartition = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean,List<Integer>> evenOddList = numbersPartition.stream().collect(Collectors.partitioningBy(s->s%2==0));
        LogCapture.info("evenOddList---->"+evenOddList);

//        Problem: Given a list of strings, count the frequency of each string.
        List<String> stringsfrequency = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
        Map<String, Long> frequencyMap=stringsfrequency.stream().collect(Collectors.groupingBy(s->s,Collectors.counting()));
        LogCapture.info("frequencyMap---->"+frequencyMap);

//        Problem: Given a list of integers, find the second highest number.
        List<Integer> numbers2ndHighest = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer SecndHigh = numbers2ndHighest.stream().sorted().skip(2).findFirst().orElse(null);
        LogCapture.info("SecndHigh---->"+SecndHigh);


//        Given an Array , WAP to print all the odd numbers before and then followed by the even numbers, in a sorted manner

        int[] a={2,4,5,6,7,3,1};
        Map<Boolean, List<Integer>> mp12 = Arrays.stream(a).boxed().sorted().collect(Collectors.partitioningBy(s->s%2==0));
        LogCapture.info("mp12 ---------------> "+mp12);
        List<Integer> lst123 = new ArrayList<>();
        lst123.addAll(mp12.get(false));
        lst123.addAll(mp12.get(true));
        LogCapture.info("lst123 ---------------> "+lst123);


        List<Integer> lsts = Arrays.asList(1,4,7,3,1,4,6);
        lsts.stream().filter(s->Collections.frequency(lsts,1) > 1).collect(Collectors.toList());

        List<String> rShetty = Arrays.asList("a","g","y","a");
        rShetty.stream().filter(s->s.startsWith("a")).map(String::toUpperCase).forEach(System.out::println);


//        String str = "publicissapient"; // first non repeated characterd // 1st repeated characters
//        int totalLnt = str.length();
//        for(int i=0; i< str.length(); i++){
//            String x = String.valueOf(str.charAt(i));
//            String newStr = str.replaceAll(x,"");
//            if(newStr.length()<=totalLnt-2){
//                System.out.println("1st repeating character = "+x);
//                break;
//            }
//        }
//
//        for(int i=0; i< str.length(); i++){
//            String x = String.valueOf(str.charAt(i));
//            String newStr = str.replaceAll(x,"");
//            if(newStr.length()==totalLnt-1){
//                System.out.println("1st NON-repeating character = "+x);
//                break;
//            }
//        }
//
//        //{1,2,4,1,0,9,2,0,8} --> {1,2,4,1,9,2,0,0,0}
//
//        int[] arr = {1,2,4,1,0,9,2,0,0,8};
//        int index=0;
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] != 0) {
//                arr[index++] = arr[i];
//            }
//        }
//        while (index < arr.length) {
//            arr[index++] = 0;
//        }
//
////        r=4
////
////            1
////           212
////          32123
////         4321234
//
//        int pyramidSize=4;
//        for(int i = 0 ; i < pyramidSize; i++){
//            int cnt=0;
//            for(int j=1; j<pyramidSize-i;j++){
//                System.out.print(" ");
//                cnt++;
//            }
//            for(int a = pyramidSize; a > cnt; a--){
//                System.out.print(a-cnt);
//            }
//            for(int a = pyramidSize-1; a > cnt; a--){
//                System.out.print(pyramidSize-a+1);
//            }
//            System.out.println("");
//        }
    }


    @Given("Demo test fabonacciSeries {int}")
    public void demoTestFabonacciSeries(int num) {
//        0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 || num = 10
        int[] fab = new int[num];
        fab[0] = 0;
        if(num>1){
            fab[1] = 1;
        }
        for(int i = 2 ; i < num ; i++){

            fab[i] = fab[i-1] + fab[i-2];
        }

        for(int i = 0 ; i < fab.length ; i++){

            LogCapture.info("-->"+fab[i]);
        }

    }

    @Given("Demo test SortedArrays")
    public void demoTestSortedArrays() {

//        arr1 --> 1,2,3,4,5
//        arr2 --> 1,3,5,7,9
//        o/p --> 1,1,2,3,3,4,5,5,7,9

        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,3,5,7,9,10};

//        1,2,3,4,5,1,3,5,7,9,10

        List<Integer> finalArrLst = new ArrayList<>();
        for(int i = 0 ; i < arr1.length ; i++){
            finalArrLst.add(arr1[i]);
        }
        for(int j = 0 ; j < arr2.length ; j++){
            finalArrLst.add(arr2[j]);
        }

        List<Integer> finals = finalArrLst.stream().sorted().collect(Collectors.toList());
        LogCapture.info("-->"+finals);

    }

    @Given("Demo test binarySearch")
    public void demoTestBinarySearch() {
        int searchParam = 7;
        int[] a = {1,2,3,4,5,6,7,8};

        LogCapture.info("binary result --> "+binarySearch(a,7));
    }

    public int binarySearch(int[] a, int searchParam){
//        int[] a = {1,2,3,4,5,6,7,8};
        int left = 0;
        int right = a.length -1;
        int cntr=0;
        while(left<=right) {
            cntr++;
            LogCapture.info(" cntr-->"+cntr);
            int mid = (left + right) / 2;
            if (a[mid] == searchParam) {
                return mid;
            }

            if (searchParam > a[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    @Given("find the missing number")
    public void findTheMissingNumber() {

        int[] a = {1,2,3,4,5,6,7,9}; // 18


        int originalSum=0;
        int actualSum=0;
        for(int i=0; i < a.length;i++){
            originalSum+=a[i];
        }
        int max = a[a.length-1];

        for(int j = 1 ; j <= max; j++){
            actualSum+=j;
        }

        System.out.println("Missing element-->"+(actualSum-originalSum));



    }

    @Given("find the Anagram")
    public void findTheAnagram() {
        String str1 = "aabbcc";
        String str2 = "abcacb";

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        System.out.println("Are strings Anagram s1 "+Integer.MIN_VALUE);

        System.out.println("Are strings Anagram "+Arrays.equals(s1,s2));




    }

    @Given("Find the longest common prefix in an array of strings.")
    public void findTheLongestCommonPrefixInAnArrayOfStrings() {

        String[] arr = {"shannon", "shanti", "shalom"};
        int i = 0;
        int min = arr[0].length();
        StringBuilder sb = new StringBuilder();
        Character k = null;
        for (String s : arr) {
            if (s.length()<min){
                min=s.length();
            }
        }
        boolean isMatch=true;
        while (i < min){
            for (String s : arr) {
                if (k == null) {
                    k = s.charAt(i);
                } else if (s.charAt(i) == k) {
                    k = s.charAt(i);
                }else{
                    isMatch=false;
                }
            }
            i++;
            if(isMatch) {
                sb.append(k);
            }
            k=null;
    }
        System.out.println("Here is the max prefix -->"+sb);

    }

    @Given("Remove duplicates from a sorted array in-place.")
    public void removeDuplicatesFromASortedArrayInPlace() {

        int[] arr = {1,2,4,6,8,8,9};
        Arrays.sort(arr);

        Set<Integer> s = new LinkedHashSet<>();
        for(Integer i : arr){
            s.add(i);
        }

        System.out.println("My Set --> "+s);

    }

    @Given("Find the element that appears more than n divide {int} times in an array of size n.")
    public void findTheElementThatAppearsMoreThanNDivideTimesInAnArrayOfSizeN(int arg0) {

            int[] arr = {2, 2, 1, 1, 1, 2, 2,1,1,1,1,1,1,1};

            int arrSize = arr.length/2;

            //HM --> Key(count) -- value(element data)


            Map<Integer,Integer> HM = new HashMap<>();
            int d=0;
            for(int i : arr){
                if(HM.containsKey(i)){
                    HM.replace(i,HM.get(i)+1);
                }else {
                    HM.put(i, 1);
                }
            }


            System.out.println("My HashMap --> "+HM);

        //iterate HM for Key(Count) > arrSize

        for(Map.Entry<Integer,Integer> e : HM.entrySet()){
                if(e.getValue()>arrSize){
                    System.out.println("Array element greater than n/2 -->"+e.getKey());
                }
        }


    }

    @Given("Rotate Array")
    public void rotateArray() {

//        Original Array: [1, 2, 3, 4, 5, 6, 7]
//        After Rotation by 3: [5, 6, 7, 1, 2, 3, 4]

        int rotationIndex=1;

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        List<Integer> lst = new ArrayList<>();
        //iterate original array from rotationIndex+1 to arr.lenght-1
        for(int i = arr.length-rotationIndex ; i < arr.length; i++){
            //Store these values in an ArrayList
            lst.add(arr[i]);
        }

        //Iterate remaining array from 0 to rotationIndex
        for(int j = 0 ; j < arr.length-rotationIndex; j++){
            lst.add(arr[j]);
        }

        System.out.println("My rotated array-->"+lst);


    }

    @Given("Merge overlapping intervals")
    public void mergeOverlappingIntervals() {

//        Consider the intervals: [[1, 3], [2, 6], [8, 10], [15, 18]].

        List<List<Integer>> lst = Arrays.asList(
                Arrays.asList(1,3),
                Arrays.asList(8,10),
                Arrays.asList(2,6),
                Arrays.asList(9,15),
                Arrays.asList(21,30)
        );

        List<List<Integer>> newList=new ArrayList<>();

        //Iterate the List-List arr and sort each array
        lst.sort(Comparator.comparingInt(a -> a.get(0)));
        for(List<Integer> iLst : lst){
            iLst.sort((a,b)->a.compareTo(b));
        }
        System.out.println("lst --> "+lst);

        //if last element > 1st element of next array --> merge both arrays --> Add in newLst array

        for(int i = 0 ; i < lst.size(); i++){
            if ((i+1)<lst.size()) {
                if(lst.get(i).get(1) > lst.get(i+1).get(0)){
                    newList.add(Arrays.asList(lst.get(i).get(0),lst.get(i+1).get(1)));
                }else{
                    if((i-1)>0 && newList.size()>i && (!newList.get(i-1).contains(lst.get(i).get(0)) || !newList.get(i-1).contains(lst.get(i).get(1))))
                    newList.add(lst.get(i));
                }
            }
            else if((i+1)==lst.size()){
                    newList.add(lst.get(i));
            }
        }

        System.out.println("new list check -->"+newList);





    }

    @Given("Reverse a singly linked list.")
    public void reverseASinglyLinkedList() {


    }


    @Given("Evaluate Reverse Polish Notation")
    public void evaluateReversePolishNotation() {
//["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
        String[] polishN = {"4", "13", "5", "/", "+"};
        String operators = "+-/*%";
        Stack<String> stk = new Stack<>();
        for(String pn : polishN){
            if(!operators.contains(pn)){
                stk.push(pn);
            }
            else {
                //operator found
                int temp=0;
                int a = Integer.parseInt(stk.pop());
                int b = Integer.parseInt(stk.pop());

                switch (pn){
                    case "+":
                        temp = b+a;
                        break;
                    case "-":
                        temp = b-a;
                        break;
                    case "*":
                        temp = b*a;
                        break;
                    case "/":
                        temp = b/a;
                        break;
                }
                stk.push(temp+"");

            }
        }
        System.out.println("-------->"+stk.pop());
    }

    @Given("Isomorphic Strings")
    public void isomorphicStrings() {
        String a = "shannon"; //-->7
        String b = "asdfghj"; // aabbccd

        if(a.length()!=b.length()){
                System.out.println("NON ISOMORPHIC STRINGS !!!");
        }else{
                //if all values in b are unique then a is isomorphic wrt b
               boolean isUnique=false;
                    for(int i = 0 ; i < b.length(); i++){
                        b.charAt(i);
                        String x = b.replaceAll(b.charAt(i)+"",""); //bbccd --> 5
                        if(x.length()<b.length()-1){
                            isUnique=false;
                            break;
                        }else
                        {
                            isUnique=true;
                        }
                    }
                //if there is atleast 1 character repeated in b then checks needed
                        if(!isUnique){

                            // if that repeated character in b has the different corresponding character in a then NON-ISOMORPHIC

//                            shannon
//                            asdfghj
//                                 s-->a
//                                 h-->s
//                                 a-->d

                            // else its ISOMORPHIC !!!

                        }

        }


    }

    @Given("User opens the {string} and loads the {string}")
    public void userOpensTheAndLoadsThe(String browser, String URL) {

        browser=Constants.config.getProperty("browser");
        URL=Constants.config.getProperty(URL);
        Constants.key.openBrowser(browser,URL);
    }

    @When("the page loads, check for all basic components loaded on the home page")
    public void thePageLoadsCheckForAllBasicComponentsLoadedOnTheHomePage() {

        LogCapture.info("Checking all components of the MarsAir page loaded...");
        String vObjlogo = Constants.MarsApp.getProperty("companyLogo");
        String vObjreportAnIssue = Constants.MarsApp.getProperty("reportAnIssue");
        String vObjproblemDefination = Constants.MarsApp.getProperty("problemDefination");
        String vObjprivacyPolicy = Constants.MarsApp.getProperty("privacyPolicy");
        String vObjformLoaded = Constants.MarsApp.getProperty("formLoaded");

        Assert.assertEquals(Constants.key.visibleWaitCondition(vObjlogo,"10"),"PASSED","ERROR >> Logo not loaded");
        Assert.assertEquals(Constants.key.visibleWaitCondition(vObjreportAnIssue,"10"),"PASSED","ERROR >> Report an Issue not loaded");
        Assert.assertEquals(Constants.key.visibleWaitCondition(vObjproblemDefination,"10"),"PASSED");
        Assert.assertEquals(Constants.key.visibleWaitCondition(vObjprivacyPolicy,"10"),"PASSED");
        Assert.assertEquals(Constants.key.visibleWaitCondition(vObjformLoaded,"10"),"PASSED");
        LogCapture.info("All components of the MarsAir page loaded SUCCESSFULLY...");

    }

    @Then("the user clicks the departure dropdown and validates its values {string}")
    public void theUserClicksTheDepartureDropdownAndValidatesItsValues(String depDDL) {
            String vObjddlValues = Constants.MarsApp.getProperty(depDDL);
            String vObjddlXpath = Constants.MarsApp.getProperty("departureDropdown");
        Assert.assertEquals(Constants.key.selectTagDropDownValidation(vObjddlXpath,vObjddlValues),"PASSED");


    }

    @When("I am on the CP Home Page and do some quick element loaded validations")
    public void iAmOnTheCPHomePageAndDoSomeQuickElementLoadedValidations() throws Exception {

        Constants.CP_POM.setDriver();
        Constants.CP_POM.handleCookiesAndPopups();
        Assert.assertEquals(Constants.key.visibleWaitCondition(Constants.CP_POM.getCP_MainMenuBar(),"3"),"PASSED","ERROR >> Main menu NOT visible");
        Assert.assertEquals(Constants.key.visibleWaitCondition(Constants.CP_POM.getCP_SubMenuBar(),"3"),"PASSED","ERROR >> Sub menu NOT visible");

    }

    @And("I navigate to the Shop Menu and select {string} category")
    public void iNavigateToTheShopMenuAndSelectCategory(String shopMenuCategory) throws Exception {

        Assert.assertEquals(Constants.key.MouseFunctions(Constants.CP_POM.getCP_ShopMenu(),"MoveToElement"),"PASSED","ERROR >> MoveToElement failed");

        switch (shopMenuCategory){
            case "Men's":
                Assert.assertEquals(Constants.key.click(Constants.CP_POM.getCP_ShopMenu_Mens(),""),"PASSED","ERROR >> getCP_ShopMenu_Mens clicked failed");
                break;
            case "Women's":
                Assert.assertEquals(Constants.key.click(Constants.CP_POM.getCP_ShopMenu_Mens(),""),"PASSED","ERROR >> getCP_ShopMenu_Mens clicked failed");
                break;
            default:
                LogCapture.error("Incorrect Shop menu cart provided");
        }


    }


    @And("I find all {string} from all paginated pages")
    public void iFindAllFromAllPaginatedPages(String productType) throws Throwable {
        Constants.CP_ShopMen_POM.setDriver();
        String mensPrdPgTitle=Constants.config.getProperty("CP_MensProductPageTitle");
        Assert.assertEquals(Constants.key.switchToWindow(mensPrdPgTitle),"PASSED","ERROR >> Child window switch failed");
        Constants.CP_ShopMen_POM.handleCookiesAndPopups();
        Assert.assertEquals(Constants.key.visibleWaitCondition(Constants.CP_ShopMen_POM.getCP_Mens_Jacket_RadioBtn(),"4"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn visibleWaitCondition failed");
        Assert.assertEquals(Constants.key.javascrpiptScroll(Constants.CP_ShopMen_POM.getCP_Mens_Jacket_RadioBtn(),""),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn javascrpiptScroll failed");
        Assert.assertEquals(Constants.key.KeyboardAction(Constants.CP_ShopMen_POM.getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.key.KeyboardAction(Constants.CP_ShopMen_POM.getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.key.KeyboardAction(Constants.CP_ShopMen_POM.getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.key.KeyboardAction(Constants.CP_ShopMen_POM.getCP_Mens_NextPage(),"upArrow"),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn KeyboardAction failed");
        Assert.assertEquals(Constants.key.click(Constants.CP_ShopMen_POM.getCP_Mens_Jacket_RadioBtn(),""),"PASSED","ERROR >> getCP_Mens_Jacket_RadioBtn clicked failed");
        Constants.CP_ShopMen_POM.saveProductListData();
    }
}
