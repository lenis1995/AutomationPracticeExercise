package com.projectTestsPackage.pageFactory.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    // Declare register page main fields
    @FindBy(name="firstName")
    WebElement firstName;
    @FindBy(name="lastName")
    WebElement lastName;
    @FindBy(name="phone")
    WebElement phoneNum;
    @FindBy(name="country")
    WebElement countryName;
    @FindBy(xpath = "//input[@id='email' and @name='email']")
    WebElement userName;
    @FindBy(name="password")
    WebElement password;
    @FindBy(name="confirmPassword")
    WebElement confPass;
    @FindBy(name="register")
    WebElement submit;

    // Declare WebElements operations

    public void setFirstName(String name){firstName.sendKeys(name);}
    public void setLastName(String lastname){lastName.sendKeys(lastname);}
    public void setPhoneNumber(String phone){phoneNum.sendKeys(phone);}
    public void setCountryName(String country){new Select(countryName).selectByVisibleText(country.toUpperCase());}
    public void setUserName(String user){userName.sendKeys(user);}
    public void setPassword(String pass){password.sendKeys(pass);}
    public void setPasswordConfirm(String pass2){confPass.sendKeys(pass2);}
    public void clickOnSubmitButton(){submit.click();}
    public void submitUserInformation(String user, String pass){
        this.setUserName(user);
        this.setPassword(pass);
        this.setPasswordConfirm(pass);
        this.clickOnSubmitButton();
    }
}
