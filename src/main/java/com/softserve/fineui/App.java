package com.softserve.fineui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        structure.createFilesStructure();

        Screenshots s = new Screenshots(driver, structure.getPath());

        s.setScreenshotDirs();

        driver.manage().window().maximize();
        driver.get("https://facebook.com");

        s.makeExpectedScreenshot();

        driver.findElement(By.id("loginbutton")).click();
        s.makeActualScreenshot();
        s.makeDiff();
        driver.close();

        driver = new ChromeDriver(Utils.allowChromeNotifications());
        s = new Screenshots(driver, structure.getPath());
        s.setScreenshotDirs();
        driver.manage().window().maximize();
        driver.get("https://facebook.com");
        String login = "dmitriy.firsov@gmail.com";
        String password = "alternativerock";
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("loginbutton")).click();

        driver.findElement(By.cssSelector(".jewelButton")).click();

        s.makeExpectedScreenshotByCssSelector("._2t-a");
        driver.findElement(By.cssSelector("._19eb")).click();
        s.makeActualScreenshotByCssSelector("#blueBarDOMInspector");
        s.makeDiff();
/*
        WebElement myWebElement = driver.findElement(By.id("header_container"));
        Screenshot elementScreenshot = new AShot().takeScreenshot(driver, myWebElement);
        try{
            ImageIO.write(elementScreenshot.getImage(), "png", new File("." + File.separator + "element.png"));
        }catch(IOException e){ e.printStackTrace(); }
*/
        driver.close();

        //s.removeScreenshotsRootFolder();

    }


}
