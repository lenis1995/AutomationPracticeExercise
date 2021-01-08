package com.projectTestsPackage.POM.tests;


import com.projectTestsPackage.POM.pages.HomePage;
import com.projectTestsPackage.POM.pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterUserTest {

    WebDriver driver;
    HomePage homePageObj;
    RegisterPage regPageObj;
    String actualResult=null;
    String expecResult=null;
    String URL= "http://newtours.demoaut.com/";

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver" , System.getProperty("user.dir")+ "\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().to(URL);
    }
    @Test (priority = 0)
    public void goToRegisterPage() {
        homePageObj= new HomePage(driver);
        actualResult=homePageObj.getHomePageTitle();
        homePageObj.clickOnRegisterLink();
        expecResult="Welcome: Mercury Tours";
        Assert.assertEquals(actualResult,expecResult);
    }
    @Test (priority = 1)
    public void registerAnUser(){
        regPageObj=new RegisterPage(driver);
        regPageObj.setFirstName("Alejo");
        regPageObj.setLastName("Lenis");
        regPageObj.setPhoneNumber("3201478");
        regPageObj.setCountryName("Colombia");
        regPageObj.submitUserInformation("lens19", "123456");

        String textSuccess=driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/" +
                                                       "tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/a/font/b")).getText();
        System.out.println("Test passed ----> " + textSuccess);
    }
    @AfterTest
    public void tearDown(){driver.quit();}
}
