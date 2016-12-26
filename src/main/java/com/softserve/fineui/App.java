package com.softserve.fineui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Screenshots s = new Screenshots();
        WebDriver driver = Utils.webDriverInit();

        s.setScreenshotDirs("testScreenshots");

        driver.manage().window().maximize();

        driver.get("https:\\facebook.com");
        s.makeExpectedScreenshot(driver);

        driver.findElement(By.id("loginbutton")).click();
        s.makeActualScreenshot(driver);
        s.makeDiff();

        driver.close();

    }
}
