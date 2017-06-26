package com.softserve.fineui;

import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Dmytro Firsov on 12/3/2016.
 */
public class UtilsTest {

    private WebDriver driver;
    private String TEMP_DIR_NAME = "temp";
    private String SCREENSHOTS_ROOT_DIR = "tempScreenDir";
    private Screenshots s;

    @BeforeClass
    public static void beforeAll() {

    }

    @AfterClass
    public static void afterAll() {

    }

    @Before
    public void beforeTest(){
        Utils.createDir(TEMP_DIR_NAME);
        this.driver = Utils.chromeDriverInit();
        s = new Screenshots(this.driver);
        s.setScreenshotDirs(SCREENSHOTS_ROOT_DIR);
    }

    @After
    public void afterTest(){
        this.driver.close();
        Utils.removeFolder(TEMP_DIR_NAME);
        //s.removeScreenshotsRootFolder();
    }

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
            driver.close();
        }
    }
}