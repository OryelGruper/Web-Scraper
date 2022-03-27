package com.uni.JobScraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Scanner;


public class Myscraper {
    //деклериране на ползвани селектори от сайта zaplata.bg
    private static final String SiteURL ="https://www.zaplata.bg/search/?q=&city=&city_distance=0&price=200%3B10000&cat%5B0%5D=1000&go=&page=";
    //jobBase селектора се ползва е общия час на селектора което се повтария при всички други.
    private static final String JobBase ="body > div.page.lineHeightFix.pInside > ul.scheme23 > li.left > div.listItems > ul:nth-child(";
    private static final String JobTitle =") > li.c2 > a";
    private static final String JobSalary =") > li.c2 > span.is_visibility_salary";
    private static final String JobLocation =") > li.c2 > span.location";
    private static int NumberOfOffers;



    public static void main(String[] args) {
        //Деклериране на нов броузер.
        System.setProperty("webdriver.chrome.driver", ".\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");
        //options.setBinary("D:\\Programs\\Google\\Chrome\\Application\\chrome.exe");  //Направено зареди личния компютър зарели локацията на кром.
        WebDriver driver = new ChromeDriver(options);
        
        //деклериране на скенер и с него даваме избор за user да избере колко страници и колко обяви да се смъкни от сайта.
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Choose number of pages: ");
        int NumberOfPages = Integer.parseInt(Scanner.nextLine());
        System.out.println("Choose number of Offers (Max:20): ");
        NumberOfOffers = Integer.parseInt(Scanner.nextLine());
        //в фор цикъл повтаряме до броя на изброените страници метода GetALLJobs.
        for (int j = 1; j <= NumberOfPages; j++) {
            driver.get(SiteURL+j);
            GetAllJobs(driver);
        }
    }
    
    //Метод за сабира цалята информация за обява и да се принтира на екрана, напраена е да повтаря до брой изброени обяви.
    private static void GetAllJobs(WebDriver driver){
        for (int i = 1; i <= NumberOfOffers; i++) {
            JobOffers jobOffers = new JobOffers();
            jobOffers.setJobName(GetJobName(driver, i));
            jobOffers.setJobPosition(GetJobName(driver,i));
            jobOffers.setJobLink(GetJobURL(driver,i));
            jobOffers.setJobSalary(GetJobSalary(driver,i));
            jobOffers.setJobLocation(GetJobLocal(driver,i));
            jobOffers.setJobRefNumber(GetJobID(driver,i));
            System.out.println(jobOffers);
        }
    }
    
    //метод за намиране на името на обявата.
    private static String GetJobName(WebDriver driver, int i){
        return driver.findElement(By.cssSelector(JobBase+i+JobTitle)).getText();
    }
    
    //метод за намиране на линк към обявата.
    private static String GetJobURL(WebDriver driver, int i){
        return driver.findElement(By.cssSelector(JobBase+i+JobTitle)).getAttribute("href");
    }
    
    //метод за намиране на заплата на обявата, ако обявата няма заплата написана ще връща "none" като резултат
    //също така зареди начина по което заплатата е написана в сайта, трябва да ползвам indexof и substring да смъкна точно текста което ми трябва.
    private static String GetJobSalary(WebDriver driver, int i){
        String SalaryText;
        if ((driver.findElements(By.cssSelector(JobBase+i+JobSalary)).size() !=0)) {
                SalaryText = driver.findElement(By.cssSelector(JobBase+i+JobSalary)).getText();
                int PasswordSpace = SalaryText.indexOf(": ");
                return(SalaryText.substring(PasswordSpace+2));
                }
                else return "none";
    }
    //метод за намиране на локацията на обявата,също като е при заплатата трябва да се ползва indexof и substring да се смъкне локацията.
    private static String GetJobLocal(WebDriver driver, int i){
        String AddressText;
        AddressText = driver.findElement(By.cssSelector(JobBase+i+JobLocation)).getText();
        int LocalSpace = AddressText.indexOf(", ");
        return (AddressText.substring(LocalSpace+2));
    }

    //метод за намиране на Рефериран номер на обявата, той се намира в линка на обявата и indexof се ползва зареди причината че номера е след 3 " / " символа.
    //също така номера винаги е 6 символа, зареди това substring е slash+1 до slash+7.
    private static String GetJobID(WebDriver driver, int i){
        String FindID = driver.findElement(By.cssSelector(JobBase+i+JobTitle)).getAttribute("href");
        FindID = FindID.substring(23);
                int Slash = FindID.indexOf("/");
                FindID= FindID.substring(Slash+1);
                Slash = FindID.indexOf("/");
                return (FindID.substring(Slash+1,Slash+7));
    }
}
