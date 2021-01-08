package com.projectTestsPackage.Advanced;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogsTest {

    WebDriver driver;
    String URL="http://healthunify.com/bmicalculator/";
    Logger log=Logger.getLogger(LogsTest.class);

    @BeforeClass
    public void InitializeComponent(){
        PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log.properties");
    }
    @Test
    public void LaunchBrowser(){
        try{
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
            driver=new ChromeDriver();
            driver.get(URL);
            driver.manage().window().maximize();
            log.info("Opening Web site: " + URL);
        }catch (WebDriverException we){
            log.error("WebDriver failed: " + we.getMessage());
        }catch (Exception e){
            log.fatal(e.getMessage());
        }
    }
    @Test(dependsOnMethods = {"BMICalculator"})
    public void TearDown(){
        driver.quit();
        log.info("Browser was closed");
    }
    @Test(dependsOnMethods = {"LaunchBrowser"})
    public void BMICalculator (){
        try {
            log.info("Entering weight");
            driver.findElement(By.name("wg")).sendKeys("91");
            log.info("Selecting kilograms like units");
            new Select(driver.findElement(By.name("opt1"))).selectByVisibleText("kilograms");
            log.info("Entering height in cm");
            driver.findElement(By.xpath("//*[@name='ht' and @type='text']")).sendKeys("198");
            log.info("clicking on calculate");
            driver.findElement(By.xpath("//input[@onclick='calc()']")).click();

            String resSI=driver.findElement(By.name("si")).getAttribute("value");
            String resUS=driver.findElement(By.name("us")).getAttribute("value");
            String resUK=driver.findElement(By.name("uk")).getAttribute("value");
            String resDesc=driver.findElement(By.name("desc")).getAttribute("value");

            log.info("SI Units:" + resSI);
            log.info("US Units:" + resUS);
            log.info("UK Units:" + resUK);
            log.info("Description: " + resDesc);

        }catch (NoSuchElementException se){
            log.error("WebElement not found: " + se.getMessage());
        }catch (WebDriverException we){
            log.error("WebDriver failed: " + we.getMessage());
        }catch (Exception e){
            log.fatal(e.getMessage());
        }


    }
}
