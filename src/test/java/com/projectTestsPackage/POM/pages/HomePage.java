package com.projectTestsPackage.POM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    By botReg=By.linkText("REGISTER");

    public HomePage(WebDriver driver){
        this.driver=driver;
    }
    public void clickOnRegisterLink(){
        driver.findElement(botReg).click();
    }
    public String getHomePageTitle(){
        return driver.getTitle();
    }
}
