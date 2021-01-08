package com.projectTestsPackage.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private DriverFactory(){

    }

    private static DriverFactory instance= new DriverFactory();

    public static DriverFactory getInstance(){
        return  instance;
    }
    ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue(){
            return null;
        }
    };

    public WebDriver getDriver(){
        return driver.get();
    }
    public WebDriver setDriver(BrowserType browser){
        String OSName=  System.getProperty("os.name").toLowerCase().contains("windows")?"windows":"mac";
        String driverPath=System.getProperty("user.dir")+ "\\drivers\\";

        switch (browser.toString()){
            case "CHROME":
                if(OSName.equals("windows")){
                    System.setProperty("webdriver.chrome.driver",driverPath + "chromedriver.exe");
                }else{
                    System.setProperty("webdriver.chrome.driver",driverPath + "chromedriver");
                }
                driver.set(new ChromeDriver());
                break;
            case "IE":
                if(OSName.equals("windows")){
                    System.setProperty("webdriver.ie.driver",driverPath + "IEDriverServer.exe");
                }
                driver.set(new InternetExplorerDriver());
                break;
            case "FIREFOX":
                if(OSName.equals("windows")){
                    System.setProperty("webdriver.gecko.driver",driverPath + "geckodriver.exe");
                }else{
                    System.setProperty("webdriver.chrome.driver",driverPath + "geckodriver");
                }
                driver.set(new FirefoxDriver());
                break;
            case "SAFARI":
                if(OSName.equals("mac")){driver.set(new SafariDriver()); }
                break;
            case "EDGE":
                if(OSName.equals("windows")){System.setProperty("webdriver.edge.driver",driverPath + "MicrosoftWebDriver.exe");}
                driver.set(new EdgeDriver());
                break;
        }
        int i=10;

        for (int j=1;j<=i;j++){
            try{
                driver.get().manage().window().maximize();
                break;
            }catch (WebDriverException we){
                driver.set(new ChromeDriver());
                driver.get().manage().window().maximize();
            }if (j==i){
                Assert.fail("Failed to maximize window: " + j + " times");
            }
        }
        driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver.get();
    }
    public void removeDrive(){
        driver.get().quit();
        driver.remove();
    }
}
