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
        FilesStructure structure = new FilesStructure("some_test_suite", "some_test_name");
        structure.createFileStructure();

        Screenshots s = new Screenshots(driver, structure.getPath());

        s.setScreenshotDirs("visual");

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
