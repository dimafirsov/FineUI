package com.softserve.fineui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class UtilsTest {

    WebDriver driver;

    @Before
    public void beforeTest(){
        File tempDir = new File("temp");
            if(!tempDir.exists()){
                tempDir.mkdir();
            }

        this.driver = Utils.webDriverInit();
    }

    @After
    public void afterTest(){
        File tempDir = new File("temp");
        Utils.clearTempFolder(tempDir);

        this.driver.close();
    }

    @Test
    public void isExist() throws Exception {
        String path = "temp\\test.txt";
        File file = new File(path);
        file.createNewFile();
        assertEquals(file.exists(), Utils.isExist(path));
    }

    @Test
    public void createDir() throws Exception {
        String dirName = "temp";
    }

    @Test
    public void createDirs() throws Exception {

    }

    @Test
    public void setScreenshotDirs() throws Exception {

    }

    @Test
    public void getProps() throws Exception {

    }

}