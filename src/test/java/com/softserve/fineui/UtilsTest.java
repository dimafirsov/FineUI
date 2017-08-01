package com.softserve.fineui;

import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class UtilsTest extends AbstractTest {

    @Test
    public void fileExists() throws Exception {
        String path = TEMP_DIR_NAME + File.separator + "test.txt";
        File file = new File(path);
        assertEquals(file.exists(), Utils.fileExists(path));
        file.createNewFile();
        assertEquals(file.exists(), Utils.fileExists(path));
    }

    @Test
    public void createDir() throws Exception {
        String dir = this.TEMP_DIR_NAME + File.separator + "createDir";
        Boolean result = Utils.createDir(dir);
        File testDir = new File(dir);
        testDir.mkdir();
        assertEquals(testDir.exists(), result);
    }

    @Test
    public void canAccessSomePage() {
        try{
            ed.driversGet("https://google.com");
            ed.focusOnElementByCssSelector("#gb");
            s.makeExpectedScreenshotsForAllBrowsers();

            ed.driversGet("https://google.com");
            ed.focusOnElementByCssSelector("#gb");
            s.makeActualScreenshotsForAllBrowsers();

            th.assertAllDiffs(s.makeDiffScreenshotsForAllBrowsers());

        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void canAccessWebPage() {
        try{
            for(int i = 0; i< drivers.size(); i++){
                //drivers.get(i).manage().window().maximize();
                drivers.get(i).get("https://facebook.com");
                screenshots.get(i).makeExpectedScreenshot();
                drivers.get(i).get("https://amazon.com");
                screenshots.get(i).makeActualScreenshot();
                screenshots.get(i).makeDiff();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void loginToFacebook(){
        String login = "dmitriy.firsov@gmail.com";
        String password = "alternativerock";
        chrome_driver.manage().window().maximize();
        chrome_driver.get("https://facebook.com");
        s4ch.makeActualScreenshotByCssSelector("#email");
        chrome_driver.findElement(By.id("email")).sendKeys(login);
        chrome_driver.findElement(By.id("pass")).sendKeys(password);
        chrome_driver.findElement(By.id("loginbutton")).click();
        s4ch.makeActualScreenshot();
    }
}