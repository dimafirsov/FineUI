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
        WebDriver driver = Utils.chromeDriverInit();
        Screenshots s = new Screenshots(driver);

        s.setScreenshotDirs("testScreenshots");

        driver.manage().window().maximize();
        driver.get("https://facebook.com");

        s.makeExpectedScreenshot();

        driver.findElement(By.id("loginbutton")).click();
        s.makeActualScreenshot();
        s.makeDiff();

        driver.close();

        //s.removeScreenshotsRootFolder();

    }


}
