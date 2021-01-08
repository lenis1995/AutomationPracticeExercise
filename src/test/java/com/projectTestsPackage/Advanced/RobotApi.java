package com.projectTestsPackage.Advanced;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotApi {

    WebDriver driver;

    @Test
    public void RobotAPITest() throws AWTException, InterruptedException {
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://econoshift.com/en/descargas-gratis-en-espanol/");
        driver.findElement(By.linkText("DESCARGAR")).click();

        Robot OSRobot= new Robot();
        Thread.sleep(2000);
        OSRobot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(2000);
        for(int i=0;i<3;i++){OSRobot.keyPress(KeyEvent.VK_TAB);}
        Thread.sleep(2000);
        OSRobot.keyPress(KeyEvent.VK_ENTER);
        driver.quit();
    }

}
