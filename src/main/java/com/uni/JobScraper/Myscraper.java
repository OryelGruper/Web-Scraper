package com.uni.JobScraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Scanner;


public class Myscraper {
    private static final String SiteURL ="https://www.zaplata.bg/search/?q=&city=&city_distance=0&price=200%3B10000&cat%5B0%5D=1000&go=&page=";
    private static final String JobBase ="body > div.page.lineHeightFix.pInside > ul.scheme23 > li.left > div.listItems > ul:nth-child(";
    private static final String JobTitle =") > li.c2 > a";
    private static final String JobSalary =") > li.c2 > span.is_visibility_salary";
    private static final String JobLocation =") > li.c2 > span.location";
    private static int PageLimit = 3;


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", ".\\bin\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1400,800");
        //options.setBinary("D:\\Programs\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver(options);
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Choose number of pages: ");
        int NumberOfPages = Integer.parseInt(Scanner.nextLine());
        for (int j = 1; j <= NumberOfPages; j++) {
            driver.get(SiteURL+j);
            GetAllJobs(driver);
        }
    }

    private static void GetAllJobs(WebDriver driver){
        for (int i = 1; i <= 20; i++) {
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
    private static String GetJobName(WebDriver driver, int i){
        return driver.findElement(By.cssSelector(JobBase+i+JobTitle)).getText();
    }

    private static String GetJobURL(WebDriver driver, int i){
        return driver.findElement(By.cssSelector(JobBase+i+JobTitle)).getAttribute("href");
    }

    private static String GetJobSalary(WebDriver driver, int i){
        String SalaryText;
        if ((driver.findElements(By.cssSelector(JobBase+i+JobSalary)).size() !=0)) {
                SalaryText = driver.findElement(By.cssSelector(JobBase+i+JobSalary)).getText();
                int PasswordSpace = SalaryText.indexOf(": ");
                return(SalaryText.substring(PasswordSpace+2));
                }
                else return "none";
    }

    private static String GetJobLocal(WebDriver driver, int i){
        String AddressText;
        AddressText = driver.findElement(By.cssSelector(JobBase+i+JobLocation)).getText();
        int LocalSpace = AddressText.indexOf(", ");
        return (AddressText.substring(LocalSpace+2));
    }

    private static String GetJobID(WebDriver driver, int i){
        String FindID = driver.findElement(By.cssSelector(JobBase+i+JobTitle)).getAttribute("href");
        FindID = FindID.substring(23);
                int Slash = FindID.indexOf("/");
                FindID= FindID.substring(Slash+1);
                Slash = FindID.indexOf("/");
                return (FindID.substring(Slash+1,Slash+7));
    }
}
