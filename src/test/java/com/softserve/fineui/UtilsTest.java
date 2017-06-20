package com.softserve.fineui;

import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class UtilsTest {

    WebDriver driver;
    String    tempDirName = "temp";

    @BeforeClass
    public static void beforeAll() {
        File testDir = new File("temp");
        testDir.mkdir();
    }

    @AfterClass
    public static void afterAll() {
        Utils.removeFolder(new File("temp"));
    }

    @Before
    public void beforeTest(){
//        this.driver = Utils.webDriverInit();
    }

    @After
    public void afterTest(){
//        this.driver.close();
    }

    @Test
    public void isExist() throws Exception {
        String path = tempDirName + File.separator + "test.txt";
        File file = new File(path);
        assertEquals(file.exists(), Utils.isExist(path));
        file.createNewFile();
        assertEquals(file.exists(), Utils.isExist(path));
    }

    @Test
    public void createDir() throws Exception {
        String dir = this.tempDirName + File.separator + "createDir";
        Boolean result = Utils.createDir(dir);
        File testDir = new File(dir);
        testDir.mkdir();
        assertEquals(testDir.exists(), result);
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

    @Test
    public void getCurrentDate() throws Exception {
        Utils.getCurrentDate();
    }

    @Test
    public void getCurrentDay() throws Exception {
        System.out.println(Utils.getCurrentDay());
    }

}