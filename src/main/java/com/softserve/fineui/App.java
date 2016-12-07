package com.softserve.fineui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.softserve.fineui.Utils.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Utils u = new Utils();
        Screenshots s = new Screenshots();
        WebDriver driver = Utils.webDriverInit();

        String expected = getProps().getProperty("expected.screenshot.path") + "expected_screen.png";
        String actual = getProps().getProperty("actual.screenshot.path") + "actual_screen.png";
        String differ = getProps().getProperty("diff.screenshot.path") + "differ.png";


        u.setScreenshotDirs("D:\\Git\\FineUI\\testScreenshots");

        driver.manage().window().maximize();

        driver.get("https:\\facebook.com");
        s.makeScreenshot(expected, driver);

        driver.findElement(By.id("u_0_n")).click();
        s.makeScreenshot(actual, driver);

        s.makeDiff(expected, actual);

        driver.close();

    }
}
