package com.softserve.fineui;

import jdk.nashorn.internal.runtime.ECMAException;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class AbstractTest {

    public WebDriver chrome_driver;
    public WebDriver ff_driver;
    public WebDriver ie_driver;
    public ArrayList<WebDriver> drivers = new ArrayList<WebDriver>();

    int webDriverTypeItemsAmount = WebDriverType.values().length;
    WebDriverType[] webDriverTypesArray = WebDriverType.values();

    public String TEMP_DIR_NAME = "temp";
    public Integer TESTCASE_NAME_ID;
    public String TEST_NAME;
    public String TESTCASE_NAME;

    public Screenshots s4ch;
    public Screenshots s4ff;
    public Screenshots s4ie;
    public ArrayList<Screenshots> screenshots = new ArrayList<Screenshots>();

    public FilesStructure structure;
    public ExecuteDriver ed;
    public Screenshots s;


    public static int counter = 1;

    @Rule
    public TestName name = new TestName();


    @BeforeClass
    public static void beforeAll() {

    }

    @AfterClass
    public static void afterAll() {

    }

    @Before
    public void beforeTest(){
        // Creating a 'temp' folder for tests. It is removed after all tests have been run
        Utils.createDir(TEMP_DIR_NAME);

        //Evaluating the structure of the test case folder name.
        this.TESTCASE_NAME_ID = this.counter;
        this.TEST_NAME = name.getMethodName();
        this.TESTCASE_NAME = TESTCASE_NAME_ID.toString() + "." + this.TEST_NAME;

        // Creating directory structure, with timestamp, test suite name and test case name
        this.structure = new FilesStructure(this.getClass().getSimpleName(), TESTCASE_NAME);

        //Creating the directory structure for the screenshots
        for (int i = 0; i<webDriverTypeItemsAmount; i++ ) {
            this.structure.createFilesStructure(webDriverTypesArray[i]);
            switch(webDriverTypesArray[i]) {
                case CHROME:
                    this.chrome_driver = Utils.chromeDriverInit();
                    this.drivers.add(i, this.chrome_driver);
                    s4ch = new Screenshots(this.chrome_driver, this.structure.getPath(WebDriverType.values()[i]));
                    this.screenshots.add(s4ch);
                    s4ch.setScreenshotDirs();
                    break;
                case FF:
                    this.ff_driver = Utils.fireFoxDriverInit();
                    this.drivers.add(i, this.ff_driver);
                    s4ff = new Screenshots(this.ff_driver, this.structure.getPath(WebDriverType.values()[i]));
                    this.screenshots.add(s4ff);
                    s4ff.setScreenshotDirs();
                    break;
                /*case IE:
                    s4ff = new Screenshots(this.ff_driver, this.structure.getPath(WebDriverType.values()[i]));
                    //s4ch.setScreenshotDirs(SCREENSHOTS_ROOT_DIR);
                    s4ff.setScreenshotDirs();
                    break;*/
            }
        }
        //Increasing the counter value to make sure the test case id is increased
        this.counter++;
        ed = new ExecuteDriver(drivers);
        s = new Screenshots(screenshots);

    }

    @After
    public void afterTest(){
        //Cleanup procedures
    try {
        chrome_driver.close();
        //ie_driver.close();
    }catch(NullPointerException e){
        System.out.println("Driver instance was not created");
        e.printStackTrace();
    }
    try{
        ff_driver.close();
    }catch(Exception e){
        e.printStackTrace();
    }
        Utils.removeFolder(TEMP_DIR_NAME);
        //s4ch.removeScreenshotsRootFolder();
    }

}
