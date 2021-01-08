package com.projectTestsPackage.driverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {

    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeTest
    public void setupTest(){
        DriverFactory.getInstance().setDriver(BrowserType.FIREFOX);
        driver= DriverFactory.getInstance().getDriver();
        driver.get("https://www.amazon.com/-/es/");
        driverWait=new WebDriverWait(driver,15);
    }
    @Test(priority = 0,enabled=true)
    public void searchJordanSneakers(){
        WebElement searchField=driver.findElement(By.name("field-keywords"));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated((By.name("field-keywords"))));
        searchField.sendKeys("Jordan Retro 4");
        driver.findElement(By.xpath("//input[@value='Ir']")).click();

        WebElement jordanCheck=driver.findElement(By.xpath("//*[@class='a-icon a-icon-checkbox']"));
        driverWait.until(ExpectedConditions.visibilityOf(jordanCheck));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",jordanCheck);
        jordanCheck.click();
    }
    @Test(priority=1,enabled = true)
    public void searchFirstFiveItems(){
        for(int i=0;i<5;i++){

            String productName=driver.findElement(By.xpath("//*[@data-index='"+ i + "']/div/span/div/div/div[2]/h2/a/span")).getText();
            String productRate=driver.findElement(By.xpath("//*[@data-index='"+ i + "']" +
                                      "/div/span/div/div/div[3]/div/span")).getAttribute("aria-label");
            System.out.println("Product name's= " + productName + "\nProduct rate's: " + productRate);
        }
    }
    @AfterTest
    public void tearDown(){
        DriverFactory.getInstance().removeDrive();
    }
}
