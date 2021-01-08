package com.projectTestsPackage.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GridTest {
    WebDriver driver;
    String URL= "http://newtours.demoaut.com/index.php";
    String expected=null;
    String actual=null;
        WebDriverWait wait;
    DesiredCapabilities capability=null;

    @BeforeTest
    @Parameters({"browser","nodeURL"})
    public void LaunchBrowser(String browser,String nodeURL) throws Exception{
        switch (browser.toUpperCase()){
            case "CHROME":
                System.out.println("chrome");
                capability=DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setPlatform(Platform.ANY);
                break;
            case "FIREFOX":
                System.out.println("firefox");
                capability=DesiredCapabilities.firefox();
                capability.setBrowserName("firefox");
                capability.setPlatform(Platform.ANY);
                break;
            case "EDGE":
                System.out.println("edge");
                capability=DesiredCapabilities.edge();
                capability.setBrowserName("MicrosoftEdge");
                capability.setPlatform(Platform.WINDOWS);
                break;
            case "IE":
                System.out.println("iexplore");
                capability=DesiredCapabilities.internetExplorer();
                capability.setBrowserName("Internet Explorer");
                capability.setPlatform(Platform.WINDOWS);
                break;
            default: Assert.fail("Incorrect Browser");
        }
        driver=new RemoteWebDriver(new URL(nodeURL), capability);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(URL);
    }
    @BeforeMethod
    public void verifyHomePageTitle() throws InterruptedException {
        expected="Welcome: Mercury Tours";
        Thread.sleep(2000);
        actual=driver.getTitle();
        Assert.assertEquals(actual,expected);
    }
    @AfterMethod
    public void goBackToHomePage(){ driver.findElement(By.xpath("//a[@href='mercurywelcome.php']")).click();}

    @AfterTest
    public void tearDown(){driver.quit();}

    @Test(priority = 1,enabled = true)
    public void register() throws InterruptedException{
        driver.findElement(By.linkText("REGISTER")).click();
        expected="Register: Mercury Tours";
        wait.until(ExpectedConditions.titleIs("Register: Mercury Tours"));
        actual=driver.getTitle();
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 0,enabled = true)
    public void support() throws InterruptedException{
        driver.findElement(By.linkText("SUPPORT")).click();
        wait=new WebDriverWait(driver,15);
        expected="Under Construction: Mercury Tours";
        wait.until(ExpectedConditions.titleIs("Under Construction: Mercury Tours"));
        actual=driver.getTitle();
        Assert.assertEquals(actual,expected);
    }
}
