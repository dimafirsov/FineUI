package com.softserve.fineui;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class AbstractTest {

    public WebDriver chrome_driver;
    public WebDriver ff_driver;
    public WebDriver ie_driver;
    public String TEMP_DIR_NAME = "temp";
    public Integer TESTCASE_NAME_ID;
    public String TEST_NAME;
    public String TESTCASE_NAME;
    public String SCREENSHOTS_ROOT_DIR = "visual";
    public Screenshots s;
    public FilesStructure structure;
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

        //Initializing the browser instance
        this.chrome_driver = Utils.chromeDriverInit();

        //Evaluating the structure of the test case folder name.
        this.TESTCASE_NAME_ID = this.counter;
        this.TEST_NAME = name.getMethodName();
        this.TESTCASE_NAME = TESTCASE_NAME_ID.toString() + "." + this.TEST_NAME;

        // Creating directory structure, with timestamp, test suite name and test case name
        this.structure = new FilesStructure(this.getClass().getSimpleName(), TESTCASE_NAME);
        this.structure.createFilesStructure();

        //Creating the directory structure for the screenshots
        s = new Screenshots(this.chrome_driver, this.structure.getPath());
        s.setScreenshotDirs(SCREENSHOTS_ROOT_DIR);

        //Increasing the counter value to make sure the test case id is increased
        this.counter++;
    }

    @After
    public void afterTest(){
        //Cleanup procedures
        this.chrome_driver.close();
        Utils.removeFolder(TEMP_DIR_NAME);
        //s.removeScreenshotsRootFolder();
    }

}
