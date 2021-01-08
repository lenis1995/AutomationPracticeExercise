package com.projectTestsPackage.POM.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
    }

    // Declare register page main fields
    By firstName= By.name("firstName");
    By lastName=By.name("lastName");
    By phoneNum=By.name("phone");
    By countryName=By.name("country");
    By userName=By.xpath("//input[@id='email' and @name='email']");
    By password=By.name("password");
    By confPass=By.name("confirmPassword");
    By submit=By.name("register");


    public void setFirstName(String name){driver.findElement(firstName).sendKeys(name);}
    public void setLastName(String lastname){driver.findElement(lastName).sendKeys(lastname);}
    public void setPhoneNumber(String phone){driver.findElement(phoneNum).sendKeys(phone);}
    public void setCountryName(String country){new Select(driver.findElement(countryName)).selectByVisibleText(country.toUpperCase());}
    public void setUserName(String user){driver.findElement(userName).sendKeys(user);}
    public void setPassword(String pass){driver.findElement(password).sendKeys(pass);}
    public void setPasswordConfirm(String pass2){driver.findElement(confPass).sendKeys(pass2);}

    public void clickOnSubmitButton(){driver.findElement(submit).click();}
    public void submitUserInformation(String user, String pass){
        this.setUserName(user);
        this.setPassword(pass);
        this.setPasswordConfirm(pass);
        this.clickOnSubmitButton();
    }
}
