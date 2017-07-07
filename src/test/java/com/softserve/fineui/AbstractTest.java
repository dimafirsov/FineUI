package com.softserve.fineui;

import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.*;

/**
 * Created by dimafirsov on 05.07.17.
 */
public class AbstractTest {

    public WebDriver driver;
    public String TEMP_DIR_NAME = "temp";
    public Integer TESTCASE_NAME_ID;
    public String TEST_NAME;
    public String TESTCASE_NAME;
    public String SCREENSHOTS_ROOT_DIR = "visual";
    public String[] allMethods;
    public Screenshots s;
    public FilesStructure structure;
    public static int counter = 0;

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
        Utils.createDir(TEMP_DIR_NAME);
        this.driver = Utils.chromeDriverInit();
        Class currentTestSuite = this.getClass();
        /* Method[] allMethodsFromCurrentClass = currentTestSuite.getDeclaredMethods();
        allMethods = new String[allMethodsFromCurrentClass.length];
        for(int i=0; i<allMethodsFromCurrentClass.length; i++){
            allMethods[i] = allMethodsFromCurrentClass[i].getName();
        }*/
        this.TESTCASE_NAME_ID = this.counter + 1;
        //this.TEST_NAME = allMethods[TESTCASE_NAME_ID - 1];
        this.TEST_NAME = name.getMethodName();
        this.TESTCASE_NAME = TESTCASE_NAME_ID.toString() + "." + this.TEST_NAME;
        this.structure = new FilesStructure(currentTestSuite.getSimpleName(), TESTCASE_NAME);
        this.structure.createFileStructure();
        s = new Screenshots(this.driver, this.structure.getPath());
        s.setScreenshotDirs(SCREENSHOTS_ROOT_DIR);
        this.counter++;
    }

    @After
    public void afterTest(){
        this.TESTCASE_NAME_ID+=1;
        this.driver.close();
        Utils.removeFolder(TEMP_DIR_NAME);
        //s.removeScreenshotsRootFolder();
    }

}
