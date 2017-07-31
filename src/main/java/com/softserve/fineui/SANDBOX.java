package com.softserve.fineui;

import org.openqa.selenium.WebDriver;

import javax.jws.WebService;
import java.util.ArrayList;

import static com.softserve.fineui.Utils.getCurrentDate;

/**
 * Created by dimafirsov on 30.07.17.
 */
public class SANDBOX {

    private WebDriver driver;
    private FilesStructure structure;
    private Screenshots s;

    public static int getWebDriverTypeEnumLength(){
        return WebDriverType.values().length;
    }

    public void beforeTest(){
        int webDriverTypeItemsAmount = WebDriverType.values().length;
        WebDriverType[] webDriverTypesArray = WebDriverType.values();
        //this.driver = Utils.chromeDriverInit();
        //this.driver.get("http://alterportal.ru");
        for (int i = 0; i<webDriverTypeItemsAmount; i++ ) {

            //Initializing the browser instance
            switch (webDriverTypesArray[i]) {
                case CHROME:
                    this.driver = Utils.chromeDriverInit();
                    break;
                case FF:
                    this.driver = Utils.fireFoxDriverInit();
            }
            this.structure = new FilesStructure("Test_suite_name", "Some_test_case_name");
            this.structure.createFilesStructure(webDriverTypesArray[i]);

            s = new Screenshots(this.driver, structure.getPath(webDriverTypesArray[i]));
            s.setScreenshotDirs();

        }
    }

    public static void main(String[] args) {

        new SANDBOX().beforeTest();


    }

}
