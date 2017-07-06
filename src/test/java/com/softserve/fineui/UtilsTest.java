package com.softserve.fineui;

import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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
    public void canAccessGooglePage() {
        try{
        driver.manage().window().maximize();
        driver.get("https://google.com");
        if(!s.actualScreenshotExists()){
            s.makeActualScreenshot();}
            s.makeExpectedScreenshot();
            s.makeDiff();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}