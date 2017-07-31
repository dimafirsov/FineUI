package com.softserve.fineui;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.softserve.fineui.WebDriverType;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class AbstractTest {

    public WebDriver driver;
    int webDriverTypeItemsAmount = WebDriverType.values().length;
    WebDriverType[] webDriverTypesArray = WebDriverType.values();
    public String TEMP_DIR_NAME = "temp";
    public Integer TESTCASE_NAME_ID;
    public String TEST_NAME;
    public String TESTCASE_NAME;
    public String SCREENSHOTS_ROOT_DIR = "visual";
    public Screenshots s;
    public FilesStructure structure;
    public WebDriverType webDriverType;
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
        for (int i = 0; i<webDriverTypeItemsAmount; i++ ) {

            //Initializing the browser instance
            switch (webDriverTypesArray[i]) {
                case CHROME:
                    this.driver = Utils.chromeDriverInit();
                    break;
                case FF:
                    this.driver = Utils.fireFoxDriverInit();
                    break;
                    //case IE: this.driver = new InternetExplorerDriver();
            }

            //Evaluating the structure of the test case folder name.
            this.TESTCASE_NAME_ID = this.counter;
            this.TEST_NAME = name.getMethodName();
            this.TESTCASE_NAME = TESTCASE_NAME_ID.toString() + "." + this.TEST_NAME;

            // Creating directory structure, with timestamp, test suite name and test case name
            this.structure = new FilesStructure(this.getClass().getSimpleName(), TESTCASE_NAME);
            this.structure.createFilesStructure(webDriverTypesArray[i]);

            //Creating the directory structure for the screenshots

            s = new Screenshots(this.driver, this.structure.getPath(WebDriverType.values()[i]));
            //s.setScreenshotDirs(SCREENSHOTS_ROOT_DIR);
            s.setScreenshotDirs();

            //Increasing the counter value to make sure the test case id is increased
            this.counter++;
        }
    }
@Ignore
    @After
    public void afterTest(){
        //Cleanup procedures
        Utils.closeAllDrivers(driver, webDriverType);
        Utils.removeFolder(TEMP_DIR_NAME);
        //s.removeScreenshotsRootFolder();
    }

}
